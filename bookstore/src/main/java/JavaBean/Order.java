package JavaBean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	/**
	 * createOrder
	 * 1.生成订单 <--- Basket
	 * 2.生成订单详情 <--- BasketItem
	 * 3.更改相应book的库存和销量
	 * 4.清空购物车
	 */
	
	private String id;
	private Date orderTime;
	private int totalCount;
	private double totalAmount;
	private int state; // 0, 1, 2
	private int userId;
	
	
	public Order() {
		super();
	}

	public Order(String id, Date orderTime, int totalCount, double totalAmount, int state, int userId) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
		this.state = state;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", totalCount=" + totalCount + ", totalAmount="
				+ totalAmount + ", state=" + state + ", userId=" + userId + "]";
	}

}
