package admin.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.service.GroupService;
import admin.utils.AdminConstants;
import admin.utils.ResponseUtils;

@Scope("prototype")
@Controller
@RequestMapping("/groupApi")
public class GroupApi extends BaseApi{
	private static final Logger LOGGER = Logger.getLogger(GroupApi.class);
	
	@Autowired
	private GroupService groupService;
	/**
	 * 
	* 方法名: list 
	* 描述: 用户组列表  (分页，关键字)
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日下午3:39:39 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/list")
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

			Map<String, Object> result = groupService.groupList(map);
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
	* 方法名: premissionList 
	* 描述: 获取所有 权限列表 (权限列表页面用)
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日下午4:24:13 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/premissionList")
	@ResponseBody
	public void premissionList(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		try {
			// conditions
			Map<String, Object> map = new HashMap<>();
			Map<String, Object> result = groupService.permissionList(map);
			sb = successStr(sb, result);
		} catch (Exception e) {
			sb = exceptionStr(sb, e.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}
	
	/**
	 * 
	* 方法名: groupList 
	* 描述: 获取所有 用户组列表 (权限列表页面用)
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日下午4:34:19 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/groupList")
	@ResponseBody
	public void groupList(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		try {
			// conditions
			Map<String, Object> map = new HashMap<>();
			map.put("name", "");// 搜索名称
			map.put("page", 1);// 页数
			map.put("pageSize", 1000);// 条数
			
			Map<String, Object> result = groupService.groupList(map);
			result.remove("pageCount");
			result.remove("count");
			
			sb = successStr(sb, result);
		} catch (Exception e) {
			sb = exceptionStr(sb, e.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}
	
	/**
	 * 
	* 方法名: group permission 
	* 描述: 获取 组的权限 (权限列表页面用)
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日下午4:38:53 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/groupPermission")
	@ResponseBody
	public void groupPermission(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		String groupId = request.getParameter("groupId") != null ? request.getParameter("groupId") : "";
		if(groupId.equals("")) {
			sb = exceptionStr(sb, "组ID 为空 ");
			ResponseUtils.renderJson(response, sb.toString());
			return;
		}
		try {
			Map<String, Object> result = groupService.groupPermission(groupId);
			
			sb = successStr(sb, result);
		} catch (Exception e) {
			sb = exceptionStr(sb, e.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}
	
	
	/**
	 * 
	* 方法名: saveGroupPermission 
	* 描述: 保存用户组权限  权限ID用','隔开 (权限列表页面用) 
	* 参数: @param request
	* 参数: @param response    设定文件 
	* 时间:2018年4月11日下午4:57:48 
	* 返回: void    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/saveGroupPermission")
	@ResponseBody
	public void saveGroupPermission(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		String groupId = request.getParameter("groupId") != null ? request.getParameter("groupId") : "";
		String permissionIds = request.getParameter("permissionIds") != null ? request.getParameter("permissionIds") : "";
		if(groupId.equals("")) {
			sb = exceptionStr(sb, "组ID 为空 ");
			ResponseUtils.renderJson(response, sb.toString());
			return;
		}
		try {
			Map<String, Object> result = groupService.saveGroupPermission(groupId,permissionIds);
			
			sb = successStr(sb, result);
		} catch (Exception e) {
			sb = exceptionStr(sb, e.toString());
		}
		ResponseUtils.renderJson(response, sb.toString());
	}
	
}
