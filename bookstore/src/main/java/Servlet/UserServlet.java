package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaBean.User;
import Service.UserService;
import Service.UserServiceImpl;


public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		// retrieve data from DB to check if use information exists
		User user = userService.getUser(new User(null, username, password,null));
		
		if(user == null) {
			// Fail 
			//标记, 在域中存放数据, 使用request域对象功能传递数据
			
			request.setAttribute("msg", "Incorrect username or password");
			request.getRequestDispatcher("/pages/usermanage/login.jsp").forward(request,response);
		}else {
			session.setAttribute("user", user);
			//Succeed  redirect
			System.out.println("用户登录跳转");
			response.sendRedirect(request.getContextPath() + "/pages/usermanage/login_success.jsp");
		}
		
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm");
		String email = request.getParameter("email");
		//获取验证码
		String code = request.getParameter("code");
		Object code1 = session.getAttribute("KAPTCHA_SESSION_KEY");
		boolean isExist = userService.checkUserName(username);
		if(code1 != null && code1.toString().equals(code)) {
			
		if(isExist) {
			
			request.setAttribute("msg", "User Already Exists");
			request.getRequestDispatcher("/pages/usermanage/register.jsp").forward(request, response);
			
		}else {
			if(!password.equals(confirmPassword)) {
				request.setAttribute("msg", "not same password");
				request.getRequestDispatcher("/pages/usermanage/register.jsp").forward(request, response);
			}else {
				userService.saveUser(new User(null, username, password, email));
				response.sendRedirect(request.getContextPath() + "/pages/usermanage/register_success.jsp");
			}
		}
	}else {
		//the verification code is incorrect
		request.setAttribute("msg", "invalid verification code");
		request.getRequestDispatcher("/pages/usermanage/register.jsp").forward(request, response);
	}
	
}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//remove user from session
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		//redirect to index.jsp
		response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
	}

	protected void checkUserName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//取值
		String username = request.getParameter("uname");
		//调用
		boolean isExist = userService.checkUserName(username);
		//相应数据(回调函数)
		PrintWriter writer = response.getWriter();
//		if(isExist) {
//			writer.write("username already exists");
//		}else {
//			writer.write("username okay");
//		}
		/*
		 * 优化,直接传判断结果,然后用jQuery判断,并根据结果赋予不同css
		 */
		writer.print(isExist);
		
	}



}
