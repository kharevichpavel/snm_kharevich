package by.htp.spr.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.htp.spr.controller.util.ControllerAttribute;
import by.htp.spr.controller.util.ErrorParameter;
import by.htp.spr.entity.News;
import by.htp.spr.entity.Role;
import by.htp.spr.entity.User;
import by.htp.spr.service.NewsService;
import by.htp.spr.service.NewsServiceException;
import by.htp.spr.service.UserService;
import by.htp.spr.service.UserServiceException;

@Controller
@RequestMapping("/newsManagement")
public class UserController {

	private final static Logger log = LogManager.getRootLogger();

	@Autowired
	private UserService userService;

	@Autowired
	private NewsService newsService;

	@Transactional
	@RequestMapping("/showBasePage")
	public String showBasePage(Model theModel, HttpSession session, HttpServletRequest request) {
		try {
			User user = new User();
			List<News> listNews;
			listNews = newsService.getNews();
			News news = new News();
			session.setAttribute(ControllerAttribute.ATTRIBUTE_LIST_NEWS, listNews);
			theModel.addAttribute(ControllerAttribute.ATTRIBUTE_LIST_NEWS, listNews);
			theModel.addAttribute(ControllerAttribute.MODEL_NEWS, news);
			theModel.addAttribute(ControllerAttribute.MODEL_USER, user);
			session.setAttribute(ControllerAttribute.PRESENTATION,
					request.getParameter(ControllerAttribute.PRESENTATION));
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_4);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER_4);
		}
		return "basePage";
	}

	@Transactional
	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	@Transactional
	@RequestMapping("/login")
	public String logination(@ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.MODEL_USER) User user, HttpServletRequest request,
			HttpSession session) {
		request.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_LIST);
		return "basePage";
	}

	@Transactional
	@RequestMapping("/registration")
	public String registration(@ModelAttribute(ControllerAttribute.MODEL_USER) User user, HttpServletRequest request) {
		request.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_REGISTRATION);
		return "basePage";
	}

	@Transactional
	@PostMapping("/doRegistration")
	public String doRegistration(@ModelAttribute(ControllerAttribute.MODEL_USER) User user, HttpServletRequest request,
			HttpSession session) {
		try {
			Role role = new Role();
			role.setUser(user);
			user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
			user.getRole();
			if (!userService.registration(user, role)) {
				session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_8);
				return "redirect:error";
			}
			request.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_LIST);
		} catch (UserServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_8);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER_8);
		}
		return "basePage";
	}

	@Transactional
	@RequestMapping("/logout")
	public String doSignOut(@ModelAttribute(ControllerAttribute.MODEL_USER) User user, HttpSession session) {
		return "basePage";
	}
}
