package Test;

import static org.hamcrest.CoreMatchers.nullValue;

import DAO.OrderItemDAOImpl;
import JavaBean.OrderItem;

public class OrderItemDAOImplTest {
	
	public static void main(String[] args) {
		OrderItemDAOImpl o = new OrderItemDAOImpl();
		o.insertOrderItem(new OrderItem(null, 1, 3, "testTitle","testAuthor",50,"testPath","test001"));
		
	}

}
