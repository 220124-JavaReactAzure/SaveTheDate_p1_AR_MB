package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.RoleDAO;
import com.revature.saveTheDate.models.Role;

public class RoleServices {


	private final RoleDAO roleDAO;

	public RoleServices(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public boolean addRole(Role role) {

		return roleDAO.addRole(role);
	}

	public List<Role> getAllRole() {
		return roleDAO.getAllRole();

	}

	public Role getRoleById(int id) {

		return roleDAO.getRoleById(id);
	}

	public void updateRoleWithSessionMethod(Role role) {

		roleDAO.updateRoleWithSessionMethod(role);
	}
		
	public void deleteRole (int id) {
		roleDAO.deleteRole(id);
	}

}
