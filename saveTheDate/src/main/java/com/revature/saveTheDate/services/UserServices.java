package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.UserDAO;
import com.revature.saveTheDate.models.User;

public class UserServices {
	
private final UserDAO userDAO;
//private final Logger logger = LogManager.getLogger();
	
	// DI - Dependency Injection of the DAO
	public UserServices(UserDAO userDAO) {
		this.userDAO = userDAO;
		//logger.info("UserService was created");
	}
	
	public boolean addUser(User newUser) {
		//logger.info("UserServices.addUser was called for user: " + newUser);
		if (isUserEmailValid(newUser) && isUserUsernameValid(newUser)){
			return userDAO.addUser(newUser);
		}
		return false;	
	}
	
	public List<User> getAllUsers() {
		//logger.info("UserServices.getAllUsers was called");
		return userDAO.getAllUsers();

	}
	
	public User getUserById(int id) {
		//logger.info("UserServices.getUserById was called for id: " + id);
		return userDAO.getUserById(id);
	}
	

	// public User isUserValid(String username, String password) {
		
	// 	if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
	// 	throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
	// 	}
		
	// 	User authenticatedUser = userDao.findByUsernameAndPassword(username, password);
		
	// 	if(authenticatedUser == null) {
	// 		throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
	// 	}
	// 	return authenticatedUser;
	// }


	public boolean isUserEmailValid(User newUser) {
		//logger.info("UserServices.isUserEmailValid was called for user: " + newUser);
		for (User oldUser : getAllUsers()){
			if (oldUser.getEmail().equalsIgnoreCase(newUser.getEmail()))
			return false;
		}
		return true;
	}

	public boolean isUserUsernameValid(User newUser) {
		//logger.info("UserServices.isUsernameValid was called for user: " + newUser);
		for (User oldUser : getAllUsers()){
			if (oldUser.getUsername().equalsIgnoreCase(newUser.getUsername()))
			return false;
		}
		return true;
	}
	
	public void updateUserWithSessionMethod(User user) {
		//logger.info("UserServices.updateUderWithSessionMethod was called for user: " + user);
		userDAO.updateUserWithSessionMethod(user);
	}
	
}

