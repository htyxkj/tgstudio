package org.core.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;


public class GetTokenServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;  
    private static Logger log = Logger.getLogger(GetTokenServlet.class); 
    TokenThread tokenThread=new TokenThread();
    /**
     * 获取access_token标识（链接令牌）
     */
    public void init() throws ServletException {
            log.info("GetTokenAction init() 启动初始化方法，开始获取token信息...");
        	new Thread(new TokenThread()).start();
    }
}
