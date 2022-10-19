package by.htp.spr.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

	@RequestMapping("/")
	public String showBasePage() {
		return "redirect:newsManagement/showBasePage";
	}
}
