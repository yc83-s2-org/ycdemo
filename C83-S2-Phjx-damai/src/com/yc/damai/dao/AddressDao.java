package com.yc.damai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmAddress;
import com.yc.damai.po.DmProduct;
import com.yc.damai.util.DBHelper;

public class AddressDao {

	public int add(String uid,String addr,String phone,String uname,int dft) {
		String sql="insert into dm_address values(null,?,?,?,?,?,now())";
		return new DBHelper().update(sql, uid,addr,phone,uname,dft);
	}
	public int countByUid(String uid) {
		String sql="select count(*) from dm_address where uid=?";
		return new DBHelper().count(sql, uid);
	}
	public int modifyDft(String uid) {
		String sql="update dm_address set dft=1 where uid=?";
		return new DBHelper().update(sql, uid);
	}
	
	public List<?> queryByUid(String uid){
		String sql="select * from dm_address where uid=?";
		return new DBHelper().query(sql, uid);
	} 
	
		
	
	public void insert(DmAddress da) {
		String sql = "insert into dm_address values(null,?,?,?,?,?,now())";
		new DBHelper().update(sql
				,da.getUid()
				,da.getAddr()
				,da.getPhone()
				,da.getName()
				,da.getDft()
				);
	}

	public void update(DmAddress da) {
		String sql = "update dm_address set"				
				+ " addr=?,"
				+ "phone=?,"
				+ "name=?,"
				+ "dft=?"				
				+ " where id=?";
		new DBHelper().update(sql
				,da.getAddr()
				,da.getPhone()
				,da.getName()
				,da.getDft()
				,da.getId()
				);
	}
	
	
}
