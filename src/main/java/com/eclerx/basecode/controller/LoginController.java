package com.eclerx.basecode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/signin")
	public String getHomePage() {
		return "login";
	}
	
}
