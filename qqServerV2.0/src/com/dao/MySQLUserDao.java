package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.qqServer.Group;
import com.qqServer.MySqlConn;
import com.qqServer.User;
import com.qqServer.UserIFC;

public class MySQLUserDao implements UserIFC {

	private static MySQLUserDao oracleUserDao;

	private MySQLUserDao() {
	}

	public static MySQLUserDao getInstance() {
		if (oracleUserDao == null)
			oracleUserDao = new MySQLUserDao();
		return oracleUserDao;
	}

	public boolean setOnline(String id, int isOnline) {
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE USERS SET U_isOnline = ? WHERE U_qq = ?");
			pst.setInt(1, isOnline);
			pst.setString(2, id);
			if (pst.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
				}
			}
		}
		return false;
	}

	public boolean delete(String id) {
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("DELETE FROM USERS WHERE U_qq = ?");
			pst.setString(1, id);
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
				}
			}
		}
		return false;
	}

	public boolean update(User user) {
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		String sql = "UPDATE USERS SET U_name=?,"
				+ "U_photoID=?,"
				+ "WHERE U_qq=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getName().trim());
			pst.setInt(2, user.getIconId());
			pst.setString(3, user.getId().trim());

			if (pst.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
				}
			}
		}
		return false;
	}

	public boolean add(User user) {
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		String sql = "INSERT INTO USERS(U_qq,U_name,U_pwd,U_photoID,U_isOnline)"
				+ "VALUES(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getId());
			pst.setString(2, user.getName());
			pst.setString(3, "123456");
			pst.setInt(4, user.getIconId());
			pst.setInt(5, 0);
			if (pst.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
				}
			}
		}
		return false;
	}

	public List selectId(String id) {
		HashMap hm = new HashMap();
		hm.put("U_qq", id);
		return select(hm);
	}
	public List selectGroup(String id) {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM groupinfo where G_no = (select G_no from user_group where qq ="+id+")");
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		List list = new ArrayList();
		ResultSet rs = null;
		try {
			int count = 1;
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			Group group = null;
			while (rs.next()) {
				group= new Group();
				group.setNo(rs.getString("G_no"));
				group.setName(rs.getString("G_name").trim());
				group.setGroupdate(rs.getString("G_date").trim());
				System.out.println(group);
				list.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
				}
			}
		}

		return list;

	}
	public List select(HashMap hm) {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM USERS");
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		List list = new ArrayList();
		String value = null;
		ResultSet rs = null;
		if (hm != null && hm.size() > 0) {
			value = (String) hm.get("U_qq");// 用户ID
			if (value != null && !value.equals("")) {
				sql.append(" where U_qq = ? ");
			}
			value = (String) hm.get("U_name");// 用户名称
			if (value != null && !value.equals("")) {
				sql.append(" where U_name = ? ");
			}
			value = (String) hm.get("U_isOnline");// 在线与否
			if (value != null && !value.equals("")) {
				sql.append(" where U_isOnline = ? ");
			}
			value = null;
			sql.append(" ORDER BY U_qq");
		}
		try {
			int count = 1;
			pst = conn.prepareStatement(sql.toString());
			if (hm != null && hm.size() > 0) {
				Iterator it = (hm.keySet()).iterator();
				while (it.hasNext()) {
					value = (String) hm.get(it.next());
					if (value != null && !value.equals("")) {
						pst.setString(count, value);
						count++;
					}
				}
			}
			rs = pst.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getString("U_qq").trim());
				user.setName(rs.getString("U_name").trim());
				user.setIconId(rs.getInt("U_photoID"));
				System.out.println(user.getIconId()+"zaizheer");
				user.setIsOnline(rs.getInt("U_isOnline"));
				user.setPassword(rs.getString("U_pwd").trim());
				System.out.println(user.getIconId());
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
				}
			}
		}

		return list;

	}

	public List selectOnLine() {
		HashMap hm = new HashMap();
		hm.put("U_isOnline", "" + 1);
		return getUserModel(hm);
	}

	public List getUserModel(HashMap hm) {
		StringBuffer sql = new StringBuffer("SELECT U_qq,U_name,U_photoID,U_isOnline=(case U_isOnline WHEN 1 THEN '在线' WHEN 0 THEN '离线' end) FROM USERS  ");
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		List list = new ArrayList();
		String value = null;
		ResultSet rs = null;
		if (hm != null && hm.size() > 0) {
			value = (String) hm.get("U_qq");// 用户ID
			if (value != null && !value.equals("")) {
				sql.append(" where U_qq = ? ");
			}
			value = (String) hm.get("U_name");// 用户名称
			if (value != null && !value.equals("")) {
				sql.append(" where U_name = ? ");
			}
			value = (String) hm.get("U_isOnline");// 在线与否
			if (value != null && !value.equals("")) {
				sql.append(" where U_isOnline = ? ");
			}
			value = null;
			sql.append(" ORDER BY U_qq");
		}
		try {
			int count = 1;
			pst = conn.prepareStatement(sql.toString());
			if (hm != null && hm.size() > 0) {
				Iterator it = (hm.keySet()).iterator();
				while (it.hasNext()) {
					value = (String) hm.get(it.next());
					if (value != null && !value.equals("")) {
						pst.setString(count, value);
						count++;
					}
				}
			}
			rs = pst.executeQuery();
			List row = null;
			while (rs.next()) {
				row = new ArrayList();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.add(rs.getString(i).trim());
				}
				list.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return list;
	}

	public List getColumnNames() {
		List list = new ArrayList();
		list.add("编号");
		list.add("姓名");
		list.add("性别");
		list.add("头像");
		return list;
	}

	public boolean resetPassword(String id, String password) {
		if (id == null || id.equals("")) {
			return false;
		}
		String sql = "UPDATE users SET U_pwd = ? WHERE U_qq = ?";
		Connection conn = MySqlConn.getConnection();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, id);
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}