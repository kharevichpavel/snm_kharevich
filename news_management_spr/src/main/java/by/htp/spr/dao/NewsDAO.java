package by.htp.spr.dao;

import java.util.List;

import by.htp.spr.entity.News;
import by.htp.spr.entity.User;

public interface NewsDAO {

	public List<News> getNews() throws NewsDAOException;

	public void addNews(News news, User user) throws NewsDAOException;

	public News getNews(int newsId) throws NewsDAOException;

	public void deleteNews(int newsId) throws NewsDAOException;

	public List<News> getNewsListOne(int pageNumber, int pageSize) throws NewsDAOException;

	long totalPages(int pageSize) throws NewsDAOException;

}
