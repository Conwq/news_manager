package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.bean.User;
import org.example.service.UserService;
import org.example.service.exception.ServiceException;

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
	
	public String registrationUser(@ModelAttribute("user") User user, @RequestParam("confirmPassword") String confirmPassword) {
		try {
			userService.registration(user, confirmPassword);
			return "redirect:/news";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
}
