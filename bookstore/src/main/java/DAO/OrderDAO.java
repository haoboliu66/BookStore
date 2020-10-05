package DAO;

import JavaBean.Order;

public interface OrderDAO {
	
	/**
	 * create an order(insert into orders table)
	 * @param order
	 */
	void insertOrder(Order order);
	
	

}
