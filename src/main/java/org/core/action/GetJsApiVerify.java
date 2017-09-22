package org.core.action;

import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.JsapiTicket;
import org.core.accesstoken.TokenThread;
import org.core.util.SignUtil;

import com.opensymphony.xwork2.ActionSupport;

public class GetJsApiVerify extends ActionSupport{
	 private static Logger log = Logger.getLogger(GetJsApiVerify.class); 
	 private Map<String, String> map =new HashMap<String, String>();
	 private String url;
	 public Map<String, String> getMap() {
		 return map;
	 }
	 public void setUrl(String url) {
		 this.url = url;
	 }
	/**
	 * 获取前端jsApi调用信息
	 */
	public String getApiVerifu(){
		log.info(url);
		TokenThread t=new TokenThread();
		JsapiTicket jsApi=t.jsapiTicket;
		AccessToken acc=t.accessToken;
		map = SignUtil.sign(jsApi.getTicket(), url);
		map.put("appid", acc.getAppid());
		log.info(map);
		return "jsApiSign";
	}
}
