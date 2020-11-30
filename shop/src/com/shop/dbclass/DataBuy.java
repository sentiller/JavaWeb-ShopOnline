package com.shop.dbclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.entity.Buy;

public class DataBuy {
	public List<Buy> readBuy() {
		List<Buy> list = new ArrayList<Buy>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			String sql = "select * from buymessage";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String type = rs.getString("TYPE");
				String customer = rs.getString("CUSTOMER");
				String goods = rs.getString("GOODS_NAME");
				String time = rs.getString("TIME");
				Buy bl = new Buy(type, customer, goods, time);
				list.add(bl);
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

	public void addGood(String type, String customer, String goods, String time) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO goodmessage ( `TYPE`, `USER_NAME`, `GOOD_NAME`,'BUY_TIME') VALUES ( '" + type
					+ "', '" + customer + "', '" + goods + "', '" + time + "');\r\n";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			System.out.println("add successfully, type=" + type + ", customer=" + customer + ", goods=" + goods);
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