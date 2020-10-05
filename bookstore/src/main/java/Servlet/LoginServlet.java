package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import JavaBean.User;
import Service.UserService;
import Service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//		UserDAO userDAO = new UserDAOImpl();
		UserService userService = new UserServiceImpl();
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		
		User user = userService.getUser(new User(null, username, password,null));
		System.out.println(user);
		
		if(user == null) {
			// Fail 
			//标记, 在域中存放数据, 使用request域对象功能传递数据
			
			request.setAttribute("msg", "Incorrect username or password");
			request.getRequestDispatcher("/pages/usermanage/login.jsp").forward(request,response);
		}else {
			//Succeed  redirect
			response.sendRedirect(request.getContextPath() + "/pages/usermanage/login_success.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
