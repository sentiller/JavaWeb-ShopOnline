package com.shop.dbclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.entity.Goods;

public class DataGoods {
	public List<Goods> readGoods() {
		List<Goods> list = new ArrayList<Goods>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			String sql = "select * from goodmessage";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("GOOD_NAME");
				Double price = Double.parseDouble(rs.getString("GOOD_PRICE"));
				Integer store = Integer.parseInt(rs.getString("GOOD_STORE"));
				Goods gl = new Goods(name, store, price);
				list.add(gl);
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

	public void addGood(String name, Double price, Integer store) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO goodmessage ( `GOOD_NAME`, `GOOD_PRICE`, `GOOD_STORE`) VALUES ( '" + name + "', '"
					+ price + "', '" + store + "');\r\n";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			System.out.println("add successfully, name=" + name + ", price=" + price + ", store=" + store);
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

	public void fleshGood() {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC", "root", "111111");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM goodmessage;\r\n";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			System.out.println("删除数据库中所有产品信息！");
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