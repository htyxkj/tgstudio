package org.business.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.IInsorgBiz;
import org.business.biz.impl.InsorgBizImpl;
import org.business.entity.Message;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.WeixinUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 连接客服会话
 * @author Administrator
 */
public class ServiceAction extends ActionSupport{
	protected static Logger log = Logger.getLogger(InsorgAction.class);
	private Message message=new Message();
	private String kf_account;
	private String openid;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Message getMessage() {
		return message;
	}
	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}

	public String service() {
		try {
			WeixinUtil weixUtil = new WeixinUtil();
			TokenThread t = new TokenThread();
			AccessToken accTok = t.accessToken;
			HttpSession session =  ServletActionContext.getRequest().getSession();
			log.info(session);
			// 向微信服务器发起请求类型 默认为POST请求 GET需要另行修改
			String requestMethod = "POST";
			// 服务号APPID
			String appid = accTok.getAppid();
			// 用户唯一标识 openid
			String openid = (String) session.getAttribute("openid");
			log.info(openid);
			log.info(accTok.getToken());
			String access_token = accTok.getToken() == null ? "" : accTok
					.getToken();
			log.info(kf_account + appid);
			// 发送客服请求地址
			String requestUrl = "https://api.weixin.qq.com/customservice/kfsession/create?access_token="
					+ access_token;
			JSONObject json = new JSONObject();
			json.put("kf_account", kf_account + appid);
			json.put("openid", openid);
			this.closeKF(kf_account + appid, openid, access_token);
			JSONObject jsonObj = weixUtil.httpsRequest(requestUrl,requestMethod, json.toString());
			log.info(jsonObj.toString());
			if (jsonObj.getIntValue("errcode") == 0) {
				// 发送问候语
				String whUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
						+ access_token;
				String str = "{\"touser\":\""
						+ openid
						+ "\",\"msgtype\":\"text\",\"text\":{\"content\":\"您好，很高兴为您服务!\"},\"customservice\":{\"kf_account\": \""
						+ kf_account + appid + "\"}}";
				log.info(str);
				JSONObject _jsonObj = weixUtil.httpsRequest(whUrl,requestMethod, str);
				log.info(_jsonObj.toString());
			}
			message.setErrcode(jsonObj.getString("errcode"));
			message.setErrmsg( jsonObj.getString("errmsg"));
		} catch (Exception e) {

		}
		return "msg";
	}
	/**
	 * 市场部客服，商务合作and投诉建议
	 */
	public String marketService() {
		try {
			log.info("openid"+openid);
			//查询市场部客服编码
			IInsorgBiz insorg=new InsorgBizImpl();
			String service=insorg.getM();
			service=service+"@";
			WeixinUtil weixUtil = new WeixinUtil();
			TokenThread t = new TokenThread();
			AccessToken accTok = t.accessToken;
			// 向微信服务器发起请求类型 默认为POST请求 GET需要另行修改
			String requestMethod = "POST";
			// 服务号APPID
			String appid = accTok.getAppid();
			// 客户服务号开发者密码appsecret
			log.info(accTok.getToken());
			String access_token = accTok.getToken() == null ? "" : accTok
					.getToken();
			log.info(service + appid);
			// 发送客服请求地址
			String requestUrl = "https://api.weixin.qq.com/customservice/kfsession/create?access_token="
					+ access_token;
			JSONObject json = new JSONObject();
			json.put("kf_account", service + appid);
			json.put("openid", openid);
			this.closeKF(kf_account + appid, openid, access_token);
			JSONObject jsonObj = weixUtil.httpsRequest(requestUrl,requestMethod, json.toString());
			log.info(jsonObj.toString());
			String content="";
			if (jsonObj.getIntValue("errcode") == 0) {
				content="您好，很高兴为您服务!";
			}else if(jsonObj.getIntValue("errcode") == 65415){
				content="当前指定客服未在线，请您稍后再来!";
			}else if(jsonObj.getIntValue("errcode") == 65414){
				content="您当前正在被其他客服接待,请回复“转接+店名”,客服稍后会为您服务!";
			}
			// 发送问候语
			String whUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
					+ access_token;
			String str = "{\"touser\":\""
					+ openid
					+ "\",\"msgtype\":\"text\",\"text\":{\"content\":\""+content+"\"},\"customservice\":{\"kf_account\": \""
					+ service + appid + "\"}}";
			log.info(str);
			JSONObject _jsonObj = weixUtil.httpsRequest(whUrl,requestMethod, str);
			log.info(_jsonObj.toString());
			message.setErrcode(jsonObj.getString("errcode"));
			message.setErrmsg( jsonObj.getString("errmsg"));
		} catch (Exception e) {
		}
		return "closeMS";
	}
	
	public void closeKF(String kf_account,String openid,String access_token){
		WeixinUtil weixUtil = new WeixinUtil();
		// 发送客服请求地址
		String requestUrl = "https://api.weixin.qq.com/customservice/kfsession/close?access_token="+ access_token;
		JSONObject json = new JSONObject();
		String requestMethod="POST";
		json.put("kf_account", kf_account);
		json.put("openid", openid);
		JSONObject jsonObj = weixUtil.httpsRequest(requestUrl,requestMethod, json.toString());
		log.info("结束客服会话："+jsonObj.getString("errcode"));
	}
}