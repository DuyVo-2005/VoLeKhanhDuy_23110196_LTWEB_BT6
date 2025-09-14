package vn.khanhduy.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.khanhduy.configs.JPAConfigs;
import vn.khanhduy.dao.AbstractDao;
import vn.khanhduy.entities.User;

public class UserDaoImpl extends AbstractDao<User> {
	public UserDaoImpl(){
		super(User.class);
	}

	public User findByUsername(String username) {
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

	public boolean checkExistEmail(String email) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		try {
			Long count = enma.createQuery("select count(u) from User u where u.email = :email", Long.class)
					.setParameter("email", email)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			enma.close();
		}
	}

	public boolean checkExistUsername(String username) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		try {
			Long count = enma.createQuery("select count(u) from User u where u.userName = :username", Long.class)
					.setParameter("username", username)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;	
		} finally {
			enma.close();
		}
	}

	public boolean checkExistPhone(String phone) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Long count = enma.createQuery("select count(u) from User u where u.phone = :phone", Long.class)
					.setParameter("phone", phone)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;	
		} finally {
			enma.close();
		}
	}
}
