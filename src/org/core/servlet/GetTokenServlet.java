package org.core.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.core.accesstoken.TokenThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    private static Logger log = LoggerFactory.getLogger(GetTokenServlet.class); 
    TokenThread tokenThread=new TokenThread();
    /**
     * ��ȡaccess_token��ʶ���������ƣ�
     */
    public void init() throws ServletException {
            log.info("GetTokenServlet init() ������ʼ����������ʼ��ȡtoken��Ϣ...");
        	new Thread(new TokenThread()).start();
    }
}
