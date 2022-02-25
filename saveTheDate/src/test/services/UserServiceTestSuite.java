package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.UserDAO;
import com.revature.saveTheDate.models.Role;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.services.UserServices;

public class UserServiceTestSuite {
	Role role = new Role(1, "valid");
	
	
	
    UserServices sut;
	UserDAO mockUserDAO;

    @Before
	public void testPrep() {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserServices(mockUserDAO);
    }

    @Test
	public void test_addUser() {
		
    	User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
    	User user2 = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
    	User user3 = new User(1, role, "validUname1", "validUname1", "validEmail1", "validEmail1", "valid", "valid", "valid");
     
        
        List<User> testList = new ArrayList<User>();
        testList.add(user);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        boolean testResult = sut.addUser(user2);
        boolean testResult2 = sut.addUser(user3);
		
		Assert.assertFalse(testResult);
		Assert.assertFalse(testResult2);
     
    }

    @Test
	public void test_getAllUsers() {
		
    	User user = new User(1, role, "valid", "validUname", "valid", "validEmail", "valid", "valid", "valid");
       
        List<User> testList = new ArrayList<User>();
        testList.add(user);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        List<User> resultList = sut.getAllUsers();

        Assert.assertNotNull(resultList);
    }

    @Test
	public void test_getUserById() {
		
    	User user = new User(1, role, "valid", "validUname", "valid", "validEmail", "valid", "valid", "valid");
        
        when(mockUserDAO.getUserById(1)).thenReturn(user);

        User resultUser = sut.getUserById(1);

        Assert.assertNotNull(resultUser);
       
    }

    @Test
	public void test_UserEmailValid() {
		
    	User user = new User(1, role, "valid", "valid", "valid", "valid", "valid", "valid", "valid");
    	User user2 = new User(1, role, "valid", "valid", "valid", "valid", "valid", "valid", "valid");
    	User user3 = new User(1, role, "validUname", "valid", "validemail", "valid", "valid", "valid", "valid");
        
        List<User> testList = new ArrayList<User>();
        testList.add(user);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        boolean testResult = sut.isUserEmailValid(user2);
        boolean testResult2 = sut.isUserEmailValid(user3);
		
		Assert.assertFalse(testResult);
		Assert.assertTrue(testResult2);
       
    }

    @Test
	public void test_isUserUsernameValid() {
		
    	User user = new User(1, role, "valid", "valid", "valid", "valid", "valid", "valid", "valid");
    	User user2 = new User(1, role, "valid", "valid", "valid", "valid", "valid", "valid", "valid");
    	User user3 = new User(1, role, "validUname", "valid", "validEmail", "valid", "valid", "valid", "valid");
        
        List<User> testList = new ArrayList<User>();
        testList.add(user);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        boolean testResult = sut.isUserEmailValid(user2);
        boolean testResult2 = sut.isUserEmailValid(user3);
		
		Assert.assertFalse(testResult);
		Assert.assertTrue(testResult2);
       
    }
}

