package by.htp.spr.dao;

import by.htp.spr.entity.Role;
import by.htp.spr.entity.User;

public interface UserDAO {

	boolean registration(User user, Role role) throws UserDAOException;

	int takeUserId(int userId) throws UserDAOException;
}
