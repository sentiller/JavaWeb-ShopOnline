package com.shop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dbclass.DataUser;
import com.shop.entity.User;

@WebServlet(name = "Login-Servlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 2809655180454195611L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 用户信息
		String name = new String();
		String userpwd = new String();
		Integer status = 0;
		// 从地址栏直接访问页面时，先判断cookie中是否有账号
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) {
					name = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
				if (URLDecoder.decode(cookie.getName(), "utf-8").equals("password")) {
					userpwd = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
				if (URLDecoder.decode(cookie.getName(), "utf-8").equals("status")) {
					status = Integer.parseInt(URLDecoder.decode(cookie.getValue(), "utf-8"));
				}
			}
		}
		PrintWriter out = response.getWriter();
		System.out.println(
				"input: name=" + name + ", password=" + userpwd + ", status=" + status + "(1.business;2.customer)");
		check(request, response, out, name, userpwd, status);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收前端参数
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String name = new String(username.getBytes("ISO8859-1"), "UTF-8");
		String userpwd = request.getParameter("userpwd");
		Integer status = Integer.parseInt(request.getParameter("status"));
		System.out.println(
				"input: name=" + name + ", password=" + userpwd + ", status=" + status + "(1.business;2.customer)");

		check(request, response, out, name, userpwd, status);
	}

	public void check(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String name,
			String userpwd, Integer status) throws ServletException, IOException {
		// 验证身份处理
		boolean flag = false; // 默认身份验证失败
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
//				+ "/";
//		User dbUser = new User("qwe", "qwe", 1, "qwe@qq.com"); // 模拟数据库的用户：商家
//		User dbUser = new User("qwe", "qwe", 2, "qwe@qq.com"); // 模拟数据库的用户：客户
		User dbUser = new User();
		DataUser dao = new DataUser();
		List<User> list = dao.readUser();
		for (User tl : list) {
			if (name.equals(tl.getName()) && userpwd.equals(tl.getPwd()) && status.equals(tl.getStatus())) {
				dbUser = tl;
				flag = true;
				break;
			} else {
				flag = false;
			}
		}

		// 后续操作
		if (flag) {
			// 将用户信息存放在session
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", dbUser);
			// 将用户信息存在cookie
			Cookie usernameCookie = new Cookie("username", name);
			Cookie passwordCookie = new Cookie("password", userpwd);
			Cookie statusCookie = new Cookie("status", Integer.toString(status));
			usernameCookie.setMaxAge(86400);
			passwordCookie.setMaxAge(86400);
			statusCookie.setMaxAge(86400);
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
			response.addCookie(statusCookie);
			out.print("用户信息保存在cookie中");
		} else {
			out.print("<script> alert(\"用户名或密码错误\") </script>");
			out.println("<a href=\"login.jsp\" style=\"font-size:35px,color:#0e92b3\"> 返回登录页 </a>");
			return;
		}

		// 判断类型
		if ("1".equals(Integer.toString(status))) {
			request.getRequestDispatcher("/goods.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}

}
