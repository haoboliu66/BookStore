package DAO;

import JavaBean.OrderItem;

public interface OrderItemDAO {
	
	/**
	 * insert a new orderItem
	 * @param orderItem
	 */
	void insertOrderItem(OrderItem orderItem);
	
	public void insertOrderItem(Object[][] params);

}
