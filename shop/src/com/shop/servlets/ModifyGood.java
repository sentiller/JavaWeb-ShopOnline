package com.shop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dbclass.DataGoods;

@WebServlet(name = "Modify-Good", urlPatterns = { "/modifygood" })
public class ModifyGood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 删除原有数据
		DataGoods dg = new DataGoods();
		dg.fleshGood();

//		// 写入数据
		response.setContentType("text/html;charset=UTF-8");
		String[] names = request.getParameterValues("name");
		String[] prices = request.getParameterValues("price");
		String[] stores = request.getParameterValues("store");

		for (int i = 0; i < names.length; i++) {
			String name = new String(names[i].getBytes("ISO8859-1"), "UTF-8");
			String price = new String(prices[i].getBytes("ISO8859-1"), "UTF-8");
			String store = new String(stores[i].getBytes("ISO8859-1"), "UTF-8");
			System.out.println("addGood: name=" + name + ", price" + price + ", store=" + store);
			dg.addGood(name, Double.parseDouble(price), Integer.parseInt(store));
		}

		// 回到原页面
		request.getRequestDispatcher("/goods.jsp").forward(request, response);
	}

}
