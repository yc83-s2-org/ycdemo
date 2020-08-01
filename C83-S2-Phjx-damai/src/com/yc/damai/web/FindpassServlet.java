package com.yc.damai.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.UserDao;


@WebServlet("/find.do")
public class FindpassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	private UserDao ud=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置页面的字符集
		response.setContentType("text/html; charset=UTF-8");
		//设置响应的字符集
		response.setCharacterEncoding("utf-8");
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		String reqpassword=request.getParameter("reqpassword");
		if(password ==null || password.trim().isEmpty()) {
			response.getWriter().append("请输入新的密码!");
		}else if(password.equals(reqpassword)==false) {
			response.getWriter().append("两次输入的密码不一致!");
		}else {
			ud.reqpass(reqpassword, ename);
			response.getWriter().append("更改密码成功");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
