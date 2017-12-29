package org.business.quartzPackage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.business.biz.IWxchatBiz;
import org.business.biz.impl.WxchatBizImpl;
import org.business.entity.Wxchat;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.WeixinUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;



public class SMSRecord implements Job{
	protected static Logger log = Logger.getLogger(SMSRecord.class);
	/***
	 * 从微信服务器调取24小时内的聊天记录
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			log.info("获取短信验证码记录");
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			double doubleTime = (Math.floor(System.currentTimeMillis() / 60000L));
	        //往下取整 1.9=> 1.0
	        long floorValue = new Double(doubleTime).longValue()*60;
			long endtime=floorValue;
			long starttime=floorValue-(60*60*576);
			String requestUrl="http://m.5c.com.cn/api/query/index.php?username=bjstd&password_md5=7240835ee7502289c76f5e9bd4cddb8d&apikey=84d49d09365e4dd5800c366c42ced943&encode=UTF-8&action=record&from="+starttime+"&to="+endtime;
			this.getChatRecord(requestUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取短信验证码24小时内的记录
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求类型
	 * @param otputStr	传递数据
	 * @param starttime	开始时间
	 * @param endtime	结束时间
	 * @param msgid		开始条数
	 * @throws Exception 
	 */
	public void getChatRecord(String requestUrl) throws Exception{
		String requestMethod = "POST";
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
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
			log.info(buffer.toString());
		} catch (ConnectException ce) {
			log.error("SMS服务器连接超时。");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
	}
}
