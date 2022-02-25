package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.UserDAO;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.services.UserServices;

public class UserServiceTestSuite {
    UserServices sut;
	UserDAO mockUserDAO;

    @Before
	public void testPrep() {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserServices(mockUserDAO);
    }

    @Test
	public void test_addUser() {
		
		User testUser = new User(1, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser2 = new User(2, 1, "user2", "pwrd", "email1", "fname", "lname", "1", "valid");
        User testUser3 = new User(3, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser4 = new User(4, 1, "user3", "pwrd", "email2", "fname", "lname", "1", "valid");
        
        List<User> testList;
        testList.add(testUser);
        testList.add(testUser2);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        boolean testResult = sut.addUser(testUser3);
        boolean testResult2 = sut.addUser(testUser4);
		
		Assert.assertFalse(testResult);
        Assert.assertTrue(testResult2);
    }

    @Test
	public void test_getAllUsers() {
		
		User testUser = new User(1, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser2 = new User(2, 1, "user2", "pwrd", "email1", "fname", "lname", "1", "valid");
        User testUser3 = new User(3, 1, "user3", "pwrd", "email", "fname", "lname", "1", "valid");
        
        List<User> testList;
        testList.add(testUser);
        testList.add(testUser2);
        testList.add(testUser3);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        List<User> resultList = sut.getAllUsers();

        Assert.assertNotNull(resultList);
    }

    @Test
	public void test_getUserById() {
		
		User testUser = new User(1, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser2 = new User(2, 1, "user2", "pwrd", "email1", "fname", "lname", "1", "valid");
        User testUser3 = new User(3, 1, "user3", "pwrd", "email", "fname", "lname", "1", "valid");
        
        when(mockUserDAO.getUserById(1)).thenResult(testUser);

        User resultUser = sut.getUserById(1);
        User resultUser2 = sut.getUserById(5);

        Assert.assertNotNull(resultUser);
        Assert.assertNull(resultUser2);
    }

    @Test
	public void test_UserEmailValid() {
		
		User testUser = new User(1, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser2 = new User(2, 1, "user2", "pwrd", "email1", "fname", "lname", "1", "valid");
        User testUser3 = new User(3, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser4 = new User(4, 1, "user3", "pwrd", "email2", "fname", "lname", "1", "valid");
        
        List<User> testList;
        testList.add(testUser);
        testList.add(testUser2);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        boolean testResult = sut.isUserEmailValid(testUser3);
        boolean testResult2 = sut.isUserEmailValid(testUser4);
		
		Assert.assertFalse(testResult);
        Assert.assertTrue(testResult2);
    }

    @Test
	public void test_isUserUsernameValid() {
		
		User testUser = new User(1, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser2 = new User(2, 1, "user2", "pwrd", "email1", "fname", "lname", "1", "valid");
        User testUser3 = new User(3, 1, "user", "pwrd", "email", "fname", "lname", "1", "valid");
        User testUser4 = new User(4, 1, "user3", "pwrd", "email2", "fname", "lname", "1", "valid");
        
        List<User> testList;
        testList.add(testUser);
        testList.add(testUser2);

		when(mockUserDAO.getAllUsers()).thenReturn(testList);

        boolean testResult = sut.isUserUsernameValid(testUser3);
        boolean testResult2 = sut.isUserUsernameValid(testUser4);
		
		Assert.assertFalse(testResult);
        Assert.assertTrue(testResult2);
    }
}
