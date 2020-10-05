package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JDBCUtils {
	/*
	 * Druid version Code
	 * @throws Exception 
	 */
	private static DataSource source;
	/**
	 * ThreadLocal方法
	 * get()
	 * set()
	 * remove()
	 */
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	static {
		try {
			Properties props = new Properties();
			//路径问题待解决
			//InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("DBConfig/jdbc.properties");
			InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("/druid.properties");
			props.load(is);
			 source = DruidDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * get connection
	 * @return
	 * @throws Exception
	 */
	
	public static Connection getConnection() {
	     
		Connection conn = threadLocal.get();
		try {
			if(conn == null) {
				conn = source.getConnection();
				threadLocal.set(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     return conn;
	}
	
	public static void closeResource() {
		Connection conn = threadLocal.get();
		if(conn != null) {
			DbUtils.closeQuietly(conn);
			threadLocal.remove();
		}
	}	
	
	public static void closeResource(Connection conn) {
		/*
		 * 因为runner的query和update方法里已经封装了stms和resultset的关闭,所以
		 * 如果使用DbUtils工具类和QueryRunner结合的话, 此处只写连接关闭即可
		 */
		DbUtils.closeQuietly(conn);
	}	
	
//*******************************************************************************************	
	/*
	 * JDBC version Code
	 * @return
	 * @throws Exception
	 */
	/**
	 * get connection to DB
	 */
//    public static Connection getConnection() throws Exception {
//
//        //1.读取配置文件中的基本信息
//    	//第一个is的获取方法有问题, 第二个is无误
//        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("DBConfig/jdbc.properties");
//        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("DBConfig/jdbc.properties");
////        System.out.println("is is " + is);
//        Properties props = new Properties();
////        System.out.println("props ok");
//        props.load(is);
////        System.out.println("load ok");
//        String user = props.getProperty("user");
//        String password = props.getProperty("password");
//        String url = props.getProperty("url");
//        String driverClass = props.getProperty("driverClass");
//
//        //2.加载驱动
//        Class.forName(driverClass);
//
//        //3.获取连接
//        Connection conn = DriverManager.getConnection(url, user, password);
//        return conn;
//    }
//
	/**
	 * close Resources
	 */
    //关闭连接和Statement的操作
//    public static void closeResource(Connection conn, Statement ps){
//        if(ps != null){
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//	
//	
//    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
//        if(ps != null){
//        try {
//            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(rs != null){
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    

}
