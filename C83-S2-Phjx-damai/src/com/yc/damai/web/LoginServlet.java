package com.yc.damai.web;

import java.io.Console;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.damai.dao.*;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	private UserDao ud=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置页面的字符集
		response.setContentType("text/html; charset=UTF-8");
		//设置响应的字符集
		response.setCharacterEncoding("utf-8");
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		String ename=request.getParameter("username");
		String password=request.getParameter("password");
		//获取到用户输入的验证码		
	    String ucheckC=request.getParameter("ucheckC");
	    System.out.println(ename);
	    System.out.println(password);
	    System.out.println(ucheckC);		
	    
	    HttpSession session=request.getSession();
	    System.out.println(session.getAttribute("vcode"));
	  //将用户输入的验证码与session中取出的验证码进行比较，相等的话就进行登录操作不成功则返回得到登录界面
	    if(session.getAttribute("vcode").toString().equalsIgnoreCase(ucheckC)) {       
	        if(ud.selectBylogin(ename, password)) {
	        	
	        	request.getSession().setAttribute("loginedUser", ename);	        	
				response.getWriter().print("登录成功");	        	
	        }else {
	        	response.getWriter().print("用户名或密码错误");
	        }
	    }else {
	    	 response.getWriter().print("验证码错误");
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
