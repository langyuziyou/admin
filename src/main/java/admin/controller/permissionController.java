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

import admin.service.PermissionService;

@Scope("prototype")
@Controller
@RequestMapping("/permission")
public class permissionController {
	private static final Logger LOGGER = Logger.getLogger(permissionController.class);
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value = "/list")
	public ModelAndView categoryList(HttpServletRequest request) {
		LOGGER.info(" permissionList ");
		StringBuffer sb = new StringBuffer();
		List<Map<String, Object>> list = permissionService.permissionListByUserId("62");
		for(Map<String, Object> m:list) {
			System.out.println(m.get("DDT_PERMISSION_ID"));
			sb.append("_"+m.get("DDT_PERMISSION_ID")+"_,");
		}

		request.setAttribute("permissionList", sb.toString());

		return new ModelAndView("/sys/permissionList");
	}
}
