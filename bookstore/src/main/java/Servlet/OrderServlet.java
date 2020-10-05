package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDAO;
import JavaBean.Basket;
import JavaBean.User;
import Service.OrderService;
import Service.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
		private OrderService orderService = new OrderServiceImpl();
	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//取值
		//基于session的购物车设计, basket就在session中
		Basket basket = (Basket)session.getAttribute("basket");
		//用户登录成功后,user也放在了session中
		User user = (User)session.getAttribute("user");
		
		//调用Service
		String orderId = orderService.createOrder(basket, user);
		session.setAttribute("orderId", orderId);
		//跳转
		response.sendRedirect(request.getContextPath() + "/pages/basket/checkout.jsp");
		
	}
	
	

}
