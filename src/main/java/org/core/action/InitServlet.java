package org.core.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.business.quartzPackage.WeiXinChat;
import org.core.accesstoken.TokenThread;
import org.quartz.QuartzManager;



public class InitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;  
    private static Logger log = Logger.getLogger(InitServlet.class); 
    TokenThread tokenThread=new TokenThread();
    /**
     * 获取access_token标识（链接令牌）
     */
    public void init() throws ServletException {
            log.info("InitServlet init() 启动初始化方法，开始获取token信息...");
        	new Thread(new TokenThread()).start();
        	log.info("InitServlet init() 启动初始化方法，创建quartz定时任务,定时获取聊天记录...");
        	QuartzManager.addJob("getJL",WeiXinChat.class , "0 0 0 * * ?");//0 0 0 * * ?
        	QuartzManager.startJobs();
    }
}
