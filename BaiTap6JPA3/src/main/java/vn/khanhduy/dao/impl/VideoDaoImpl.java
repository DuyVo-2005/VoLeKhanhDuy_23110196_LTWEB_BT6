package vn.khanhduy.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import vn.khanhduy.configs.JPAConfigs;
import vn.khanhduy.dao.AbstractDao;
import vn.khanhduy.entities.Video;

public class VideoDaoImpl extends AbstractDao<Video> {

	public VideoDaoImpl() {
		super(Video.class);
	}

	public List<Video> searchByKeyword(String keyword) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		return enma.createQuery("SELECT v FROM Video v WHERE v.user.userName LIKE :kw", Video.class)
				.setParameter("kw", "%" + keyword + "%").getResultList();
	}
}
