package Service;

import JavaBean.User;

public interface UserService {
	
	User getUser(User user);
	
	boolean checkUserName(String username);
	
	void saveUser(User user);
	

}
