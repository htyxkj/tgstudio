package org.core.accesstoken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.core.util.WeixinUtil;


public class TokenThread implements Runnable{
	private static Logger log = Logger.getLogger(TokenThread.class); 
 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // 第三方用户唯一凭证  
    public static String appid;  
    // 第三方用户唯一凭证密钥  
    public static String appsecret;  
    // 客户服务器地址
    private String serviceURL;
    //数据库连接标识
    private String dbid;
    public static AccessToken accessToken = null;  
	public void run() {
		Locale locale = Locale.getDefault();  
		ResourceBundle bundle = ResourceBundle.getBundle("token", locale);
		appid = bundle.getString("appid");
		appsecret = bundle.getString("appsecret");
		serviceURL = bundle.getString("serviceURL");
		dbid = bundle.getString("dbid");
		while (true) {
            try {
            	//从数据库里拿每个公司微信配置信息
            		accessToken = WeixinUtil.getAccessToken(appid,appsecret);
            		if (null != accessToken) {
            			accessToken.setAppid(appid);
            			accessToken.setDbid(dbid);
            			accessToken.setAppsecret(appsecret);
            			accessToken.setServiceURL(serviceURL);
	                    log.info("获取access_token成功,获取时间:"+sdf.format(new Date())+",有效时长"+accessToken.getExpiresIn()+"秒 token:"+accessToken.getToken()+"");
	                }else {
	                	 //未获得链接令牌  休眠5秒后重新获取
		                 Thread.sleep(5000);  
					}
            	// 休眠7000秒  
                Thread.sleep(7000000);  
            }catch (Exception ex) {
            	try {
            		//出现异常10秒后重新获取
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
