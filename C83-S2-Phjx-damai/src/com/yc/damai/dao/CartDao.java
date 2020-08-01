package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.util.DBHelper;

public class CartDao {

	/**
	 * 添加购物车商品
	 * @param uid
	 * @param pid
	 * @return
	 */
	public int add(String uid,String pid) {
		String sql="insert into dm_cart values(null,?,?,1)";
		return new DBHelper().update(sql, uid,pid);
	}
	
	/**
	 * 给某个用户的购物车商品数量+1
	 * @param uid
	 * @param pid
	 * @return 新增的记录数
	 */
	public int update(String uid,String pid) {
		String sql="update dm_cart set count=count+1 where uid=? and pid=?";
		return new DBHelper().update(sql, uid,pid);
	}
	
	
	public List<?> queryByUid(String uid){
		String sql="select * from dm_cart a join dm_product b on a.pid=b.id where a.uid=?";
		return new DBHelper().query(sql, uid);
	}
	public List<?> queryByPid(String Pid){
		String sql="select * from dm_cart where pid=?";
		return new DBHelper().query(sql, Pid);
	}
	
	public int deleteByUid(String uid) {
		String sql="delete from dm_cart where uid=?";
		return new DBHelper().update(sql, uid);
	}
	
	public int del(String pid) {
		String sql="delete from dm_cart where pid=?";
		return new DBHelper().update(sql, pid);
	}
	
	
}
