package com.revature.saveTheDate.test.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.RoleDAO;
import com.revature.saveTheDate.models.Role;
import com.revature.saveTheDate.services.RoleServices;

public class RoleServicesTestSuite {
	
	RoleServices sut;
	RoleDAO mockRoleDAO;
	
	Role role = new Role(1, "valid");

	    @Before
		public void testPrep() {
			mockRoleDAO = mock(RoleDAO.class);
			sut = new RoleServices(mockRoleDAO);
	    }

	    @Test
		public void test_addRole() {
			
	    boolean result = sut.addRole(role);
	    
	    Assert.assertTrue(result);
	    }

	    @Test
		public void test_getAllRoles() {
			
	    	
	        List<Role> testList = new ArrayList<Role>();
	        testList.add(role);

	        when(mockRoleDAO.getAllRole()).thenReturn(testList);

	        List<Role> resultList = sut.getAllRoles();

	        Assert.assertNotNull(resultList);
	    }

	    @Test
		public void test_getServiceTypeById() {
			
	        when(mockRoleDAO.getRoleById(1)).thenReturn(role);

	        Role result = sut.getRoleById(1);

	        Assert.assertNotNull(result);
	        
	    } 

}
