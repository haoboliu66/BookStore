package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.Book;
import JavaBean.Page;
import Service.BookService;
import Service.BookServiceImpl;

/**
 * Servlet implementation class BookClientServlet
 * 处理客户端请求
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImpl();
	
//	查询结果正确,但是分页还有问题,待解决
	protected void getBooksByPageAndPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取值 pageNo, min, max
		String pageNo = request.getParameter("pageNo");
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		
		/*
		 * 需要考虑min, max为空的情况,交给service层处理
		 */
		//调用
		Page<Book> page = bookService.getBooksByPageAndPrice(pageNo, min, max);
		
		//跳转
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
		
	}
		
	/**
	 * 客户端的分页查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取值
		String pageNo = request.getParameter("pageNo");
//		System.out.println(pageNo);
		//调用
		Page<Book> page = bookService.getBooksByPage(pageNo);
		request.setAttribute("page", page);
		//跳转
//		System.out.println("from index and dispatch");
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);

	}

}
