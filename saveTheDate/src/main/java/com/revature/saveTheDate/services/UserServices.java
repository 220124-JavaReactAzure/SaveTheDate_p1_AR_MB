package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.UserDAO;
import com.revature.saveTheDate.models.Attendee;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.models.Wedding;

public class UserServices {
	
private final UserDAO userDAO;
	
	// DI - Dependency Injection of the DAO
	public UserServices(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean addUser(User newUser) {
		if (isUserEmailValid(newUser) && isUserUsernameValid(newUser)){
			return userDAO.addUser(newUser);
		}
		return false;	
	}
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();

	}
	
	public User getUserById(int id) {

		return userDAO.getUserById(id);
	}
	

//	public User authenticateUser(String username, String password) {
//		
//		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
//			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
//		}
//		
//		User authenticatedUser = userDao.findByUsernameAndPassword(username, password);
//		
//		if(authenticatedUser == null) {
//			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
//		}
//		return authenticatedUser;
//	}


	public boolean isUserEmailValid(User newUser) {
		for (User oldUser : getAllUsers()){
			if (oldUser.getEmail().equalsIgnoreCase(newUser.getEmail()))
			return false;
		}
		return true;
	}

	public boolean isUserUsernameValid(User newUser) {
		for (User oldUser : getAllUsers()){
			if (oldUser.getUsername().equalsIgnoreCase(newUser.getUsername()))
			return false;
		}
		return true;
	}
	
	public void updateUserWithSessionMethod(User user) {

		userDAO.updateUserWithSessionMethod(user);
	}
	
}

