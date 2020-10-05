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


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		UserDAO userDAO = new UserDAOImpl();
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirm");
//		String email = request.getParameter("email");
//		if(! password.equals(confirmPassword)) {
//			//转发
//			System.out.println("Password not confirmed");
//			request.getRequestDispatcher("pages/usermanage/register.html").forward(request, response);
//			return;
//		}
//		User user = new User(null, username, password, email);
//		if(userDAO.checkUserName(user)) {
//			userDAO.saveUser(user);
//			System.out.println("Update user info");
//		}else {
//			System.out.println("User exists");
//			//user exists
//			response.sendRedirect(request.getContextPath() + "/pages/usermanage/register.html");
//		}
//		
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm");
		String email = request.getParameter("email");
		boolean isExist = userService.checkUserName(username);
		if(isExist) {
			
			request.setAttribute("msg", "User Already Exists");
			request.getRequestDispatcher("/pages/usermanage/register.jsp").forward(request, response);
			
		}else {
			userService.saveUser(new User(null, username, password, email));
			response.sendRedirect(request.getContextPath() + "/pages/usermanage/register_success.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
