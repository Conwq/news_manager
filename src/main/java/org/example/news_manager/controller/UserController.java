package org.example.news_manager.controller;

import org.example.news_manager.bean.UserRegistrationDataBean;
import org.example.news_manager.bean.UserInfoBean;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

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

	@PostMapping("/doSignIn")
	public String doSignIn(@RequestParam("username") String login,
						   @RequestParam("password") String password,
						   HttpServletRequest request) {
		try {
			UserInfoBean userInfoBean = userService.signIn(login, password);
			Locale locale = userInfoBean.getLocale();
			HttpSession session = request.getSession(true);
			session.setAttribute("active", "true");
			session.setAttribute("locale", locale);
			session.setAttribute("localization", locale.getLanguage());
			session.setAttribute("user", userInfoBean);
			return "redirect:/news/goToNewsList";
		}
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@PostMapping("/doSignOut")
	public String signOut(HttpServletRequest request) {
		try {
			request.getSession(true).invalidate();
			return "redirect:/news";
		}
		catch (IllegalStateException e) {
			return "redirect:/news/errorPage";
		}
	}
}
