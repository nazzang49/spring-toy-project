package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class MainController {

	@RequestMapping(value="")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping(value="/test-grapedrop")
	public String getTestGrapeDrop() {
		return "test-grapedrop";
	}
	
}
