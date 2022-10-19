package by.htp.spr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.spr.dao.NewsDAO;
import by.htp.spr.dao.NewsDAOException;
import by.htp.spr.entity.News;
import by.htp.spr.entity.User;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Override
	@Transactional
	public List<News> getNews() throws NewsServiceException {
		List<News> news = null;
		try {
			news = newsDAO.getNews();
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		return news;
	}

	@Override
	@Transactional
	public void addNews(News news, User user) throws NewsServiceException {
		try {
			newsDAO.addNews(news, user);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
	}

	@Override
	@Transactional
	public News getNews(int newsId) throws NewsServiceException {
		News news = null;
		try {
			news = newsDAO.getNews(newsId);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		return news;
	}

	@Override
	@Transactional
	public void deleteNews(int newsId) throws NewsServiceException {
		try {
			newsDAO.deleteNews(newsId);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
	}

	@Override
	public List<News> getNewsListOne(int pageNumber, int pageSize) throws NewsServiceException {
		List<News> news = null;
		try {
			news = newsDAO.getNewsListOne(pageNumber, pageSize);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		return news;
	}

	@Override
	public long totalPages(int pageSize) throws NewsServiceException {
		long result = 0;
		try {
			result = newsDAO.totalPages(pageSize);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		return result;
	}
}
