package Service;

import java.util.List;

import DAO.BookDAO;
import DAO.BookDAOImpl;
import JavaBean.Book;
import JavaBean.Page;

public class BookServiceImpl implements BookService{
	
	private BookDAO bookDAO = new BookDAOImpl();

	@Override
	public List<Book> getBookList() {
		
		return bookDAO.getAllBooks();
	}

	@Override
	public void addBook(Book book) {
		bookDAO.addBook(book);	
	}

	/*
	 * delete book by Id
	 */
	@Override
	public void delBookById(String bookId) {
		bookDAO.delBookById(bookId);
	}

	@Override
	public Book getBookById(String bookId) {
		return bookDAO.getBookById(bookId);
	}

	@Override
	public void updateBook(Book book) {
		
		bookDAO.updateBook(book);
		
	}

	@Override
	public Page<Book> getBooksByPage(String pageNo) {
		Page<Book> page = new Page<Book>();
		int pNo = 1;  // Default pageNo
		//处理pageNo不能小于0
//		if("0".equals(pageNo)) {
//			pageNo = "1";
//		}
		if(pageNo != null){
			pNo = Integer.parseInt(pageNo);
		}
		page.setPageNo(pNo);
		return bookDAO.getBooksByPage(page);
	}

	@Override
	public Page<Book> getBooksByPageAndPrice(String pageNo, String min, String max) {
		Page<Book> page = new Page<Book>();
		int pNo = 1;  // Default pageNo
		
		try {
			pNo = Integer.parseInt(pageNo);
			
		} catch (NumberFormatException e) {
		}
		
		page.setPageNo(pNo);
		
		//指定min和max
//		double minEnd = "".equals(min)? 0: Double.parseDouble(min); 处理方法太单一,只能处理空字符的情况
//		double maxEnd = "".equals(max)? Double.MAX_VALUE: Double.parseDouble(max);
		
		double minEnd = 0;
		double maxEnd = Double.MAX_VALUE;
		
		try {
			minEnd = Double.parseDouble(min);
			maxEnd = Double.parseDouble(max);
			//交换大小
			if(minEnd > maxEnd) {
				minEnd = minEnd + maxEnd;
				maxEnd = minEnd - maxEnd;
				minEnd = minEnd - maxEnd;
			}
		} catch (NumberFormatException e) {
		}
		
		return bookDAO.getBooksByPageAndPrice(page, minEnd, maxEnd);
	}
	

	
	
	
	
	

	
	
	
}
