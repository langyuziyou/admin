package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import admin.service.UserService;
import admin.utils.AdminConstants;

@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list")
	public ModelAndView categoryList(HttpServletRequest request) {
		LOGGER.info(" userList ");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize"))
                : AdminConstants.PAGE_SIZE;
        // conditions
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";

		Map<String,Object> map = new HashMap<>();
		map.put("name", name);// 搜索名称
		map.put("page", page);// 页数
		map.put("pageSize", pageSize);// 条数
		
		Map<String, Object> result = userService.userList(map);

		request.setAttribute("list", result.get("list"));
		request.setAttribute("pageCount", result.get("pageCount"));// 总页数
		request.setAttribute("count", result.get("count")); // 总数量
		request.setAttribute("pageSize", pageSize); //每页数量
		request.setAttribute("page", page); // 当前页数 
		request.setAttribute("name", name); // 搜索名称

		return new ModelAndView("/sys/userList");
	}
	
	
	/**
	 * 
	* 方法名: add 
	* 描述: 新增用户 
	* 参数: @param request
	* 参数: @return    设定文件 
	* 时间:2018年4月10日下午4:28:40 
	* 返回: String    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		String USER_NAME = request.getParameter("USER_NAME");//用户名称
		String USER_SPELL = request.getParameter("USER_SPELL");//用户拼音
		String SEX = request.getParameter("SEX");// 性别（1.man 2.famle）
		String BOTHDAY = request.getParameter("BOTHDAY");//出身日期
		String EMAIL = request.getParameter("EMAIL");//电子邮件
		String ID_CRAD = request.getParameter("ID_CRAD");//身份证、护照
		String PHONE = request.getParameter("PHONE");//手机 
		String NATION = request.getParameter("NATION");// 国家 
		String PROVINCE = request.getParameter("PROVINCE");// 省份
		String CITY = request.getParameter("CITY");// 城市
		String POST = request.getParameter("POST");// 邮编
		String ADDRESS = request.getParameter("ADDRESS");// 地址
/*		GUARANTOR
		FIRST_LANGUAGE
		USER_ACCOUNT*/
		
		
		
		
		String id = request.getParameter("id");
		Map<String,Object> map = new HashMap<>();
		
		// 新增
		if(id.equals("0")) {
			map.put("USER_NAME", USER_NAME);
			userService.addUser(map);
		}
		// 编辑
		else
		{
			userService.editUser(map);
		}
		return "redirect:list";
		//return new ModelAndView("/sys/list");
	}
	

}
