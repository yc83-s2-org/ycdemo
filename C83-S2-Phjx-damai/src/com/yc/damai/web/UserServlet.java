package com.yc.damai.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.UserDao;
import com.yc.damai.po.DmAddress;
import com.yc.damai.po.DmUser;
import com.yc.damai.po.Result;


@WebServlet("/user.do")
public class UserServlet extends BaseAction {
	private static final long serialVersionUID = 1L;
       
	private UserDao ud=new UserDao();
	protected void queryByUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");							
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> users = ud.query(ename);
		ret.put("users", users);		
		print(response, ret);
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DmUser du = new DmUser();
		
		// 装载方法
		BeanUtils.populate(du, request.getParameterMap());
		// 名称验证 空值验证, 长度判断
		if(du.getCname()==null || du.getCname().trim().isEmpty()) {
			print( response, new Result(0,"英文姓名不能为空!"));
			return;
		}else if(du.getPhone()==null || du.getPhone().trim().isEmpty()) {
			print( response, new Result(0,"联系电话不能为空!"));
			return;
		}else if(du.getEmail()==null || du.getEmail().trim().isEmpty()) {
			print( response, new Result(0,"邮箱不能为空!"));
			return;
		}else {
			ud.update(du);
		}
				
		print( response, new Result(1,"保存成功!"));
	}
	

	
	

}
