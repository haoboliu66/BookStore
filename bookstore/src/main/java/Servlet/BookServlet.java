package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.junit.Test;

import JavaBean.Book;
import JavaBean.Page;
import Service.BookService;
import Service.BookServiceImpl;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
      
	private BookService bookService = new BookServiceImpl();
	
	protected void getAllBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//取值
		
		//调用Service中相应方法
		List<Book> books = bookService.getBookList();
		
		//跳转(数据需要的话就放在域中)book_manager.jsp
		request.setAttribute("books", books);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);;
	}
	
	protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("inininin");
		//取值
		String pageNo = request.getParameter("pageNo");
		
		//第一次请求的时候,pageNo是空,传到service层的Integer.parseInt(pageNo)会报错,所以需要在service层进行处理
		
		//调用Service
		Page<Book> page = bookService.getBooksByPage(pageNo);
//		System.out.println("page is " + page);
		
		//跳转(数据需要的话就放在request域中)
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}
	
	protected void delBookById(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//取值
		String bookId = request.getParameter("bookId");
		
		//调用Service
		bookService.delBookById(bookId);
		
		//跳转
		getAllBooks(request, response);
	}
	
	protected void getBookById(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//取值
		String bookId = request.getParameter("bookId");
		
		//调用Service
		Book book = bookService.getBookById(bookId);
		request.setAttribute("book", book);
		//跳转
		request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request, response);
		
	}
	
//	protected void addBook(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException{
//		//取值
//		String title = request.getParameter("title");
//		double price = Double.parseDouble(request.getParameter("price"));
//		String author = request.getParameter("author");
//		Integer sales = Integer.parseInt(request.getParameter("sales"));
//		Integer stock = Integer.parseInt(request.getParameter("stock"));
//		//调用Service
//		bookService.addBook(new Book(null,title,author,price,sales,stock));
//		//跳转之前要重新查询一次数据,否则新加的数据不会显示
//		getAllBooks(request,response);	
//	}
	
	protected void updateBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//取值
		String id = request.getParameter("bookId");
		String title = request.getParameter("title");
		String price = request.getParameter("price");
		String author = request.getParameter("author");
		String sales = request.getParameter("sales");
		String stock = request.getParameter("stock");
		
		//判断bookId是否为空,如果是空,就是add,非空是update
//		System.out.println("id is " + id);
		if(id == null || "".equals(id + "")) {
			//add new book
			//调用Service
			bookService.addBook(new Book(null,title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock)));
			System.out.println("1234add new");
			//跳转之前要重新查询一次数据,否则新加的数据不会显示
		}else {
			//update book
			//调用Service
			Book newBook = new Book(Integer.parseInt(id), title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
			bookService.updateBook(newBook);
		}
		//跳转
//		request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request, response);
//		response.sendRedirect(request.getContextPath()+"/BookServlet?method=getAllBooks");
		getAllBooks(request, response);
	}
	
	
	

}
