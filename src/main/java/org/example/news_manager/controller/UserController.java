package org.example.news_manager.controller;

import org.example.news_manager.bean.UserRegistrationDataBean;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/goToRegistrationPage")
	public String showRegistrationPage(Model model) {
		UserRegistrationDataBean user = new UserRegistrationDataBean();
		model.addAttribute("user", user);
		model.addAttribute("action", "registrationPage");
		return "baseLayout/baseLayout";
	}

	@PostMapping("/doRegistrationUser")
	public String doRegistration(@ModelAttribute("user") @Valid UserRegistrationDataBean user,
								 BindingResult bindingResult,
								 Model model) {
		try {
			if(bindingResult.hasErrors()){
				model.addAttribute("action", "registrationPage");
				return "baseLayout/baseLayout";
			}
			userService.registration(user);
			return "redirect:/news";
		}
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/authentication")
	public String goToNewsList(){
		return "redirect:/news/goToNewsList";
	}

	@GetMapping("/access-denied")
	public String getAccessDeniedPage(){
		return "accessDenied";
	}
}
