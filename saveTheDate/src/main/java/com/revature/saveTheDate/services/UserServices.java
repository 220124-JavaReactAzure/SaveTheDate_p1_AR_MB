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
		
		return userDAO.addUser(newUser);
	}
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();

	}
	
	public User getUserById(int id) {

		return userDAO.getUserById(id);
	}
	
	//TODO: Impelement authentication
//	public User authenticateUser(String username, String password) {
//		
//		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
//			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
//		}
//		
//		User authenticatedScientist = userDAO.findByUsernameAndPassword(username, password);
//		
//		if(authenticatedScientist == null) {
//			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
//		}
//		return authenticatedScientist;
//	}

	public boolean isUserValid(User newUser) {
		if(newUser == null) return false;
		if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
		if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
		if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
		if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
		return newUser.getPassword() != null && !newUser.getPassword().trim().equals("");
	}
	
	public void updateUserWithSessionMethod(User user) {

		userDAO.updateUserWithSessionMethod(user);
	}
	
}

