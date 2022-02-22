package com.revature.save_the_date.services;

public class UserService {
	
private final UserDao userDao;
	
	// DI - Dependency Injection of the DAO
	public UserService(UserDAO userDAO) {
		this.userDao = userDAO;
	}
	
	public User registerNewUser(User newUser) {
		if(!isUserValid(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		boolean usernameAvailable = userDao.findByUsername(newUser.getUsername()) == null;
		boolean emailAvailable = userDao.findByEmail(newUser.getEmail()) == null;
		
		if(!usernameAvailable || !emailAvailable) {
			if(!usernameAvailable && emailAvailable) {
				throw new ResourcePersistenceException("The provided username was already taken in the database");
			} else if(usernameAvailable) {
				throw new ResourcePersistenceException("The provided email was already taken in the database");
			} else {
				throw new ResourcePersistenceException("The provided username and email were already taken in the database");
			}
		}
		
		User persistedUser = userDao.create(newUser);
		
		if(persistedUser == null) {
			throw new ResourcePersistenceException("The User could not be persisted");
		}
		
		return persistedUser;
	}
	
	public List<User> getAllUsers(){
		return userDao.findAll();	
	}
	
	//TODO: Impelement authentication
	public User authenticateUser(String username, String password) {
		
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
		}
		
		User authenticatedUser = userDao.findByUsernameAndPassword(username, password);
		
		if(authenticatedUser == null) {
			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
		}
		return authenticatedUser;
	}

	public boolean isUserValid(User newUser) {
		if(newUser == null) return false;
		if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
		if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
		if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
		if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
		return newUser.getPassword() != null && !newUser.getPassword().trim().equals("");


	}
	
}

}
