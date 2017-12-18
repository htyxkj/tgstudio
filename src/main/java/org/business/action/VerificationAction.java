package org.business.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.entity.Message;
import org.sms.utils.XcodeValidTool;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 发送验证码，验证验证码是否正确
 * @author Administrator
 *
 */
public class VerificationAction extends ActionSupport{
	HttpSession session =  ServletActionContext.getRequest().getSession();
	public static XcodeValidTool xx;
	static{
		if(xx==null){
			xx = new XcodeValidTool();
			xx.start();
		}
	}
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
		String phone = tels;
        String content="【糖果录音棚】您好，您的验证码是：[code],验证码的有效时间为10分钟";
		String result = xx.getXcode(phone,content);
		if(result.startsWith("success")){
			message.setErrmsg("发送成功，请注意查收短信！");
			message.setErrcode("0");
		}else{
			message.setErrmsg(result);
			message.setErrcode("-1");
		}
		log.info(message.getErrcode()+":"+message.getErrmsg());
		return "msg";
	}
	/**
	 * 验证验证码
	 */
	public String checkXcode(){
		try {
			String xcode1=null;
			if(xx._keycode.containsKey(tels))
				xcode1 = xx._keycode.get(tels);
			if(xcode1==null){
				log.info("请重新获取注册码！");
				message.setErrmsg("请重新获取注册码！");
				message.setErrcode("-1");
				return "msg";
			}
			long edt=0;
			if(xx._keycodeValidt.containsKey(tels+"_"+xcode1))
				edt = xx._keycodeValidt.get(tels+"_"+xcode1);
			long n1 = XcodeValidTool.getNow();
			if(n1>edt){
				log.info("注册码已过期！");
				message.setErrmsg("注册码已过期！");
				message.setErrcode("-1");
				return "msg";
			}
			if(xcode.equals(xcode1)){
				if(xx._keycode.containsKey(tels))
					xx._keycode.remove(tels);
				if(xx._keycodeValidt.containsKey(tels+"_"+xcode))
					xx._keycodeValidt.remove(tels+"_"+xcode);
				log.info("验证码通过");
				session.setAttribute("tel", tels);
				message.setErrmsg("验证码通过！");
				message.setErrcode("0");
				return "msg";
			}
			else{
				log.info("验证码不通过");
				message.setErrmsg("验证码输入错误！");
				message.setErrcode("-1");
				return"msg";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
}
