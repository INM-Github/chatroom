package com.qqServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
			System.out.println("�������ݿ�ʧ��");
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
	 * �������ݿ�����
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
	 * ȡ��һ�����ݿ�����,���õ�һģʽ
	 * @return
	 */
	public static Connection getConnection() {
		if (conn == null)
			conn = databaseConn();
		return conn;
	}

	/**
	 * �ر�����Դ����
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
