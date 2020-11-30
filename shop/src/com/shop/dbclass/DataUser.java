package com.shop.dbclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.entity.User;

public class DataUser {
	public List<User> readUser() {
		List<User> list = new ArrayList<User>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			String sql = "select * from user";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("USER_NAME");
				String password = rs.getString("USER_PASSWORD");
				String email = rs.getString("USER_EMAIL");
				Integer status = Integer.parseInt(rs.getString("USER_STATUS"));
				User ul = new User(name, password, status, email);
				list.add(ul);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	};

	public void addUser(String name, String pwd, String email, String status) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO user ( `USER_NAME`, `USER_PASSWORD`, `USER_EMAIL`, `USER_STATUS`) VALUES ('"
					+ name + "', '" + pwd + "', '" + email + "', '" + status + "');\r\n";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			System.out.println(
					"register successfully, name=" + name + ", pwd=" + pwd + ", email=" + email + ", status=" + status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}