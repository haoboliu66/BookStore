package DAO;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
//import org.apache.tomcat.jdbc.pool.PoolProperties;
//import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import JavaBean.Book;
import JavaBean.Order;
import Utils.JDBCUtils;

public class BaseDAO<T> {
	
	/*
	 * DBUtils version CRUD - QueryRunner
	 */
	private QueryRunner runner = new QueryRunner();
	
	private Class<T> type;
	
	public BaseDAO() {
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) genericSuperclass;
		Type[] actualTypeArguments = paramType.getActualTypeArguments();
		
		type = (Class<T>) actualTypeArguments[0];
	}

		//update database
		public int update(String sql, Object...args) {
			
			Connection conn = JDBCUtils.getConnection();
			int count = 0;
			try {
				count = runner.update(conn, sql, args);
				
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e);
				
			}finally {
//				JDBCUtils.closeResource();
			}
			
			return count;
		}
		/**
		 * batch processing update database
		 * @param sql
		 * @param args
		 * 		args是二维数组,一是: 执行次数; 二是: 参数
		 * @return
		 */
		public void bachUpdate(String sql, Object[][] args) {
			
			Connection conn = JDBCUtils.getConnection();
			try {
				
				runner.batch(conn, sql, args);
				
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
//				JDBCUtils.closeResource();
			}
		}
			
		/**
		 * return one record对应的对象
		 * @param sql
		 * @param args
		 * @return
		 */
		public T getBean(String sql, Object...args) {
			
			Connection conn = JDBCUtils.getConnection();
			T clazz = null;
			
				try {
					clazz = runner.query(conn, sql, new BeanHandler<T>(type), args);
				} catch (SQLException e) {
//					e.printStackTrace();
					throw new RuntimeException(e);
				}finally {
//					JDBCUtils.closeResource();
				}
				return clazz;
			}
		/**
		 * 返回多条记录对应的多个对象的集合
		 * @param sql
		 * @param args
		 * @return
		 */
		public List<T> getBeanList(String sql, Object...args){
			Connection conn = JDBCUtils.getConnection();
			List<T> list = null;
			
			try {
				list = runner.query(conn, sql, new BeanListHandler<T>(type), args);
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
//				JDBCUtils.closeResource();
			}
			
			return list;
		}
		
		
		/**
		 * return one single value
		 * @param sql
		 * @param args
		 * @return
		 */
		public Object getSingleValue(String sql, Object...args) {
			//Single Value ---> ScalarHandler
			Connection conn = JDBCUtils.getConnection();
			Object obj = null;
			
			try {
				obj = runner.query(conn, sql, new ScalarHandler(), args);
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e);
				
			}finally {
//				JDBCUtils.closeResource(conn);
			}
			
			return obj;
		}
		
//******************************************************************************************************				
	
	/*
	 * Normal version CRUD
	 */
	//update database
//	public int update(String sql, Object...args) {
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = JDBCUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			
//			for(int i = 0; i < args.length; i++) {
//				ps.setObject(i + 1, args[i]);
//			}
//			return ps.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//			JDBCUtils.closeResource(conn, ps);
//			
//		}
//		return 0;
//	}
//	
//	
//	public T getBean(Class<T> clazz, String sql, Object...args) {
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = JDBCUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			
//			for(int i = 0; i < args.length; i++) {
//				ps.setObject(i + 1, args[i]);
//			}
//			
//			ResultSet resultset = ps.executeQuery();
//			ResultSetMetaData metaData = resultset.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			
//			if(resultset.next()) {
//				
//				T t = clazz.getConstructor().newInstance();
//				
//			for(int i = 1; i <= columnCount; i++) {
//				
//					Object columnValue = resultset.getObject(i);
//					String columnLabel = metaData.getColumnLabel(i);
//					Field field = clazz.getDeclaredField(columnLabel);
//					field.setAccessible(true);
//					field.set(t, columnValue);
//				}
//			return t;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtils.closeResource(conn, ps);
//		}
//		return null;
//	}
//	
//	
//	public List<T> getBeanList(Class<T> clazz, String sql, Object...args) {
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet resultset = null;
//		List<T> list = new ArrayList<>();
//		try {
//			conn = JDBCUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			
//			for(int i = 0; i < args.length; i++) {
//				ps.setObject(i + 1, args[i]);
//			}
//			
//			resultset = ps.executeQuery();
//			ResultSetMetaData metaData = resultset.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			while(resultset.next()) {
//				T t = clazz.getConstructor().newInstance();
//				
//			for(int i = 0; i < columnCount; i++) {
//				
//					Object columnValue = resultset.getObject(i + 1);
//					String columnLabel = metaData.getColumnLabel(i + 1);
//					Field field = clazz.getDeclaredField(columnLabel);
//					field.setAccessible(true);
//					field.set(t, columnValue);
//					
//				}
//			list.add(t);
//			
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtils.closeResource(conn, ps, resultset);
//		}
//		return null;
//	}
//	
//	
//	public Object getSingleValue(Class<T> clazz, String sql, Object...args) {
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = JDBCUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			
//			for(int i = 0; i < args.length; i++) {
//				ps.setObject(i + 1, args[i]);
//			}
//			
//			ResultSet resultset = ps.executeQuery();
//			ResultSetMetaData metaData = resultset.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			Object columnValue = null;
//			if(resultset.next()) {
//				
//				
//			for(int i = 1; i <= columnCount; i++) {
//				
//					columnValue = resultset.getObject(i);
//				}
//			return columnValue;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtils.closeResource(conn, ps);
//		}
//		
//		return null;
//	}
//	
	
	
	
	

}
