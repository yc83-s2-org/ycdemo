package com.yc.damai.web;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.AddressDao;
import com.yc.damai.dao.UserDao;
import com.yc.damai.po.DmAddress;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.Result;
import com.yc.damai.util.DBHelper;


@WebServlet("/address.do")
public class AddressServlet extends BaseAction {
	private static final long serialVersionUID = 1L;
       
	private AddressDao ad=new AddressDao();
	private UserDao ud=new UserDao();
    
	protected void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//设置页面的字符集
		response.setContentType("text/html; charset=UTF-8");
		//设置响应的字符集
		response.setCharacterEncoding("utf-8");
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");
		int id=ud.selectId(ename);
		String uid=String.valueOf(id);
		
		String sql = "select * from dm_address where uid = ?";
		List<?> list = new DBHelper().query(sql,uid);
		print( response, list);
	}
	
	
	
	// 保存商品
		protected void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			DmAddress da = new DmAddress();
						
			// 装载方法
			BeanUtils.populate(da, request.getParameterMap());
			// 名称验证 空值验证, 长度判断
			if(da.getName()==null || da.getName().trim().isEmpty()) {
				print( response, new Result(0,"收货人姓名不能为空!"));
				return;
			}
			if(da.getPhone()==null || da.getPhone().trim().isEmpty()) {
				print( response, new Result(0,"联系电话不能为空!"));
				return;
			}
			if(da.getAddr()==null || da.getAddr().trim().isEmpty()) {
				print( response, new Result(0,"地址不能为空!"));
				return;
			}
			
			// id 为空或者等于0 是新增
			if(da.getId() == null || da.getId() == 0) {
				ad.insert(da);
			} else {
				ad.update(da);
			}
			print( response, new Result(1,"保存成功!"));
		}

	
	
	
	

	
	
	
}
