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
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();
    private Message message = new Message();

    public String sendXcode() {
        String phone = request.getParameter("tels");
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
            String phone = request.getParameter("tels");
            String xcode = request.getParameter("xcode");
            String xcode1 = RedisHelper.get(phone);
            long tmOut = RedisHelper.getTtl(phone);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json; charset=utf-8");
            if(tmOut>0){
                if (xcode.equals(xcode1)) {
                    log.info("验证码通过");
                    message.setErrmsg("验证码通过！");
                    message.setErrcode("0");
                } else {
                    log.info("验证码不通过");
                    message.setErrmsg("验证码不通过！");
                    message.setErrcode("-1");

                }
            }else {
                log.info("请重新获取注册码！");
                message.setErrmsg("请重新获取注册码！");
                message.setErrcode("-1");
            }
        return "msg";
    }


}
