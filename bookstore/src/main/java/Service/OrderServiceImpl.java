package Service;

import java.util.Date;
import java.util.List;

import DAO.BookDAO;
import DAO.BookDAOImpl;
import DAO.OrderDAO;
import DAO.OrderDAOImpl;
import DAO.OrderItemDAO;
import DAO.OrderItemDAOImpl;
import JavaBean.Basket;
import JavaBean.BasketItem;
import JavaBean.Book;
import JavaBean.Order;
import JavaBean.OrderItem;
import JavaBean.User;

public class OrderServiceImpl implements OrderService{
	
	private OrderDAO orderDAO = new OrderDAOImpl();
	private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();
	/**
	 * createOrder
	 * 	1.生成订单 <--- Basket
	 * 	2.生成订单详情 <--- BasketItem
	 * 	3.更改相应book的库存和销量
	 * 	4.清空购物车
	 * @param basket
	 */
	
	
	
	/**
	 * 批处理优化结账
	 * 	1.BaseDAO: 添加BatchUpdate()
	 * 		*queryRunner.batch(conn,sql,params);
	 * 		*params是一个二维数组Object[][]
	 * 			*一维:执行次数
	 * 			*二维:sql参数
	 * 	2.OrderItemDAO添加批处理接口
	 * 	3.BookDAO添加批处理接口
	 * 	4.OrderServiceImpl调用DAO
	 */
	
	/**
	 * 使用事务优化结账
	 * 	*开启事务:conn.setAutoCommit(false) | commit() 或 rollback()
	 * 		1.共用同一个conn
	 * 			*ThreadLocal管理Connection ---->将Connection放入一个线程中,都从一个线程中取
	 * 			*删除BaseDAO中的 关闭Connection 操作
	 * 		2.统一异常处理(使用Filter)
	 * 			*抛出BaseDAO和BaseServlet中的异常,统一在Filter中处理
	 * 			*统一开启事务, 提交或回滚
	 */
	@Override
	public String createOrder(Basket basket, User user) {
		//1.create order
		//orderId = 时间戳(System.currentTimeMillis方法) + userId
		
		if(user == null) { // user could be null
			
		}
		String orderId = System.currentTimeMillis() + "" + user.getId();
		orderDAO.insertOrder(new Order(orderId, new Date(), basket.getTotalCount(),
				basket.getTotalAmount(),0,user.getId()));
		//2.生成订单详情
		//获取所有购物项
		List<BasketItem> basketItems = basket.getBasketItems();
		//二维数组参数
		Object[][] orderItemParams = new Object[basketItems.size()][];
		Object[][] bookParams = new Object[basketItems.size()][];
		//遍历购物项,添加到订单详情
		for(int i = 0; i < basketItems.size(); i++) {
			//2.生成订单详情
			BasketItem basketItem = basketItems.get(i);
			Book book = basketItem.getBook();
//			orderItemDAO.insertOrderItem(new OrderItem(null, basketItem.getCount(),basketItem.getAmount(),
//					book.getTitle(),book.getAuthor(),book.getPrice(),
//					book.getImgPath(),orderId));
			//二维数组第二个参数赋值
			orderItemParams[i] = new Object[] {basketItem.getCount(),basketItem.getAmount(),
					book.getTitle(),book.getAuthor(),book.getPrice(),
					book.getImgPath(),orderId};
			
			//3.更改相应book的库存和销量  ---> 引入BookDAO
			int count = basketItems.get(i).getCount(); // the number of the book sold
			Integer sales = book.getSales() + count; // new sales
			Integer stock = book.getStock() - count; // new stock
			bookParams[i] = new Object[] {stock,sales,book.getId()};
//			bookDAO.updateBook(stock, sales, book.getId());
		}
		System.out.println("get out the for loop");
		orderItemDAO.insertOrderItem(orderItemParams);
		bookDAO.updateBook(bookParams);
		
		//4.清空购物车
		basket.emptyBasket();

		return orderId;
	}
	
	
	
	

}
