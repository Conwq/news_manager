package org.example.news_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.news_manager.bean.User;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;

@Controller
@RequestMapping("/user")
public class UsersController {
	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	public String showRegistrationPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("action", "registrationPage");
		return "baseLayout/baseLayout";
	}
	
	public String registrationUser(@ModelAttribute("user") User user,
								   @RequestParam("confirmPassword") String confirmPassword,
								   @RequestParam("country") String country) {
		try {
			userService.registration(user, confirmPassword, country);
			return "redirect:/news";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
}
