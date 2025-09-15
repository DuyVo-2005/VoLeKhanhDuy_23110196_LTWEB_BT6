package vn.khanhduy.services;

import vn.khanhduy.entities.User;

public interface IUserService {
	User login(String username, String password);

	User findByUsername(String username);
}
