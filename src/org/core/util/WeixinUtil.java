package org.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.core.accesstoken.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/** 
 * ��ȡaccess_token
 * ����https/http���󴫵����ݲ���ȡ���
 * @author lizhengguo
 * @date 2017-09-08 
 */  
public class WeixinUtil {
	
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��2000����/�죩  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 
     * ����https���󲢻�ȡ��� 
     *  
     * @param requestUrl �����ַ 
     * @param requestMethod ����ʽ��GET��POST�� 
     * @param outputStr �ύ������ 
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ) 
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
            // ��������ʽ��GET/POST��  
            httpUrlConn.setRequestMethod(requestMethod);  

            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // ����������Ҫ�ύʱ  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // ע������ʽ����ֹ��������  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }
            //�����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }
            bufferedReader.close();  
            inputStreamReader.close();  
            // �ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            log.error("Weixin���������ӳ�ʱ��");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    } 
    /** 
     * ����http���󲢻�ȡ��� 
     *  
     * @param requestUrl �����ַ 
     * @param requestMethod ����ʽ��GET��POST�� 
     * @param outputStr �ύ������ 
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ) 
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
            // ��������ʽ��GET/POST��  
            httpUrlConn.setRequestMethod(requestMethod);  

            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // ����������Ҫ�ύʱ  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // ע������ʽ����ֹ��������  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }
            //�����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }
            bufferedReader.close();  
            inputStreamReader.close();  
            // �ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            log.error("Weixin���������ӳ�ʱ��");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    } 
    /** 
     * ��ȡaccess_token 
     * @param appid ƾ֤ 
     * @param appsecret ��Կ 
     * @return 
     */  
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;  
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        //�������ɹ�
        if (null != jsonObject) {  
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (Exception e) {  
                accessToken = new AccessToken();  
                accessToken.setToken(jsonObject.getString("errcode"));
                // ��ȡtokenʧ��  
                log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
        return accessToken;  
    }  
}
