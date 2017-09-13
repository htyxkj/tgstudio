package org.business.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.business.biz.IInsorgBiz;
import org.business.biz.impl.InsorgBizImpl;
import org.business.entity.Insorg;

public class ContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 获取店铺信息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IInsorgBiz insorgB=new InsorgBizImpl();
			List<Insorg> listI=insorgB.getALl();
			request.setAttribute("listI", listI);
			request.getRequestDispatcher("jsp/contact/Contact.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
