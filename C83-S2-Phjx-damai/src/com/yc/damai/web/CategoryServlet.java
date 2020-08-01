package com.yc.damai.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.CategoryDao;

@WebServlet("/category.do")
public class CategoryServlet extends BaseAction {
	private static final long serialVersionUID = 1L;
       
  
	private CategoryDao cd=new CategoryDao();
	protected void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		List<?> list = cd.query();
		print(response, list);
	}

	

}
