package org.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.JsapiTicket;

import net.sf.json.JSONObject;

/** 
 * 获取access_token
 * 发起https/http请求传递数据并获取结果
 * @author lizhengguo
 * @date 2017-09-08 
 */  
public class WeixinUtil {
	
	private static Logger log = Logger.getLogger(WeixinUtil.class);
	// 获取access_token的接口地址（GET） 限2000（次/天）  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 获取jsapi_ticket的接口
	public final static String jsapi_ticket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	/** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  

            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }
            //将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            log.error("Weixin服务器连接超时。");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    } 
    /** 
     * 发起http请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  

            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }
            //将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            log.error("Weixin服务器连接超时。");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    } 
    /** 
     * 获取access_token 
     * @param appid 凭证 
     * @param appsecret 密钥 
     * @return 
     */  
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;  
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        //如果请求成功
        if (null != jsonObject) {  
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (Exception e) {  
                accessToken = new AccessToken();  
                accessToken.setToken(jsonObject.getString("errcode"));
                // 获取token失败  
                log.error("获取token失败 errcode:{"+ jsonObject.getInt("errcode")+"} errmsg:{"+ jsonObject.getString("errmsg")+"}");
            }  
        }  
        return accessToken;  
    } 
    public static JsapiTicket getJsapiTicket(String accessTonke){
    	JsapiTicket jsapiTicket=null;
    	String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessTonke);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        //如果请求成功
        if (null != jsonObject) {  
            try {
            	jsapiTicket = new JsapiTicket();
            	jsapiTicket.setErrcode(jsonObject.getString("errcode"));
            	jsapiTicket.setTicket(jsonObject.getString("ticket"));
            	jsapiTicket.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (Exception e) {  
            	jsapiTicket = new JsapiTicket();  
            	jsapiTicket.setErrcode(jsonObject.getString("errcode"));
                // 获取token失败  
                log.error("获取jsapiTicket失败 errcode:{"+ jsonObject.getInt("errcode")+"} errmsg:{"+ jsonObject.getString("errmsg")+"}");
            }  
        }  
    	return jsapiTicket;
    }
}
