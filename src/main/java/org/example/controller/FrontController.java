package org.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.example.service.NewsService;
import org.example.service.UserService;
import org.example.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bean.News;
import bean.User;

@Controller
@RequestMapping("/news")
public class FrontController {
	private NewsService newsService;
	private UserService userService;
	
	@Autowired
	public FrontController(NewsService newsService, UserService userService) {
		this.newsService = newsService;
		this.userService = userService;
	}
	
	@RequestMapping
	public String goToBasePage(Model model) {
//		List<News> newses = newsService.getNewses("5");
//		model.addAttribute("news", newses);
//		model.addAttribute("action", "newsList");
		return "baseLayout/baseLayout";
	}
	
	@RequestMapping("/goToNewsList")
	public String goToNewsList(Model model) {
		try {
			List<News> newses = newsService.getNewses();
			model.addAttribute("news", newses);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
	@RequestMapping("/goToEditNews")
	public String showEditNewsPage(@RequestParam("id") String id, Model model) {
		try {
			News news = newsService.findById(id);
			model.addAttribute("news", news);
			return "tiles/editNews";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
	@RequestMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") News news) {
		try {
			newsService.editNews(news);
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("id") String id) {
		try {
			newsService.deleteNewsById(id);
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
	@RequestMapping("/goToRegistrationPage")
	public String showRegistrationPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "tiles/registrationPage";
	}
	
	@RequestMapping("/doRegistrationUser")
	public String doRegistration(@ModelAttribute("user") User user, HttpServletRequest request) {
		try {
			userService.registration(user);
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
	@RequestMapping("/doSignIn")
	public String doSignIn(@RequestParam("login") String login, @RequestParam("password") String password, HttpServletRequest request) {
		try {
			User user = userService.signIn(login, password);
			HttpSession session = request.getSession();
			session.setAttribute("active", "true");
			session.setAttribute("role", user.getRoleName());
			return "redirect:/news";
		}
		catch(ServiceException e) {
			return "";
		}
	}
	
	@RequestMapping("/doSignOut")
	public String signOut(HttpServletRequest request) {
		try {
			request.getSession(true).invalidate();
			return "redirect:/news";
		}
		catch(IllegalStateException e) {
			return "";
		}
	}
}