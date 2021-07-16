package com.dxc.application.controllers;

import javax.servlet.http.HttpServletRequest;

import com.dxc.application.services.GimMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@Autowired
	GimMasterService homeService;

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		return "views/home.html";
	}
	
	@GetMapping("/home/js/home.js")
	public String js() {
		return "js/home.js";
	}
}
