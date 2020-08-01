package com.yc.damai.dao;

import com.yc.damai.util.DBHelper;

public class AdminDao {
	
	public boolean selectBylogin(String username,String password) {
		String sql="select * from dm_adminuser where username=? and password=?";
		DBHelper db=new DBHelper();
		//System.out.println(db.count(sql, ename,password));
		return db.count(sql, username,password)>0;
	}

}
