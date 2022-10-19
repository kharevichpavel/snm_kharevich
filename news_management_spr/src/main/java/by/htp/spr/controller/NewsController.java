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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import by.htp.spr.controller.util.ControllerAttribute;
import by.htp.spr.controller.util.ErrorParameter;
import by.htp.spr.controller.util.UtilParameter;
import by.htp.spr.entity.News;
import by.htp.spr.entity.User;
import by.htp.spr.service.NewsService;
import by.htp.spr.service.NewsServiceException;

@Controller

@RequestMapping("/newsManagement")
public class NewsController {

	private final static Logger log = LogManager.getRootLogger();

	@Autowired
	private NewsService newsService;

	@Transactional
	@RequestMapping("/newsList")
	public String listNews(Model theModel, @ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.MODEL_USER) User user, HttpSession session,
			HttpServletRequest request) {
		try {
			session.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_LIST);
			news.setUser(user);
			theModel.addAttribute(ControllerAttribute.MODEL_NEWS, news);
			List<News> listNewsForPagination;
			int paginationSizeForPage;
			int dbRowCount;
			int firstPagination;
			int pageNumber;
			if (request.getParameter(UtilParameter.PAGINATION_SIZE_FROM_USER) == null) {
				firstPagination = UtilParameter.START_PAGINATION_SIZE;
			} else {
				firstPagination = Integer.parseInt(request.getParameter(UtilParameter.PAGINATION_SIZE_FROM_USER));
			}
			if (session.getAttribute(UtilParameter.PAGINATION_PAGE_NUMBER) == null) {
				pageNumber = UtilParameter.START_PAGINATION_PAGE;
			} else {
				pageNumber = (int) session.getAttribute(UtilParameter.PAGINATION_PAGE_NUMBER);
			}
			listNewsForPagination = newsService.getNewsListOne(pageNumber, firstPagination);
			dbRowCount = (int) newsService.totalPages(firstPagination);
			paginationSizeForPage = (int) Math.ceil((double) dbRowCount / firstPagination);
			session.setAttribute(UtilParameter.PAGINATION_PAGE_NUMBER, pageNumber);
			session.setAttribute(UtilParameter.PAGINATION_SIZE_FROM_SESSION, firstPagination);
			session.setAttribute(UtilParameter.PAGINATION_SIZE_FOR_PAGE, paginationSizeForPage);
			session.setAttribute(ControllerAttribute.ATTRIBUTE_LIST_NEWS, listNewsForPagination);
			theModel.addAttribute(ControllerAttribute.ATTRIBUTE_LIST_NEWS, listNewsForPagination);

		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_7);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER_7);
		}
		return "basePage";
	}

	@Transactional
	@GetMapping("/pagination")
	public String pagination(HttpSession session, Model theModel, HttpServletRequest request,
			@ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.MODEL_USER) User user) {
		try {
			List<News> listNewsForPagination;
			int paginationSizeForPage;
			int dbRowCount;
			int firstPagination = 0;
			int pageNumber;
			if (request.getParameter(UtilParameter.PAGINATION_SIZE_FROM_USER) == null
					&& session.getAttribute(UtilParameter.PAGINATION_SIZE_FROM_SESSION) != null) {
				firstPagination = (int) session.getAttribute(UtilParameter.PAGINATION_SIZE_FROM_SESSION);
			} else if (request.getParameter(UtilParameter.PAGINATION_SIZE_FROM_USER) != null) {
				firstPagination = Integer.parseInt(request.getParameter(UtilParameter.PAGINATION_SIZE_FROM_USER));
			}
			if (request.getParameter(UtilParameter.PAGINATION_SIZE_FROM_USER) != null) {
				pageNumber = UtilParameter.START_PAGINATION_PAGE;
			} else if (request.getParameter(UtilParameter.PAGINATION_PAGE_NUMBER) != null) {
				pageNumber = Integer.parseInt(request.getParameter(UtilParameter.PAGINATION_PAGE_NUMBER));
			} else {
				pageNumber = (int) session.getAttribute(UtilParameter.PAGINATION_PAGE_NUMBER);
			}
			session.setAttribute(UtilParameter.PAGINATION_PAGE_NUMBER, pageNumber);
			session.setAttribute(UtilParameter.PAGINATION_SIZE_FROM_SESSION, firstPagination);
			listNewsForPagination = newsService.getNewsListOne(pageNumber, firstPagination);
			dbRowCount = (int) newsService.totalPages(firstPagination);
			paginationSizeForPage = (int) Math.ceil((double) dbRowCount / firstPagination);
			session.setAttribute(UtilParameter.PAGINATION_SIZE_FOR_PAGE, paginationSizeForPage);
			session.setAttribute(ControllerAttribute.ATTRIBUTE_LIST_NEWS, listNewsForPagination);
			theModel.addAttribute(ControllerAttribute.ATTRIBUTE_LIST_NEWS, listNewsForPagination);
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_7);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER_7);
		}
		return "basePage";
	}

	@Transactional
	@RequestMapping("/goToEditNews")
	public String goToEditNews(HttpSession session, Model theModel,
			@ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.MODEL_USER) User user,
			@SessionAttribute(value = ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD, required = false) News newsForEdit) {
		try {
			session.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_EDIT_NEWS);
			newsForEdit = newsService.getNews(news.getNewsId());
			session.setAttribute(ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD, newsForEdit);
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_9);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER_9);
		}
		return "basePage";
	}

	@Transactional
	@PostMapping("/editNews")
	public String editNews(Model theModel, HttpSession session,
			@ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.MODEL_USER) User user,
			@SessionAttribute(value = ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD, required = false) News newsForEdit) {
		try {
			session.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_LIST);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			news.setNewsId(newsForEdit.getNewsId());
			user.setLogin(username);
			news.setNewsDate(new Timestamp(System.currentTimeMillis()));
			news.setUser(user);
			newsService.addNews(news, user);
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_6);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_6);
		}
		return "redirect:/newsManagement/newsList";
	}

	@Transactional
	@RequestMapping("/goToAddNews")
	public String goToAddNews(HttpSession session, @ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD) News newsForEdit) {
		session.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_ADD_NEWS);
		newsForEdit.setNewsId(0);
		session.setAttribute(ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD, newsForEdit);
		return "basePage";
	}

	@Transactional
	@PostMapping("/addNews")
	public String addNews(HttpSession session, @ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@ModelAttribute(ControllerAttribute.MODEL_USER) User user) {
		try {
			session.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_LIST);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			user.setLogin(username);
			news.setNewsDate(new Timestamp(System.currentTimeMillis()));
			news.setUser(user);
			newsService.addNews(news, user);
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_5);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_5);
		}
		return "redirect:/newsManagement/newsList";
	}

	@Transactional
	@RequestMapping("/goToViewNews")
	public String goToViewNews(Model theModel, HttpSession session,
			@ModelAttribute(ControllerAttribute.MODEL_NEWS) News news,
			@SessionAttribute(value = ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD, required = false) News newsForEdit) {
		try {
			session.setAttribute(ControllerAttribute.PRESENTATION, ControllerAttribute.PRESENTATION_NEWS_VIEW_NEWS);
			newsForEdit = newsService.getNews(news.getNewsId());
			session.setAttribute(ControllerAttribute.ATTRIBUTE_NEWS_FOR_EDIT_OR_ADD, newsForEdit);
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_9);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_9);
		}
		return "basePage";
	}

	@Transactional
	@PostMapping("/deleteNews")
	public String deleteNews(Model theModel, HttpSession session,
			@ModelAttribute(ControllerAttribute.MODEL_NEWS) News news) {
		try {
			int newsId = news.getNewsId();
			newsService.deleteNews(newsId);
		} catch (NewsServiceException e) {
			session.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_2);
			return "redirect:error";
		} finally {
			log.log(Level.ERROR, ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_2);
		}
		return "redirect:/newsManagement/newsList";
	}
}
