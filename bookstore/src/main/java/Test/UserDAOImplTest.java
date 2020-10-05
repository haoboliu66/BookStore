package Test;

import DAO.UserDAOImpl;
import JavaBean.User;

public class UserDAOImplTest {
	
	public static void main(String[] args) {
		
		UserDAOImpl user = new UserDAOImpl();
		User user2 = user.getUser(new User(null,"test01","test01",null));
		System.out.println(user2);
		boolean bool = user.checkUserName("test011");
		System.out.println(bool);
		
	}

}
