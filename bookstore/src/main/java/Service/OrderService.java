package Service;

import JavaBean.Basket;
import JavaBean.User;

public interface OrderService {
	/**
	 * createOrder
	 * 1.生成订单 <--- Basket
	 * 2.生成订单详情 <--- BasketItem
	 * 3.更改相应book的库存和销量
	 * 4.清空购物车
	 * @param basket
	 */
	public String createOrder(Basket basket, User user);
	/*
	 * 由于用户在点checkout,生成订单后需要将订单Id显示给用户,所以返回值是String的orderId
	 */

	
	
}
