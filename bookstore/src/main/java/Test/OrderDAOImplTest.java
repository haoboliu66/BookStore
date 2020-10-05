package Test;

import java.util.Date;

import DAO.OrderDAOImpl;
import JavaBean.Order;

public class OrderDAOImplTest {
	
	
	public static void main(String[] args) {
		
		OrderDAOImpl o = new OrderDAOImpl();
		o.insertOrder(new Order("test001", new Date(), 2,300,0,1));
		//OK
		
	}
	

}
