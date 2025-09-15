package vn.khanhduy.services.impl;

import vn.khanhduy.services.IUserService;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.entities.User;

public class UserServiceImpl implements IUserService {
	
	UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.findByUsername(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		return UserDaoImpl.findByUsername(username);
	}
}
