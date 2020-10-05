package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import JavaBean.Basket;
import JavaBean.BasketItem;
import JavaBean.Book;
import Service.BookService;
import Service.BookServiceImpl;

/**
 * Servlet implementation class BasketServlet
 */
public class BasketServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private BookService bookService = new BookServiceImpl();
//	private Basket basket = new Basket(); 如果创建对象,每次调用方法都会重新初始化Basket,不合理

    /**
     * add a book to basket
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBookToBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String bookId = request.getParameter("bookId");
        String pageNo = request.getParameter("pageNo");

        //带数据重新请求的方式用获取Referer的方法替代了
//		String min = request.getParameter("min");
//		System.out.println("min is " + min);
//		String max = request.getParameter("max");
//		System.out.println("max is " + max);

        Book book = bookService.getBookById(bookId);

        //Basket要放在session域中
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket == null) {
            basket = new Basket();
            session.setAttribute("basket", basket);
        }
        basket.addBookToBasket(book);
        //验证stock
        Integer stock = book.getStock();
        BasketItem basketItem = basket.getMap().get(book.getId() + "");
        int count = basketItem.getCount();
        if (count > stock) {
            //库存不足
            session.setAttribute("msg", "Out of Stock, Only " + stock + " Left");
            //将购买数量设置为stock
            basket.getMap().get(book.getId() + "").setCount(stock);
        } else {
            //因为通过EL sessionScope的basket无法取到title,所以还需要将title放入session
            session.setAttribute("title", book.getTitle());
        }
        //跳转
        /*
         * 为了保证加入购物车后,页面不跳转回首页,保留上一次点击加入购物车请求时的数据
         */
        //My Version: 带着数据在request中, 让BookServlet方法重新查询
        //request.getRequestDispatcher("/BookClientServlet?method=getBooksByPageAndPrice&pageNo=" + pageNo + "").forward(request, response);

        //实际更好的做法: 获取并保留HTTP header中Referer的值即可, Referer保存着上次请求的url值
        String url = request.getHeader("Referer");
        //再重定向回上次请求的url
        response.sendRedirect(url);
        /*
         * 此处不能用转发,只能用redirect,因为转发是服务器动作,路径是基于当前项目
         * request.getRequestDispatcher(url).forward(request, response);会因为路径不对找不到页面
         */
    }

    /**
     * delete an item from basket
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delBasketItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获取bookId
        String bookId = request.getParameter("bookId");
        //调用
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket != null) {
            //第一次addBookToBasket的时候,book信息就已经被加入session了,所以删除的时候只要移除可以了
            basket.delBasketItem(bookId);
        }
        //跳转
        String url = request.getHeader("Referer");
        response.sendRedirect(url);
    }


    /**
     * empty the basket
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void emptyBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Basket basket = (Basket) session.getAttribute("basket");
        if (basket != null) {
            basket.emptyBasket();
        }
        String url = request.getHeader("Referer");
        response.sendRedirect(url);
    }

    /**
     * update the number of an item
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBasketItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //取值bookId, count
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        //调用
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket != null) {
            basket.updateBasketItemCount(bookId, count);
        }
        //跳转
//		String url = request.getHeader("Referer");
//		response.sendRedirect(url);
        /*
         * Ajax回调信息: totalCount, totalAmount
         */
        double totalAmount = basket.getTotalAmount();
        int totalCount = basket.getTotalCount();
        BasketItem basketItem = basket.getMap().get(bookId);
        double amount = basketItem.getAmount();
        //封装数据也可以用GSON做
        String msg = "{\"amount\":" + amount + ",\"totalAmount\":" +
                totalAmount + ",\"totalCount\":" + totalCount + "}";
        PrintWriter writer = response.getWriter();
        writer.write(msg);

    }


}
