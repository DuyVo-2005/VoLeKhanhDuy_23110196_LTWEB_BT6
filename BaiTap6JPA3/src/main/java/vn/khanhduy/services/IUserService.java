package vn.khanhduy.services;

import vn.khanhduy.entities.User;

public interface IUserService {
	User login(String username, String password);

	User findByUsername(String username);

	boolean register(String usernamwe, String password, String email, String phone);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
}
