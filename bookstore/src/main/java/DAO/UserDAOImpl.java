package DAO;

import JavaBean.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO{

	@Override
	public User getUser(User user) {
		 
		String sql = "select id, username userName, password, email from users "
				+ "where username = ? and password = ?;";
		return getBean(sql, user.getUserName(),user.getPassword());
		
	}

	// if user exists return true; else return false;
	@Override
	public boolean checkUserName(User user) {
		String sql = "select id, username userName, password, email from users "
				+ "where username = ? ;";
		User user1 = getBean(sql, user.getUserName());
		
		if(user1 == null) return true;  //user doesn't exist
		else return false;
	}
	
	public boolean checkUserName(String username) {
		String sql = "select id, username userName, password, email from users "
				+ "where username = ? ;";
		User user = getBean(sql, username);
		
		return user != null;  //user exists return true;
		
		
	}

	
	@Override
	public void saveUser(User user) {
		
		String sql = "insert into users (username, password, email) "
				+ "values(?,?,?)";
		try {
			update(sql, user.getUserName(), user.getPassword(), user.getEmail());
		} catch (Exception e) {
			System.out.println("Duplicate username");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
