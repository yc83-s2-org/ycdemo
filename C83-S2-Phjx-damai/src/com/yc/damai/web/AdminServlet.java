package com.yc.damai.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.AdminDao;
import com.yc.damai.dao.UserDao;


@WebServlet("/admin.do")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private AdminDao ad=new AdminDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置页面的字符集
		response.setContentType("text/html; charset=UTF-8");
		//设置响应的字符集
		response.setCharacterEncoding("utf-8");
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(ad.selectBylogin(username, password)) {
			request.getSession().setAttribute("loginedUser", username);	        	
			response.getWriter().print("登录成功");	
		}else {
			response.getWriter().print("用户名或密码错误");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
