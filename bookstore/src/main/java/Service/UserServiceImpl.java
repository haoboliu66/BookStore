package Service;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import JavaBean.User;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public User getUser(User user) {
		return userDAO.getUser(user);
	}

	@Override
	public boolean checkUserName(String username) {
		
		return userDAO.checkUserName(username);
	}

	@Override
	public void saveUser(User user) {
		userDAO.saveUser(user);
		
	}

	
	

}
