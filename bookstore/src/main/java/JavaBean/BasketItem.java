package JavaBean;

import java.io.Serializable;
import java.math.BigDecimal;

import JavaBean.Book;

public class BasketItem implements Serializable{
	
	private Book book;
	private int count;
	private double amount; // amount = book.price * count
	
	public BasketItem() {
		super();
	}

	public BasketItem(Book book, int count, double amount) {
		super();
		this.book = book;
		this.count = count;
		this.amount = amount;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * calculate the amount of one single item
	 * @return
	 */
	public double getAmount() {
		BigDecimal price = new BigDecimal(book.getPrice() + "");
		BigDecimal c = new BigDecimal(count + "");
		return (price.multiply(c)).doubleValue();
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BasketItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
	}
	
	
	
	

}
