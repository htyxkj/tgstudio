package com.bip.base.action;


import com.bip.base.entity.Message;
import com.bip.base.utils.RedisHelper;
import com.bip.base.utils.XcodeValidTool;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SMSAction extends ActionSupport{
    protected static Logger log = Logger.getLogger(SMSAction.class);
    private String tels;
    private String xcode;
	private Message message = new Message();
    public void setTels(String tels) {
		this.tels = tels;
	}
	public void setXcode(String xcode) {
		this.xcode = xcode;
	}
    public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public String sendXcode() {
        String phone = tels;
        String result = XcodeValidTool.getXcode(phone);
        if (result.startsWith("success")) {
        	message.setErrmsg("发送成功，请注意查收短信！");
        	message.setErrcode("0");
        } else {
        	message.setErrmsg(result);
        	message.setErrcode("-1");
        }
        log.info("sendXcode");
        return "msg";
    }

    public String checkXcode() {
            String phone = tels;
            String xcode1 = RedisHelper.get(phone);
            long tmOut = RedisHelper.getTtl(phone);
            if(tmOut>0){
                if (xcode.equals(xcode1)) {
                    log.info("验证码通过");
                    message.setErrmsg("验证码通过！");
                    message.setErrcode("0");
                } else {
                    log.info("验证码不通过");
                    message.setErrmsg("验证码输入错误!");
                    message.setErrcode("-1");

                }
            }else {
                log.info("请重新获取注册码！");
                message.setErrmsg("请重新获取注册码!");
                message.setErrcode("-2");
            }
        return "msg";
    }


}
