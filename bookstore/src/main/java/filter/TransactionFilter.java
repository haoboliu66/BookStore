package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.JDBCUtils;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter extends HttpFilter implements Filter {

	/**
	 * TransactionFilter的作用:
	 * 	1.统一处理异常
	 * 	2.统一处理事务,管理connection
	 */
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			//放行
			chain.doFilter(request, response);
			//无异常
			conn.commit();
		} catch (Exception e) { 
			/*
			 * 由于BaseDAO和BaseServlet抛出的都是RuntimeException,
			 * 所以此处catch的异常不能小于RuntimeException
			 */
			//有异常发生
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			//发生异常回滚后,跳转error页面提醒用户(项目中正常应该记入log)
			response.sendRedirect(request.getContextPath() + "/pages/error/transaction_error.jsp");
		}finally {
			//释放connection
			JDBCUtils.closeResource();
		}
		
		
	}

    

}
