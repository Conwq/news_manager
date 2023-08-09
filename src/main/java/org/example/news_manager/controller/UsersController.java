package org.example.news_manager.controller;

import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UsersController {
	private final UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	public String showRegistrationPage(Model model) {
		UserDTO userBean = new UserDTO();
		model.addAttribute("user", userBean);
		model.addAttribute("action", "registrationPage");
		return "baseLayout/baseLayout";
	}
	
	public String registrationUser(@ModelAttribute("user") UserDTO userBean,
								   @RequestParam("confirmPassword") String confirmPassword,
								   @RequestParam("localeName") String localeName) {
		try {
			userService.registration(userBean, confirmPassword, localeName);
			return "redirect:/news";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
}
