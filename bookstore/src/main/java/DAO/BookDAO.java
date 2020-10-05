package DAO;

import java.util.List;
import JavaBean.Book;
import JavaBean.Page;

public interface BookDAO {
	
	/*
	 * sql : select * from books
	 * get all the book, return a list
	 */
	
	public List<Book> getAllBooks();
	
	/*
	 * sql : insert into books(title, author, price, sales, stock, img_path)
	 * values (?,?,?,?,?,?);
	 */
	public void addBook(Book book);
	
	
	/*
	 * sql: delete from books where 
	 */
	public void delBookById(String bookId);
	
	
	/* 修改book
	 * 	1.通过id获取book信息
	 * 	2.updateBook
	 */
	public Book getBookById(String id);
	
	public void updateBook(Book book);
	
	public void updateBook(int stock, int sales, int id);
	
	public void updateBook(Object[][] params);
	
	public Page<Book> getBooksByPage(Page<Book> page);
	
	/**
	 * 带价格区间的分页查询
	 * @param page
	 * @param min
	 * @param max
	 * @return
	 */
	public Page<Book> getBooksByPageAndPrice(Page<Book> page, double min, double max);
	
	
	
}
