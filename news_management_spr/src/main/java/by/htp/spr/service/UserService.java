package by.htp.spr.service;

import by.htp.spr.entity.Role;
import by.htp.spr.entity.User;

public interface UserService {

	boolean registration(User user, Role role) throws UserServiceException;

	int takeUserId(int userId) throws UserServiceException;
}
