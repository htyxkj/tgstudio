package org.business.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.ITellBiz;
import org.business.biz.impl.TellBizImpl;
import org.business.entity.Tell;

import com.bip.base.action.SMSAction;
import com.opensymphony.xwork2.ActionSupport;

public class CallAction extends ActionSupport{
	/**
	 * 一键拨号，查询电话号码
	 */
	protected static Logger log = Logger.getLogger(CallAction.class);
	private String phone;
    public String getPhone() {
		return phone;
	}
	public String call() {
    	ITellBiz tellbiz=new TellBizImpl();
		try {
			Tell tell=tellbiz.getOne();
			phone=tell.getNumber();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "call";
    }
}
