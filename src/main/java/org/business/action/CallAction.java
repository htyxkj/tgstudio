package org.business.action;

import org.apache.log4j.Logger;
import org.business.biz.ITellBiz;
import org.business.biz.impl.TellBizImpl;
import org.business.entity.Tell;

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
