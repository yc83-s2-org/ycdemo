package com.yc.damai.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.UserDao;


@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {
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
		String cname=request.getParameter("cname");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
		if(ename==null || ename.trim().isEmpty()) {
			response.getWriter().append("请输入英文名!");
		}else if(cname==null || cname.trim().isEmpty()) {
			response.getWriter().append("请输入中文名!");
		}else if(password==null || password.trim().isEmpty()) {
			response.getWriter().append("请输入密码!");
		}else if(password.equals(repassword)==false) {
			response.getWriter().append("两次输入的密码不一致!");		
		}else if(email==null || email.trim().isEmpty()) {
			response.getWriter().append("请输入电子邮箱!");
		}else if(phone==null || phone.trim().isEmpty()) {
			response.getWriter().append("请输入联系电话!");
		}else {
			ud.insert(ename, cname, password, email, phone, sex);
			response.getWriter().append("用户注册成功");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
