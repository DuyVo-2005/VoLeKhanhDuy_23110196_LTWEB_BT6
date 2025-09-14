package vn.khanhduy.services.impl;

import vn.khanhduy.services.IUserService;
import jakarta.inject.Inject;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.entities.User;

public class UserServiceImpl implements IUserService {
	
	@Inject
	private UserDaoImpl UserDaoImpl;

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

	@Override
	public boolean register(String username, String password, String email, String phone) {
		if (UserDaoImpl.checkExistUsername(username)) {
			return false;
		}
		
		IRoleDao roleDao = new RoleDaoImpl();
		Roles role = roleDao.findById(1);
		UserDaoImpl.insert(new Users(username, password, email, phone, role));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return UserDaoImpl.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return UserDaoImpl.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return UserDaoImpl.checkExistPhone(phone);
	}
}
