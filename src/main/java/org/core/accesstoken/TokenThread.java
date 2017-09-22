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
    // 客户服务器地址 wx
    private String serviceURL;
    // 	客户服务器地址 BIP 
    private String bipServiceURL;
    //数据库连接标识
    private String dbid;
    //附件地址
    private String fileURL;
    //AccessToken
    public static AccessToken accessToken = null;  
    //jsapi_ticket
    public static JsapiTicket jsapiTicket=null;
	public void run() {
		Locale locale = Locale.getDefault();  
		ResourceBundle bundle = ResourceBundle.getBundle("token", locale);
		appid = bundle.getString("appid");
		appsecret = bundle.getString("appsecret");
		serviceURL = bundle.getString("serviceURL");
		dbid = bundle.getString("dbid");
		bipServiceURL = bundle.getString("BIPServiceURL");
		fileURL=bundle.getString("fileURL");
		while (true) {
            try {
            		accessToken = WeixinUtil.getAccessToken(appid,appsecret);
            		if (null != accessToken) {
            			accessToken.setAppid(appid);
            			accessToken.setDbid(dbid);
            			accessToken.setAppsecret(appsecret);
            			accessToken.setServiceURL(serviceURL);
            			accessToken.setBipServiceURL(bipServiceURL);
            			accessToken.setFileURL(fileURL);
	                    log.info("获取access_token成功,获取时间:"+sdf.format(new Date())+",有效时长"+accessToken.getExpiresIn()+"秒 token:"+accessToken.getToken()+"");
	                    //获取.js jsapi_ticket  用于前端调用危险分享等功能
	                    jsapiTicket=WeixinUtil.getJsapiTicket(accessToken.getToken());
	                    log.info("获取jsapi_ticket成功,获取时间:"+sdf.format(new Date())+",有效时长"+jsapiTicket.getExpires_in()+"秒 token:"+jsapiTicket.getTicket()+"");
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
