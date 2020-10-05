package DAO;

import java.util.List;

import org.junit.Test;

import JavaBean.Book;
import JavaBean.Page;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
	
	
	public static void main(String[] args) {
		BookDAOImpl bookDAO = new BookDAOImpl();
		String sql = "select id,title,author,price,"
				+ "sales,stock,img_path from books where id = ?;";
		Book bean = bookDAO.getBean(sql, "10");
		System.out.println(bean);
	}
	@Test
	public void Test() {
		BookDAOImpl bookDAO = new BookDAOImpl();
		String sql = "select id,title,author,price,"
				+ "sales,stock,img_path from books where id = ?;";
		Book bean = bookDAO.getBean(sql, "30");
		System.out.println(bean);
		
	}
	
	
	

	@Override
	public List<Book> getAllBooks() {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from books";
		return getBeanList(sql);
	 
	}

	@Override
	public void addBook(Book book) {
		String sql = "insert into books (title, author, price, sales, stock, img_path)"
				+ "value(?,?,?,?,?,?)";
		update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),
				book.getStock(),book.getImgPath());
	}

	@Override
	public void delBookById(String bookId) {
		String sql = "delete from books where id = ?";
		update(sql, bookId);
		
	}

	@Override
	public Book getBookById(String id) {
		System.out.println("getBookById in BookDAOImpl");
 		String sql = "select id,title,author,price,sales,stock,img_path imgPath from books where id = ?;";
				
		return this.getBean( sql, id);
	}
	/**
	 * book_manage add new book
	 */
	@Override
	public void updateBook(Book book) {
		String sql = "update books set title = ?, author = ?, price= ?, sales = ?, stock=? where id = ?";
		
		update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(), book.getStock(), book.getId());
	}
	
	/**
	 * Update a book's stock and sales after checkout
	 */
	public void updateBook(int stock, int sales, int id) {
		String sql = "update books set stock = ?, sales = ? where id = ?";
		
		update(sql, stock,sales,id);
	}
	
	/**
	 * batch Update books after checkout
	 */
	@Override
	public void updateBook(Object[][] params) {
		String sql = "update books set stock = ?, sales = ? where id = ?";
		bachUpdate(sql, params);
	}
	
	
	/**
	 * 实现分页
	 */
	@Override
	public Page<Book> getBooksByPage(Page<Book> page) {
		//page object: pageNo, PAGE_SIZE, totalPageNo
		/*
		 * pageNo 是Servlet传过来的;
		 * PAGE_SIZE static final 静态常量
		 * totalPageNo 通过totalRecord计算可得
		 */

		//DAO: totalRecord
		String sql = "select count(*) from books;";
		int totalRecord = Integer.parseInt(getSingleValue(sql) + ""); //返回值是long类型,拼接 ""
		page.setTotalRecord(totalRecord);
		
//		处理pageNo不能大于总页数
//		if(page.getPageNo() > page.getTotalPageNo()) {
//			int limit = page.getTotalPageNo();
//			System.out.println("limit is " + limit);
//			page.setPageNo(limit);
//		}
		
		//DAO: list
		String sql2 = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM books WHERE 1=1 LIMIT ?,?";
		List<Book> list = getBeanList( sql2, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
		//set list into page object
		page.setList(list);
		
		return page;
	}

	/**
	 * 实现分页和按价格区间查询
	 */
	@Override
	public Page<Book> getBooksByPageAndPrice(Page<Book> page, double min, double max) {
		//page object: pageNo, PAGE_SIZE, totalPageNo
				/*
				 * pageNo 是Servlet传过来的;
				 * PAGE_SIZE static final 静态常量
				 * totalPageNo 通过totalRecord计算可得
				 */

				//DAO: totalRecord
				String sql = "select count(*) from books "
						+ "where 1=1 and price between ? and ?;";
				int totalRecord = Integer.parseInt(getSingleValue(sql, min, max) + ""); //返回值是long类型
				page.setTotalRecord(totalRecord);
				
				//DAO: list
				String sql2 = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM books "
						+ "WHERE 1=1 and price BETWEEN ? AND ? LIMIT ?, ?;";
				List<Book> list = getBeanList(sql2, min, max, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
				//set list into page object
				page.setList(list);
				
				return page;
	}

	
	
	
	

}
