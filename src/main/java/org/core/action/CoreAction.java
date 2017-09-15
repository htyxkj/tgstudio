package org.core.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.core.util.SignUtil;

import com.opensymphony.xwork2.ActionSupport;
/** 
 * 核心请求处理类 
 * @author lizhengguo 
 * @date 2017-09-08 
 */  
public class CoreAction extends ActionSupport{
	 private static Logger log = Logger.getLogger(CoreAction.class); 
	 private static final long serialVersionUID = 4440739483644821986L;
	 private HttpServletResponse response = ServletActionContext.getResponse();
	 private String signature ;// 微信加密签名  
	 private String timestamp;// 时间戳  
	 private String nonce;// 随机数  
	 private String echostr;// 随机字符串  
	 
	 public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	/** 
     * 确认请求来自微信服务器 
     */  
	 public void coreRequest(){
	        try {
				PrintWriter out = response.getWriter();  
				// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
				    out.print(echostr);  
				}  
				out.close();  
				out = null;
			} catch (Exception e) {
				e.printStackTrace();
			}  
	 }
}
