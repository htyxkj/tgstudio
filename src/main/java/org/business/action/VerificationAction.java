package org.business.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bip.base.entity.Message;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class VerificationAction extends ActionSupport{
	/**
	 * 验证手机号是否存在
	 */
	protected static Logger log = Logger.getLogger(VerificationAction.class);
	private String tel;
	private Message message=new Message();
	 
	public Message getMessage() {
		return message;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String verificationTel(){
		
		
		return "msg";
	}
}
