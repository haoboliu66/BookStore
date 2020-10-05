package DAO;

import JavaBean.Order;

public class OrderDAOImpl extends BaseDAO<Order> implements OrderDAO{

	@Override
	public void insertOrder(Order order) {

		String sql = "INSERT INTO orders (id, order_time, total_count, total_amount, "
				+ "state, user_id) VALUES(?,?,?,?,?,?);";
		
		update(sql, order.getId(), order.getOrderTime(), 
				order.getTotalCount(), order.getTotalAmount(), 
				order.getState(), order.getUserId());
	}
	
	
	

}
