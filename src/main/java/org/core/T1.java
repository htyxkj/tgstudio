package org.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

public class T1 {
 public static void main(String[] args) {
	 String requestMethod = "POST"; 
//	 String requestUrl="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=43uzvzowVPv2WULUA4xakVVUtr9jD0qqIXS4J6jmBGLqg-_hULn1G-aEt1FDHjjsQwKD7IrfXsZfgleCKUVmRedjsIib3Ol6YxzYhX_ePHN6B4p9r494A_FfpWJDQg_yRJNfAAAMEI";
//	 String outputStr ="{\"kf_account\" : \"LiZhengguo@wx515d2c7b6c6a2339\",\"nickname\" : \"�ͷ�1\" }";
//	 String requestUrl="https://api.weixin.qq.com/customservice/kfsession/create?access_token=43uzvzowVPv2WULUA4xakVVUtr9jD0qqIXS4J6jmBGLqg-_hULn1G-aEt1FDHjjsQwKD7IrfXsZfgleCKUVmRedjsIib3Ol6YxzYhX_ePHN6B4p9r494A_FfpWJDQg_yRJNfAAAMEI";
//	 String outputStr ="{\"kf_account\":\"LiZhengguo@wx515d2c7b6c6a2339\",\"openid\" : \"o4BQkwDwIrfx-Y1VwAC8ilcT8jNA\"}";
	 String requestUrl="https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=_ZASmmL-C9cWYTb2k4OfBHybmA4zQWTqWIgaupL6Djr3P4GQxoqFsSCcyaUW6AgCZhQadSvLqJvNhIH6tw-aj4-03DKKa79SnjvOKhn3qTFPHvXWhECAtv2DlJqRW8XPPDSgAGAGUJ";
	 String outputStr=null;
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
//         jsonObject = JSONObject.fromObject(buffer.toString());  
         System.out.println(buffer.toString());
     } catch (ConnectException ce) {  
     } catch (Exception e) {  
     }  
 }
}
