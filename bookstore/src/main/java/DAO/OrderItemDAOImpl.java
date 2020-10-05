package DAO;

import JavaBean.OrderItem;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {

	@Override
	public void insertOrderItem(OrderItem orderItem) {
		String sql = "INSERT INTO order_item (count, amount, title, author,"
				+ "price, img_path, order_id) values(?,?,?,?,?,?,?)";
		
		update(sql, orderItem.getCount(), orderItem.getAmount(),
				orderItem.getTitle(), orderItem.getAuthor(), orderItem.getPrice(),
				orderItem.getImgPath(), orderItem.getOrderId());
	}
	
	
	public void insertOrderItem(Object[][] params) {
		String sql = "INSERT INTO order_item (count, amount, title, author,"
				+ "price, img_path, order_id) values(?,?,?,?,?,?,?)";
		bachUpdate(sql, params);
		
	}
	

}
