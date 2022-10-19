package by.htp.spr.service;

import java.util.List;

import by.htp.spr.entity.News;
import by.htp.spr.entity.User;

public interface NewsService {

	public List<News> getNews() throws NewsServiceException;

	public void addNews(News news, User user) throws NewsServiceException;

	public News getNews(int newsId) throws NewsServiceException;

	public void deleteNews(int newsId) throws NewsServiceException;

	public List<News> getNewsListOne(int pageNumber, int pageSize) throws NewsServiceException;

	long totalPages(int pageSize) throws NewsServiceException;

}
