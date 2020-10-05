package DAO;

import JavaBean.User;

public interface UserDAO {
	
	User getUser(User user);
	
	//check if the user already exists;
	boolean checkUserName(User user);
	
	boolean checkUserName(String username);
	
	//insert new user info into database;
	void saveUser(User user);

}
