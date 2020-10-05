package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaBean.Basket;
import JavaBean.User;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
public class CheckLoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//判断是否登录; 登录, 放行; 未登录, 跳转到登录页面
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			request.setAttribute("msg", "Please log in first");
			//确保在登录之后购物车为空
			Basket basket = (Basket)session.getAttribute("basket");
			basket.emptyBasket();
			
			request.getRequestDispatcher("/pages/usermanage/login.jsp").forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
		
	}

	


}
