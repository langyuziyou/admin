package admin.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import admin.entity.AjaxJson;
import admin.myThread.EmailThread;
import admin.service.PermissionService;
import admin.service.UserService;
import admin.utils.AdminConstants;
import admin.utils.Email;
import admin.utils.PasswordUtil;
import admin.utils.ResponseUtils;

@Scope("prototype")
@Controller
@RequestMapping("/userApi")
public class UserApi extends BaseApi {
	private static final Logger LOGGER = Logger.getLogger(UserApi.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;

	/**
	 * 
	* 方法名: list  
	* 描述: 获取用户  列表 分页查询  , 可输入
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日上午10:58:57 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		try {
			int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
			int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize"))
					: AdminConstants.PAGE_SIZE;
			// conditions
			String name = request.getParameter("name") != null ? request.getParameter("name") : "";

			Map<String, Object> map = new HashMap<>();
			map.put("name", name);// 搜索名称
			map.put("page", page);// 页数
			map.put("pageSize", pageSize);// 条数

			Map<String, Object> result = userService.userList(map);
			result.put("name", name);
			result.put("page", page);
			result.put("pageSize", pageSize);
			sb = successStr(sb, result);
		} catch (Exception e) {
			sb = exceptionStr(sb, e.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}

	/**
	 * 
	* 方法名: adminLoad 
	* 描述: 管理员登录 
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日上午11:16:14 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/adminLoad")
	@ResponseBody
	public void adminLoad(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		String accountName = request.getParameter("accountName") != null ? request.getParameter("accountName") : "";
		String pass = request.getParameter("pass") != null ? request.getParameter("pass") : "";
		// 验证条件
		if (accountName.equals("")) {
			sb = exceptionStr(sb, "用户名为空");
			ResponseUtils.renderJson(response, sb.toString());
			return;
		}
		if (pass.equals("")) {
			sb = exceptionStr(sb, "密码为空");
			ResponseUtils.renderJson(response, sb.toString());
			return;
		}
		try {
			// 获取密码（暗纹）
			String realPass = PasswordUtil.encrytPwd(pass, accountName);
			System.out.println(realPass);
			// 验证 账户 
			Map<String, Object> result = userService.findByAccountName(accountName, realPass);
			result.remove("PAY_PASSWORD");
			result.remove("PASSWORD");
			// 获取 权限 
			List<Map<String, Object>> list = permissionService.permissionListByUserId(result.get("USER_ID").toString());
			result.put("permissionList", list);
			if (result != null) {
				sb = successStr(sb, result);
			}
		} catch (EmptyResultDataAccessException e) {
			sb = exceptionStr(sb, "登入失败：未匹配到用户");
		}catch(Exception e1) {
			sb = exceptionStr(sb, e1.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}
	
	
	/**
	 * 
	* 方法名: sendEmail 
	* 描述: 找回密码发送 电子邮件 同时修改密码 
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日下午2:12:53 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		String accountName = request.getParameter("accountName") != null ? request.getParameter("accountName") : "";
		// 验证条件
		if (accountName.equals("")) {
			sb = exceptionStr(sb, "用户名为空");
			ResponseUtils.renderJson(response, sb.toString());
			return;
		}
		try {
			// 查询用户是否存在
			Map<String, Object> result = userService.findByAccountName(accountName,null);
			
			// 获取密码（暗纹）
			String random = getRandomPassWord();// 随机密码 
			String realPass = PasswordUtil.encrytPwd(random, accountName);// 暗码 
			int editResult = userService.editUserPassWord(accountName,realPass);// 修改用户密码 
			
			// 延时    发送邮件  
			EmailThread eThread= new EmailThread(result.get("EMAIL").toString(), result.get("USER_ACCOUNT").toString(), random);
			eThread.start();

			Map<String,Object> reMap = new HashMap<>();
			reMap.put("msg", "修改成功，新的密码已经发送到您的邮件:"+result.get("EMAIL").toString());
			if(editResult == 1) {
				sb = successStr(sb, reMap);
			}
		} catch (EmptyResultDataAccessException e) {
			sb = exceptionStr(sb, "发送失败：未匹配到用户");
		}catch(Exception e1) {
			sb = exceptionStr(sb, e1.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}

}
