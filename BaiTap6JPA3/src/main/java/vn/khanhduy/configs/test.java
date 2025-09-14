package vn.khanhduy.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfigs.CreateEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}
}
