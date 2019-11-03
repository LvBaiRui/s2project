package edu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbPub {
	public static String STD_DRIVER;
	public static String STD_URL;
	public static String STD_USERNAME;
	public static String STD_PASSWORD;
	public static String filename = "/app.properties";
	static {
		// 从配置文件获取四大金刚
		// Properties类专门用于读取扩展名为.properties的配置文件4
//		java.util.Properties prop = new java.util.Properties();
//		try {
//			prop.load(DbPub.class.getResourceAsStream(filename));
//			STD_DRIVER = prop.getProperty("STD_DRIVER");
//			STD_URL = prop.getProperty("STD_URL");
//			STD_USERNAME = prop.getProperty("STD_USERNAME");
//			STD_PASSWORD = prop.getProperty("STD_PASSWORD");
//			// 将数据库的驱动类的字节码文件加载到内存——只需要执行一次即可
//			Class.forName(STD_DRIVER);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("类全名【" + DbPub.STD_DRIVER + "】没有找到");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static Connection getConn() {
		Connection conn = null;
		conn=DruidFun.getConnection();
		
//		try {
//			conn = DriverManager.getConnection(STD_URL, STD_USERNAME, STD_PASSWORD);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return conn;
	}

	public static ResultSet query(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}

			}
			rs = stmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】出现错误");
		}
		return rs;

	}

	public static Long queryScalarLong(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long result = 0L;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}

			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】出现错误");
		}
		return result;
	}

	public static Long update(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		Integer num = 0;

		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			num = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】出现错误");
		}

		return num.longValue();

	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void close(Connection conn, Statement stmt) {
		try {

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
