package com.yc.damai.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.mysql.cj.Session;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yc.damai.dao.CartDao;
import com.yc.damai.dao.OrderItemDao;
import com.yc.damai.dao.OrdersDao;
import com.yc.damai.dao.UserDao;
import com.yc.damai.po.DmOrderitem;
import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.Result;


@WebServlet("/orders.do")
public class OrdersServlet extends BaseAction {
	private static final long serialVersionUID = 1L;

	private OrdersDao odao = new OrdersDao();
	private OrderItemDao oidao = new OrderItemDao();
	private CartDao cdao = new CartDao();
	private UserDao ud=new UserDao();

	// 添加订单
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");						
		String uid=String.valueOf(ud.selectId(ename));		
		odao.insert(uid);
		oidao.insert(uid);
		cdao.deleteByUid(uid);
		response.getWriter().append("{\"code\":\"1\"}");
	}

	// 查询新增的订单
	protected void query(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");						
		String uid=String.valueOf(ud.selectId(ename)); // 用户id 从会话中获取 loginedUser.getId(),
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> orders = odao.queryNewOrders(uid);
		ret.put("orders", orders);
		ret.put("orderitem", oidao.queryByOid("" + orders.get("id")));
		print(response, ret);
	}
	
	protected void queryPayOrdersItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");	
		/**
		 * 用于获取多个参数的工具
		 * BeanUtils.populate(bean, properties);
		 * bean: 要装载的实体对象
		 * properties: 存放属性值的map集合
		 */
		DmProduct dp=new DmProduct();
		BeanUtils.populate(dp,request.getParameterMap());
		
		List<Map<String, Object>> list=odao.queryPayOrdersItem(dp,page,rows);	
		int total=odao.count(dp);
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("rows", list);
		data.put("total", total);
		print(response, data);	
	}
	
	
	protected void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id==null || id.isEmpty()) {
			response.getWriter().print("订单不存在");response.getWriter().append("{\"msg\":\"订单不存在!\"}");
		}else {
			odao.Update(id);
			response.getWriter().append("{\"msg\":\"支付成功!\"}");
		}
		
	}
	
	
	//收货
	protected void shouHuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid=request.getParameter("oid");
		System.out.println(oid);
		try {
			if(oid!=null && !oid.equals("")) {
				odao.UpdateState("3",oid);
				response.getWriter().print("收货成功");
			}else {
				response.getWriter().append("收货失败,请联系客服！");
			}
		} catch (Exception e) {
			response.getWriter().append("收货失败,请联系客服！");
		}
		
	}
	
	
	
	//查询所有的订单
	protected void queryAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");						
		String uid=String.valueOf(ud.selectId(ename)); 		
		
		List<Map<String, Object>> orders=odao.queryAllorders(uid);
	
		List<Map<String, Object>> orderitem=odao.queryOrders(uid);//查询订单详情
		Map<String, Object> ret = new HashMap<>();
		ret.put("orders", orders);
		ret.put("orderitem",orderitem);
		print(response, ret);
		
		
		
		
	}
	
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取web服务器自动创建的会话对象
		HttpSession session=request.getSession();
		//从会话中获取登录的标识
		String ename=(String) session.getAttribute("loginedUser");						
		String uid=String.valueOf(ud.selectId(ename));		
		odao.delByUid(uid);
		response.getWriter().append("{\"code\":\"1\"}");
	}
	
	protected void queryBy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		List<Map<String, Object>> olist=odao.query();	
		print(response, olist);
	}

	// 保存商品
	protected void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		DmOrders dp = new DmOrders();
		// 装载方法
		BeanUtils.populate(dp, request.getParameterMap());
		// 商品名称验证 空值验证, 长度判断
		if(dp.getId()==null) {
			print( response, new Result(0,"订单编号不能为空!"));
			return;
		}		
		
		odao.updateState(dp);
		print( response, new Result(1,"发货成功!"));
	}

	

}
