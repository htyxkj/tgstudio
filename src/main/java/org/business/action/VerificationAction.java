package org.business.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.IPhonelogBiz;
import org.business.biz.impl.PhonelogBizImpl;
import org.business.entity.Message;
import org.business.entity.Phonelog;
import org.business.quartzPackage.WeiXinChat;
import org.core.util.WeixinUtil;
import org.sms.utils.XcodeValidTool;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 发送验证码，验证验证码是否正确
 * @author Administrator
 *
 */
public class VerificationAction extends ActionSupport{
	HttpSession session =  ServletActionContext.getRequest().getSession();
	protected static Logger log = Logger.getLogger(VerificationAction.class);
	private String tels;
	private String xcode;
	private Message message=new Message();
	
	public void setXcode(String xcode) {
		this.xcode = xcode;
	}
	public Message getMessage() {
		return message;
	}
	public void setTels(String tels) {
		this.tels = tels;
	}
	public String verificationTel(){
		return "msg";
	}
	
	/**
	 * 发送验证码
	 * @return
	 */
	public String sendXcode(){
		try {
			IPhonelogBiz phonelog = new PhonelogBizImpl();
			Locale locale = Locale.getDefault();  
			ResourceBundle bundle = ResourceBundle.getBundle("token", locale);
			String url = bundle.getString("smsUrl");
			String requestUrl = url+"xcode";
			Map<String, String> map = new HashMap<String, String>();
			map.put("tels", tels);
			map.put("title", "糖果录音棚");
			map.put("fhxcode", "0");
			String jsonStr=WeixinUtil.httpclient(requestUrl, map);
			JSONObject jsonObj=JSONObject.parseObject(jsonStr);
			if(jsonObj.getIntValue("type")==0){
				String code=jsonObj.getString("xcode");
				Phonelog phone = new Phonelog();
		        //往下取整 1.9=> 1.0
		        long floorValue = new Date().getTime();
				phone.setOid(""+floorValue);
				phone.setTel(tels);
				phone.setContent("【糖果录音棚】您好，您的验证码是："+code+",验证码的有效时间为10分钟");
				phonelog.addChat(phone);
				message.setErrmsg("发送成功，请注意查收短信！");
				message.setErrcode("0");
			}else{
				message.setErrmsg("短信发送失败!");
				message.setErrcode("-1");
			}
			log.info(message.getErrcode()+":"+message.getErrmsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
	/**
	 * 验证验证码
	 */
	public String checkXcode(){
		try {
			this.session.setAttribute("tel", "18310009189");
			Locale locale = Locale.getDefault();  
			ResourceBundle bundle = ResourceBundle.getBundle("token", locale);
			String url = bundle.getString("smsUrl");
			String requestUrl = url+"ckxcode";
			Map<String, String> map = new HashMap<String, String>();
			map.put("tels", tels);
			map.put("xcode", xcode);
			String jsonStr=WeixinUtil.httpclient(requestUrl, map);
			JSONObject jsonObj=JSONObject.parseObject(jsonStr);
			if(jsonObj.getIntValue("type")==0){
				this.session.setAttribute("tel", "18310009189");
				message.setErrmsg("验证码通过");
				message.setErrcode("0");
			}else{
				message.setErrmsg(jsonObj.getString("info"));
				message.setErrcode("-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
}