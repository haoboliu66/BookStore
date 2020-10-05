package JavaBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Basket implements Serializable{
	
	//使用Map为了保证Basket中的数据有序且不重复
	//key: bookId; value: BasketItem
	Map<String,BasketItem> map = new LinkedHashMap<String,BasketItem>();
	private int totalCount;
	private double totalAmount;
	
	/*
	 * 0.从map中单独获取所有的BasketItems
	 * 1.add book to basket
	 * 2.delete from basket
	 * 3.empty basket
	 * 4.modify the amount of BasketItem
	 */
	
	//不用数据库,所以把方法都写在bean里
	/**
	 * 1.Add book to basket
	 * 		本质: 将book添加到map里
	 * 		思路: 判断购物车中是否有该book;
	 * 			*如果买过, 数量+1; 
	 * 			*如果没买过, 1.new一个BasketItem; 2.setCount()&setBook(); 3.然后加入map 
	 * @param book
	 */
	public void addBookToBasket(Book book) {
		System.out.println("addBookToBasket in Basket bean");
		
		BasketItem basketItem = map.get(book.getId() + "");
		//判断Basket中是否有此book
		if(basketItem == null) {
			// no this book in the basket
			basketItem = new BasketItem();
			basketItem.setBook(book);
			basketItem.setCount(1);
			map.put(book.getId()+ "", basketItem);
			
		}else {
			//number + 1
			int newCount = basketItem.getCount() + 1;
			basketItem.setCount(newCount);	
		}
	}
	
	/**
	 * 2.delete from basket
	 * 		delete a key from the map
	 * @return
	 */
	public void delBasketItem(String bookId) {
		map.remove(bookId);
	}
	
	/**
	 * 3.empty basket
	 */
	public void emptyBasket() {
		map.clear();
	}
	
	/**
	 * 4.update the number of a single basketItem
	 */
	public void updateBasketItemCount(String bookId, String count) {
		//根据Id找到指定item
		BasketItem basketItem = map.get(bookId);
		//update the number
		try {
			basketItem.setCount(Integer.parseInt(count));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 0.获取map(Basket)中所有的bookItems,放在list里,便于遍历统计个数
	 * @return
	 */
	public List<BasketItem> getBasketItems(){
		return new ArrayList<>(map.values());
	}

	public Basket(Map<String, BasketItem> map, int totalCount, double totalAmount) {
		super();
		this.map = map;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
	}
	
	public Basket() {
		super();
	}

	public Map<String, BasketItem> getMap() {
		return map;
	}

	public void setMap(Map<String, BasketItem> map) {
		this.map = map;
	}

	//计算购物车总数量
	public int getTotalCount() {
		int totalCount = 0;
		List<BasketItem> basketItems = getBasketItems();
		for(BasketItem basketItem : basketItems) {
			totalCount += basketItem.getCount();
		}
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	//计算购物车总金额
	public double getTotalAmount() {
		BigDecimal tAmount = new BigDecimal("0");
//		int totalAmount = 0;
		List<BasketItem> basketItems = getBasketItems();
		for(BasketItem basketItem : basketItems) {
			tAmount = tAmount.add(new BigDecimal(basketItem.getAmount() + ""));
//			totalAmount += basketItem.getAmount();	
			
	}
		return tAmount.doubleValue();
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Basket [map=" + map + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + "]";
	}
	
	
	
	
	
	

}
