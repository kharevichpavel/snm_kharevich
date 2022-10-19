package by.htp.spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.spr.dao.UserDAO;
import by.htp.spr.dao.UserDAOException;
import by.htp.spr.entity.Role;
import by.htp.spr.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public boolean registration(User user, Role role) throws UserServiceException {
		try {
			if (userDAO.registration(user, role)) {
				return true;
			}
			return false;
		} catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	@Override
	@Transactional
	public int takeUserId(int userId) throws UserServiceException {
		try {
			userDAO.takeUserId(userId);
		} catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
		return userId;
	}
}
