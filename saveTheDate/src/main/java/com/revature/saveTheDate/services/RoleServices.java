package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.RoleDAO;
import com.revature.saveTheDate.models.Role;

public class RoleServices {

private final RoleDAO roleDAO;
	//private final Logger logger = LogManager.getLogger();

	public RoleServices(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
		//logger.info("RoleServices created");
	}

	public boolean addRole(Role role) {
		//logger.info("RoleServices.addRole called for Role: " + role);
		return roleDAO.addRole(role);
	}

	public List<Role> getAllRoles() {
		//logger.info("RoleServices.getAllRoles called");
		return roleDAO.getAllRole();
	}

	public Role getRoleById(int id) {
		//logger.info("RoleServices.getRoleById called for id: " + id);
		return roleDAO.getRoleById(id);
	}

	public void updateRoleWithSessionMethod(Role role) {
		//logger.info("RoleServices.updateRoleWithSessionMethod called for Role: " + role);
		roleDAO.updateRoleWithSessionMethod(role);
	}
		
	public void deleteRoleById (int id) {
		//logger.info("RoleServices.deleteRoleById called for id: " + id);
		roleDAO.deleteRoleById(id);
	}


}
