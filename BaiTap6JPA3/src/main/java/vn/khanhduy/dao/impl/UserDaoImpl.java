package vn.khanhduy.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import vn.khanhduy.configs.JPAConfigs;
import vn.khanhduy.dao.AbstractDao;
import vn.khanhduy.entities.User;

public class UserDaoImpl extends AbstractDao<User> {
	public UserDaoImpl() {
		super(User.class);
	}

	public static User findByUsername(String username) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		try {
			return enma.createQuery("select u from User u where u.userName = :username", User.class)
					.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			enma.close();
		}
	}

	public List<User> searchByUsername(String username) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		return enma.createQuery("SELECT u FROM User u WHERE u.userName LIKE :un", User.class)
				.setParameter("un", "%" + username + "%").getResultList();
	}
}
