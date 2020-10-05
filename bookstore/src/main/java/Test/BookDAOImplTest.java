package Test;

import java.util.List;

import org.junit.Test;

import DAO.BookDAOImpl;
import JavaBean.Book;

public class BookDAOImplTest {
	BookDAOImpl bookDAO = new BookDAOImpl();
	
	@Test
	public void testAddBook() {
		bookDAO.addBook(new Book(null,"testTitle","testAuthor",100,200,250));
		
	}
	
	@Test
	public void testSingle() {
		String sql = "select count(*) from books;";
		Object a = bookDAO.getSingleValue(sql);
		System.out.println(a);
	}
	
	
	

}
