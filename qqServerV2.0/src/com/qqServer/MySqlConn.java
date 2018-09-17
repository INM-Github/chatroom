package com.qqServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class MySqlConn {

	private static Connection conn = null;
	private MySqlConn() {
	}

	public static boolean testConnection() {
		Connection conn = null;
		try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qq?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","Zkd319132");                
	            
			return true;
		} catch(Exception e) {	
			System.out.println("连接数据库失败");
            e.printStackTrace();
			System.out.println(e);
		} finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
		return false;
	}

	/**
	 * 进行数据库连接
	 * @return
	 */
	private static Connection databaseConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qq?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","Zkd319132");
		} catch(Exception e) {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
			return null;
		}
		return conn;
	}

	/**
	 * 取得一个数据库连接,采用单一模式
	 * @return
	 */
	public static Connection getConnection() {
		if (conn == null)
			conn = databaseConn();
		return conn;
	}

	/**
	 * 关闭数据源连接
	 * @return
	 */
	public static boolean Close() {
		try {
			if (conn != null)
				conn.close();
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return false;
	}
}
