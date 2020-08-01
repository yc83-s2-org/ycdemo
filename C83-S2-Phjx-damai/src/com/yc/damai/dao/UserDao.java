package com.yc.damai.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmProduct;
import com.yc.damai.po.DmUser;
import com.yc.damai.util.*;

public class UserDao {

	/**
	 * 
	 * @param ename 英文名
	 * @param cname 中文名
	 * @param password 密码
	 * @param email 邮箱
	 * @param phone 电话
	 * @param sex 性别
	 */
	public void insert(String ename,String cname,String password,String email,String phone,String sex) {
		String sql="insert into dm_user values(null,?,?,?,?,?,?,1,now())";
		DBHelper db=new DBHelper();
		db.update(sql, ename,cname,password,email,phone,sex);
	}
	
	public Map<String, Object> query(String ename) {
		String sql="select * from dm_user where ename=?";
		List<Map<String, Object>> list = new DBHelper().query(sql, ename);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public boolean selectBylogin(String ename,String password) {
		String sql="select * from dm_user where ename=? and password=?";
		DBHelper db=new DBHelper();
		//System.out.println(db.count(sql, ename,password));
		return db.count(sql, ename,password)>0;
	}
	
	public int selectId(String ename) {
		String sql="select id from dm_user where ename=?";
		DBHelper db=new DBHelper();
		
		return (int) db.query(sql, ename).get(0).get("id");
		
		
	}
	
	
	
	public void reqpass(String password,String ename) {
		String sql="update dm_user set password=? where ename=?";
		DBHelper db=new DBHelper();
		db.update(sql,password, ename);
	}
	
	public void update(DmUser du) {
		String sql = "update dm_user set "
				+ "ename=?,"
				+ "cname=?,"
				+ "email=?,"
				+ "phone=?,"
				+ "sex=?,"
				+ " where id=?";
		new DBHelper().update(sql
				,du.getEname()
				,du.getCname()
				,du.getEmail()
				,du.getPhone()
				,du.getSex()
				,du.getId());
	}
	
}
