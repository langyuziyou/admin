package admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.entity.Model;
import admin.service.DdtService;

@Scope("prototype")
@Controller
@RequestMapping("/category")
public class ddtController {
	private static final Logger LOGGER = Logger.getLogger(ddtController.class);
	
	@Autowired
	private DdtService ddtService;
	
	@RequestMapping(value = "/list")
	public ModelAndView categoryList(HttpServletRequest request) {
		LOGGER.info(" categoryList ");

		List<Map<String, Object>> list = ddtService.ddtList();

		request.setAttribute("list", list);

		return new ModelAndView("/sys/list");
	}
	
	
	/**
	 * 
	* 方法名: goAdd 
	* 描述: 跳转到 新增模块
	* 参数: @param request
	* 参数: @return    设定文件 
	* 时间:2018年4月9日上午9:47:35 
	* 返回: ModelAndView    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd(HttpServletRequest request) {
		request.setAttribute("model", null);
		return new ModelAndView("/sys/add");
	}
	
	/**
	 * 
	* 方法名: add 
	* 描述: 新增
	* 参数: @param request
	* 参数: @return    设定文件 
	* 时间:2018年4月9日上午10:03:56 
	* 返回: ModelAndView    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		String name = request.getParameter("name");
		String imageUrl = request.getParameter("imageUrl");
		String id = request.getParameter("id");
		LOGGER.info(" name " + name);
		LOGGER.info(" imageUrl " + imageUrl);
		
		// 新增
		if(id.equals("0")) {
			ddtService.addDdt(name, imageUrl);
		}
		// 编辑
		else
		{
			ddtService.editDdt(Long.parseLong(id),name, imageUrl);
		}
		return "redirect:list";
		//return new ModelAndView("/sys/list");
	}
	
	/**
	 * 
	* 方法名: goEdit 
	* 描述: 跳转编辑页面
	* 参数: @param request
	* 参数: @return    设定文件 
	* 时间:2018年4月9日上午10:40:40 
	* 返回: ModelAndView    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String,Object> entity = ddtService.findById(id);
		LOGGER.info(" id " + id);
		request.setAttribute("model", entity);
		return new ModelAndView("/sys/add");
	}
	
	
	/**
	 * 
	* 方法名: del 
	* 描述: 删除 
	* 参数: @param request
	* 参数: @return    设定文件 
	* 时间:2018年4月9日上午11:10:16 
	* 返回: ModelAndView    返回类型 
	* 作者: yzj 
	* @throws
	 */
	@RequestMapping(value = "/del")
	public String del(HttpServletRequest request) {
		String id = request.getParameter("id");
		ddtService.delDdt(Long.parseLong(id));
		return "redirect:list";
	}

}
