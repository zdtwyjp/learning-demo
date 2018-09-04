package org.tech.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tech.spring.entity.User;
import org.tech.spring.entity.vo.JsonResult;
import org.tech.spring.service.SysService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private SysService sysService;

	@ResponseBody
	@RequestMapping("/save")
	public JsonResult saveEntity() {
		User u = new User();
		u.setAccount("admin");
		u.setName("Admin");
		sysService.saveOrUpdateEntity(u);
		return new JsonResult(true);
	}
	
}
