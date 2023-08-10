package org.example.news_manager.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.example.news_manager.dto.NewsDTO;
import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.service.NewsService;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news")
public class FrontController {
	private final NewsService newsService;
	private final UserService userService;
	private final ServletContext context;

	@Autowired
	public FrontController(NewsService newsService, UserService userService, ServletContext context) {
		this.newsService = newsService;
		this.userService = userService;
		this.context = context;
	}

	@RequestMapping
	public String goToBasePage(HttpServletRequest request, Model model) {
		try {
			String localeParameter = (String) request.getSession().getAttribute("localization");
			Locale locale = localeParameter == null ? LocaleContextHolder.getLocale() : new Locale(localeParameter);

			List<NewsDTO> news = newsService.getNewses("5", locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/goToNewsList")
	public String goToNewsList(@SessionAttribute("locale") Locale locale, Model model) {
		try {
			List<NewsDTO> news = newsService.getNewses(locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/goToViewNews")
	public String showNews(@RequestParam("id") String id, 
			@SessionAttribute("locale") Locale locale, Model model) {
		try {
			NewsDTO news = newsService.findById(id, locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "viewNews");
			return "baseLayout/baseLayout";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/goToEditNews")
	public String showEditNewsPage(@RequestParam("id") String id, Model model) {
		try {
			NewsDTO news = newsService.findById(id);
			model.addAttribute("news", news);
			model.addAttribute("action", "editNews");
			return "baseLayout/baseLayout";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") NewsDTO news) {
		try {
			newsService.editNews(news);
			return "redirect:/news/goToNewsList";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/goToAddNewsPage")
	public String showAddNewsPage(Model model) {
		NewsDTO news = new NewsDTO();
		model.addAttribute("news", news);
		model.addAttribute("action", "addNewsPage");
		return "baseLayout/baseLayout";
	}

	@RequestMapping("/doAddNews")
	public String addNews(@ModelAttribute("news") NewsDTO news,
			@RequestParam("image") MultipartFile image) {
		try {
			newsService.addNews(news);
			return "redirect:/news/goToNewsList";
		}
		catch (ServiceException e){
			return "";
		}
	}

	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("id") String id) {
		try {
			newsService.deleteNewsById(id);
			return "redirect:/news/goToNewsList";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doDeleteSomeNews")
	public String deleteNewsList(@RequestParam("news") String[] news) {
		try {
			newsService.deleteNewsList(news);
			return "redirect:/news/goToNewsList";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/changeLocale")
	public String changeLocale(HttpServletRequest request) throws URISyntaxException {
		request.getSession().setAttribute("localization", request.getParameter("localization"));
		request.getSession().setAttribute("locale", new Locale(request.getParameter("localization")));
		URI uri = new URI(request.getHeader("referer"));
		String path = uri.getPath();
		if (uri.getQuery() != null) {
			path += "?" + uri.getQuery();
		}
		return "redirect:" + path;
	}

	@RequestMapping("/goToRegistrationPage")
	public String showRegistrationPage(Model model) {
		UserDTO userBean = new UserDTO();
		model.addAttribute("user", userBean);
		model.addAttribute("action", "registrationPage");
		return "baseLayout/baseLayout";
	}

	@RequestMapping("/doRegistrationUser")
	public String doRegistration(@ModelAttribute("user") UserDTO userBean,
			@RequestParam("confirmPassword") String confirmPassword, @RequestParam("localeName") String localeName) {
		try {
			userService.registration(userBean, confirmPassword, localeName);
			return "redirect:/news";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doSignIn")
	public String doSignIn(@RequestParam("username") String login, @RequestParam("password") String password,
			HttpServletRequest request) {
		try {
			UserDTO userBean = userService.signIn(login, password);
			Locale locale = userBean.getLocale();
			HttpSession session = request.getSession(true);
			session.setAttribute("active", "true");
			session.setAttribute("role", userBean.getRoleName());
			session.setAttribute("locale", locale);
			session.setAttribute("localization", locale.getLanguage());
			return "redirect:/news/goToNewsList";
		} catch (ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doSignOut")
	public String signOut(HttpServletRequest request) {
		try {
			request.getSession(true).invalidate();
			return "redirect:/news";
		} catch (IllegalStateException e) {
			return "";
		}
	}
}