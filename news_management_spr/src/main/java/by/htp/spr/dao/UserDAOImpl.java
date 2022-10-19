package by.htp.spr.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import by.htp.spr.entity.Role;
import by.htp.spr.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean registration(User user, Role role) throws UserDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			Query<?> query = currentSession
					.createQuery("select login from User where login ='" + user.getLogin() + "'");
			List<?> resultQuery = query.getResultList();
			for (int i = 0; i < resultQuery.size(); i++) {
				String resultLogin = (String) resultQuery.get(i);
				if (resultLogin.equals(user.getLogin())) {
					return false;
				}
			}
			role.setUser(user);
			role.setRole("GUEST");
			currentSession.save(user);
			currentSession.save(role);
			return true;
		} catch (HibernateException e) {
			throw new UserDAOException(e);
		}
	}

	@Override
	public int takeUserId(int userId) throws UserDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			User theUser = currentSession.get(User.class, userId);
			return theUser.getUserId();
		} catch (HibernateException e) {
			throw new UserDAOException(e);
		}
	}
}
