package Service;

import java.util.List;

import JavaBean.Book;
import JavaBean.Page;

public interface BookService {
	
	
	public List<Book> getBookList();
	
	
	public void addBook(Book book);


	public void delBookById(String bookId);
	
	public Book getBookById(String bookId);
	
	public void updateBook(Book book);
	
	public Page<Book> getBooksByPage(String pageNo);
	
	public Page<Book> getBooksByPageAndPrice(String pageNo, String min, String max);

}
