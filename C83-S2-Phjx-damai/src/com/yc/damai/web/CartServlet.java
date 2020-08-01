package com.yc.damai.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.damai.dao.CartDao;
import com.yc.damai.dao.UserDao;
import com.yc.damai.util.DBHelper;


@WebServlet("/cart.do")
public class CartServlet extends BaseAction {
	private static final long serialVersionUID = 1L;
   

	private CartDao cd=new CartDao();
	private UserDao ud=new UserDao();
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");
		
		int id=ud.selectId(ename);
		String uid=String.valueOf(id);
		System.out.println(uid);
		//用户id  从会话中获取loginedUser.getId()
		if(uid==null) {
		    //表示没有登录		
			response.getWriter().append("{\"msg\":\"请登录!\"}");
		}else {
			String pid=request.getParameter("pid");
			if(cd.update(uid, pid)==0) {
				//结果为0，说明该用户还没有添加过该商品
				cd.add(uid, pid);
			}
			response.getWriter().append("{\"msg\":\"购物车添加成功!\"}");
			
		}
		
		
	}
	
	protected void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");						
		String uid=String.valueOf(ud.selectId(ename));
		List<?> list=cd.queryByUid(uid);
		print(response,list);
	}
	
	//有问题
	protected void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");						
		String uid=String.valueOf(ud.selectId(ename));
		String pid=request.getParameter("pid");	
		int result=cd.del(pid);
		if(result==1) {
			try {
				response.getWriter().append("{\"msg\":\"删除成功!\"}");
			} catch (IOException e) {
				response.getWriter().append("{\"msg\":\"删除失败!\"}");
			}
		}
		
		
	}
	
	
	

	
	

}
