package Servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//统一处理doGet和doPost方法
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		//Reflection ---> 优化动态获取方法名, 执行相应方法
		try {
			//获取当前对应method名字的方法的对象
			Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			//再通过该方法的对象invoke该方法
			declaredMethod.invoke(this, request, response);
					
		} catch (Exception e) {
//			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
