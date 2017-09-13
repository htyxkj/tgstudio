package org.business.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.business.biz.ITellBiz;
import org.business.biz.impl.TellBizImpl;
import org.business.entity.Tell;

public class CallServlet extends HttpServlet {
	//
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 一键拨号，查询电话号码
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ITellBiz tellbiz=new TellBizImpl();
		try {
			Tell tell=tellbiz.getOne();
			String phone=tell.getNumber();
			request.setAttribute("phone",phone);
			request.getRequestDispatcher("jsp/call/Call.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
