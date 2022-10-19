package by.htp.spr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.spr.entity.News;
import by.htp.spr.entity.User;

@Repository
public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getNews() throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			Query<News> theQuery = currentSession.createQuery("from News order by newsDate DESC", News.class);
			List<News> news = theQuery.getResultList();
			return news;
		} catch (HibernateException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public void addNews(News news, User user) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			news.setUser(user);
			currentSession.saveOrUpdate(news);
		} catch (HibernateException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public News getNews(int newsId) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			News news = currentSession.get(News.class, newsId);
			return news;
		} catch (HibernateException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public void deleteNews(int newsId) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			Query<?> theQuery = currentSession.createQuery("delete from News where newsId=:newsId");
			theQuery.setParameter("newsId", newsId);
			theQuery.executeUpdate();
		} catch (HibernateException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public List<News> getNewsListOne(int pageNumber, int pageSize) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			Query<News> query = currentSession.createQuery("from News", News.class);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			List<News> newsListPagination = query.getResultList();
			return newsListPagination;
		} catch (HibernateException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public long totalPages(int pageSize) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria c = currentSession.createCriteria(News.class);
			c.setFirstResult(0);
			c.setMaxResults(pageSize);
			c.setProjection(Projections.rowCount());
			Long result = (Long) c.uniqueResult();
			return result;
		} catch (HibernateException e) {
			throw new NewsDAOException(e);
		}
	}
}
