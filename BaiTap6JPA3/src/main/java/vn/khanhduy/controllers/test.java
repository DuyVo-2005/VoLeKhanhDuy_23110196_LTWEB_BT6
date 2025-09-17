package vn.khanhduy.controllers;

import vn.khanhduy.dao.impl.RoleDaoImpl;
import vn.khanhduy.entities.Role;

public class test {
	public static void main(String[] args) {
		RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
		Role role = roleDaoImpl.findById(1);
		System.out.println(role.getRoleId() + "\t" + role.getRoleName());
	}
}
