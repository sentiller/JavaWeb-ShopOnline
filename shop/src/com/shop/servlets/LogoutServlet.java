package com.shop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet(name = "Logout-Servlet", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 删除cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if (URLDecoder.decode(cookie.getName(), "utf-8").equals("password")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if (URLDecoder.decode(cookie.getName(), "utf-8").equals("status")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		// 删除数据库中信息

		// 显示信息
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("注销成功！");
		out.println("<a href=\"login.jsp\" style=\"font-size:35px,color:#0e92b3\">返回登录</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
