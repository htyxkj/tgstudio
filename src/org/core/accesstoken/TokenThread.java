package org.core.accesstoken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.core.servlet.CoreServlet;
import org.core.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TokenThread implements Runnable{
	private static Logger log = LoggerFactory.getLogger(CoreServlet.class); 
 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // �������û�Ψһƾ֤  
    public static String appid;  
    // �������û�Ψһƾ֤��Կ  
    public static String appsecret;  
    // �ͻ���������ַ
    private String erviceURL;
    public static AccessToken accessToken = null;  
	@Override
	public void run() {
		Locale locale = Locale.getDefault();  
		ResourceBundle bundle = ResourceBundle.getBundle("token", locale);
		appid = bundle.getString("appid");
		appsecret = bundle.getString("appsecret");
		erviceURL = bundle.getString("erviceURL");
		
		while (true) {
            try {
            	//�����ݿ�����ÿ����˾΢��������Ϣ
            		accessToken = WeixinUtil.getAccessToken(appid,appsecret);
            		if (null != accessToken) {
            			accessToken.setAppid(appid);
            			accessToken.setAppsecret(appsecret);
            			accessToken.setErviceURL(erviceURL);
	                    log.info("��ȡaccess_token�ɹ�,��ȡʱ��:"+sdf.format(new Date())+",��Чʱ��"+accessToken.getExpiresIn()+"�� token:"+accessToken.getToken()+"");
	                }else {
	                	 //δ�����������  ����5������»�ȡ
		                 Thread.sleep(5000);  
					}
            	// ����7000��  
                Thread.sleep(7000000);  
            }catch (Exception ex) {
            	try {
            		//�����쳣10������»�ȡ
					Thread.sleep(10000);
					StackTraceElement stackTraceElement= ex.getStackTrace()[0];
					log.error("File="+stackTraceElement.getFileName());
					log.error("Line="+stackTraceElement.getLineNumber());
					log.error("Method="+stackTraceElement.getMethodName());
					log.error("https request error:{}", ex);  
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}  
        }
	}

}
