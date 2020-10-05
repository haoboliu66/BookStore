package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestServlet implements Servlet{
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init!!!");
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("Service!!!");
		String username = request.getParameter("username"); //根据form的name获取
		System.out.println(username);
		String password = request.getParameter("pwd");
		System.out.println(password);
		
//		request.getRequestDispatcher("/pages/usermanage/register.html").forward(request, response);
//		
//		HttpServletRequest request1 = (HttpServletRequest) request;
//		String contextPath = request1.getContextPath();
		
		HttpServletResponse response2 = (HttpServletResponse) response;
		//response2.sendRedirect("pages/usermanage/register.html");
	

		PrintWriter writer = response2.getWriter();
		
//		InputStream is = new FileInputStream(new File("Last day in MF.jpeg"));
//		
//		ServletOutputStream os = response2.getOutputStream();
//		byte[] buffer = new byte[20];
//		int len;
//		while((len = is.read(buffer)) != -1) {
//			os.write(buffer,0,len);
//		}
//		
//		
		writer.write("偶尔也"); //直接写到响应的页面上,可以写String或者char[]
//		
//		os.close();
//		is.close();
		
	}

}

class myServlet extends HttpServlet{
	
}
