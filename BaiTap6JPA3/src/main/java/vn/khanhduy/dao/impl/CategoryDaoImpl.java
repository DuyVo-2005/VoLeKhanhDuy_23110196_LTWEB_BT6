package vn.khanhduy.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import vn.khanhduy.configs.JPAConfigs;
import vn.khanhduy.dao.AbstractDao;
import vn.khanhduy.entities.Category;

public class CategoryDaoImpl extends AbstractDao<Category> {

	public CategoryDaoImpl() {
		super(Category.class);
	}

	public List<Category> searchByCategoryName(String categoryName) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		return enma.createQuery("SELECT c FROM Category c WHERE c.categoryName LIKE :cn", Category.class)
				.setParameter("cn", "%" + categoryName + "%").getResultList();
	}
}
