package com.yc.damai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;
import com.yc.damai.util.DBHelper;

public class OrdersDao {
	
	// 添加订单主表记录 orders
		public int insert(String uid) {
			String sql = "INSERT INTO dm_orders SELECT\n" + "	NULL,\n" + "	c.total,\n" + "	now(),\n" + "	0,\n"
					+ "	a.id,\n" + "	b.id\n" + "FROM\n" + "	dm_user a\n" +
					// 获取默认地址
					"JOIN dm_address b ON a.id = b.uid\n" + "AND dft = 1\n" + "JOIN (\n" +
					// 求订单总金额
					"	SELECT\n" + "		sum(b.shop_price * a.count) total,\n" + "		a.uid\n" + "	FROM\n"
					+ "		dm_cart a\n" + "	JOIN dm_product b ON a.pid = b.id\n" + "	WHERE\n" + "		a.uid = ?\n"
					+ "	GROUP BY\n" + "		a.uid\n" + ") c ON a.id = c.uid\n" + "WHERE\n" + "	a.id = ?";
			return new DBHelper().update(sql, uid, uid);
		}

		public Map<String, Object> queryNewOrders(String uid) {
			String sql = "select a.*,b.addr, b.phone, b.name from dm_orders a join dm_address b on a.aid=b.id"
					+ " where a.uid=? and state=0 order by id desc limit 0,1";
			List<Map<String, Object>> list = new DBHelper().query(sql, uid);
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		}
		
		public int delByUid(String uid) {
			String sql="delete from dm_orders where uid=?";
			return new DBHelper().update(sql, uid);
		}
		
		public int selectId(String uid) {
			String sql="select id from dm_user where uid=?";
			DBHelper db=new DBHelper();
			
			return (int) db.query(sql, uid).get(0).get("id");
			
			
		}
		
		public int Update(String id) {
			String sql="update dm_orders set createtime=now(),state=1 where id=?";
			return new DBHelper().update(sql,id);
		}
		
		/**
		 * 更新支付状态
		 * 1已付款,2已发货
		 * @param state
		 * @param oid
		 * @return
		 */
		public int UpdateState(String state,String oid) {
			String sql="update dm_orders set createtime=now(),state=? where id=?";
			return new DBHelper().update(sql, state,oid);
		}
		
		//更新订单的状态
//		public int updateState(String state,String id) {
//			String sql="update from dm_orders set state=? where id=?";
//			return new DBHelper().update(sql, state,id);
//		}
		public void updateState(DmOrders dp) {
			String sql = "update dm_orders set "
					+ "createtime=?,"
					+ "total=?,"
					+ "state=?"
					+ " where id=?";
			new DBHelper().update(sql
					,dp.getCreatetime()
					,dp.getTotal()
					,dp.getState()
					,dp.getId());
		}
		
		//查询全部订单
		public List<Map<String, Object>> queryAllorders(String uid){
			String sql="select * from dm_orders where uid=? order by id desc";
			List<Map<String, Object>> list = new DBHelper().query(sql, uid);
			if (list.isEmpty()) {
				return null;
			} else {
				return  list;
			}
		}
		
		/**
		 * 查询该用户下所有的订单包括状态，查询订单表和订单详情表和商品表
		 * @return
		 */
		public List<Map<String, Object>> queryOrders(String uid){
			DBHelper db=new DBHelper();
			String sql="SELECT\n" +
					"	a.id cid,\n" +
					"	a.total,\n" +
					"	a.state,\n" +
					"	b.count,\n"+
					"	a.uid,\n" +
					"	c.*\n" +
					"FROM\n" +
					"	dm_orders a\n" +
					"LEFT JOIN dm_orderitem b ON a.id = b.oid\n" +
					"LEFT JOIN dm_product c ON b.pid = c.id\n" +
					"WHERE\n" +
					"	uid = ?";
			return db.query(sql, uid);
		}

		/**
		 * 后台使用
		 * 查询这个订单编号下的全部商品明细
		 * @param dp
		 * @param page
		 * @param rows
		 * @return
		 */
		public List<Map<String, Object>> queryPayOrdersItem(DmProduct dp,String page,String rows){
			String where="";
			List<Object> params=new ArrayList<Object>();
			if(dp.getId()!=null) {
				if(dp.getId()!=0) {
					where+= " and a.id=? ";
					params.add(dp.getId());
				}
			}	
			int ipage=Integer.parseInt(page);
			int irows=Integer.parseInt(rows);
			ipage = (ipage - 1) * 10;
			String sql="SELECT\n" +
					"	a.id,\n" +
					"	a.createtime,\n" +
					"	c.image,\n" +
					"	c.pname,\n" +
					"	b.total / b.count price,\n" +
					"	b.count,\n" +
					"	d.ename,\n" +
					"	b.total,\n" +
					"	a.state\n"+
					"FROM\n" +
					"	dm_orders a\n" +
					"JOIN dm_orderitem b ON a.id = b.oid\n" +
					"JOIN dm_product c ON b.pid = c.id\n" +
					"JOIN dm_user d ON a.uid = d.id\n" +
					"WHERE\n" +
					"	a.state = 1 "
					+ where
					+ " limit ?,? ";
			params.add(ipage);
			params.add(irows);
			return new DBHelper().query(sql,params.toArray());
		}

		public int count(DmProduct dp){
			String where="";
			List<Object> params=new ArrayList<>();
			if(dp.getPname()!=null && dp.getPname().trim().isEmpty()==false) {
				where+= " and c.pname like ? ";
				params.add("%"+dp.getPname()+"%");
			}
			if(dp.getId()!=null) {
				if(dp.getId()!=0) {
					where+= " and a.id=? ";
					params.add(dp.getId());
				}
			}	
			
			String sql = "SELECT\n" +
					"	a.id,\n" +
					"	a.createtime,\n" +
					"	c.image,\n" +
					"	c.pname,\n" +
					"	b.total / b.count price,\n" +
					"	b.count,\n" +
					"	d.ename,\n" +
					"	a.total,\n" +
					"	a.state\n"+
					"FROM\n" +
					"	dm_orders a\n" +
					"JOIN dm_orderitem b ON a.id = b.oid\n" +
					"JOIN dm_product c ON b.pid = c.id\n" +
					"JOIN dm_user d ON a.uid = d.id\n" +
					"WHERE\n" +
					"	a.state = 1 " +where+" GROUP BY a.id  ";
			return  new DBHelper().count(sql,params.toArray());
		}
		
		public List<Map<String,Object>> query() {
			String sql="select * from dm_orders a join dm_user b on a.uid=b.id where a.state=1";
			return new DBHelper().query(sql);
		}
		

//		public static void main(String[] args) {
//			// 这种写法有数据库事务的问题
//			new OrdersDao().insert("2");
//			// 出现异常, 会导致 订单被创建, 而订单明细没有创建, 购物车没有被清空
//			new OrderItemDao().insert("2");
//			new CartDao().deleteByUid("2");
//		}
	

}
