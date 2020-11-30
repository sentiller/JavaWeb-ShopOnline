package com.shop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dbclass.DataUser;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "Register-Servlet", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 3640373998515674009L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
//		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO8859-1"), "UTF-8");
		String userpwd = request.getParameter("userpwd");
		String status = request.getParameter("status");
		String email = request.getParameter("email");
//		System.out.println(username);
//		System.out.println(userpwd);
//		System.out.println(status);
//		System.out.println(email);

		// 添加到数据库
		DataUser dao = new DataUser();
		dao.addUser(username, userpwd, email, status);

		// 返回登录页面
		PrintWriter out = response.getWriter();
		out.print("<script> alert(\"您已注册成功，返回登录界面进行登录\") </script>");
		out.println("<a href=\"login.jsp\" style=\"font-size:35px,color:#0e92b3\">返回登录</a>");

	}

}
