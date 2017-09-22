package org.business.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.entity.Message;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.WeixinUtil;

import com.opensymphony.xwork2.ActionSupport;

public class ServiceAction extends ActionSupport{
	protected static Logger log = Logger.getLogger(InsorgAction.class);
	private Message message=new Message();
	private String kf_account;
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
			log.info("session");
			log.info(session);
			// 输出流
			// 向微信服务器发起请求类型 默认为POST请求 GET需要另行修改
			String requestMethod = "POST";
			// 服务号APPID
			String appid = accTok.getAppid();
			// 客户服务号开发者密码appsecret
			// String appsecret=accTok.getAppsecret();
			// 用户同意授权后，能获取到code
			// String code = request.getParameter("code");
			// 客服标识
			// 用户唯一标识 openid
			String openid = (String) session.getAttribute("openid");
			log.info(openid);
			// if (!"authdeny".equals(code) && code != null) {
			// //获取code后,请求以下链接获取access_token,以及用户 openid
			// String
			// acc_url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			// acc_url= acc_url.replace("APPID", appid).replace("SECRET",
			// appsecret).replace("CODE", code);
			// //{
			// "access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":"REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE"
			// }
			// JSONObject jsonObj=weixUtil.httpsRequest(acc_url,requestMethod,
			// null);
			// log.info(jsonObj.toString());
			// log.info(acc_url);
			// openid =jsonObj.getString("openid");
			// }
			log.info(accTok.getToken());
			String access_token = accTok.getToken() == null ? "" : accTok
					.getToken();
			log.info(kf_account + appid);
			// 获取客服未接待用户列表
			// String
			// url="https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token="+access_token;
			// String kf="{\"kf_account\":\""+kf_account+appid+"\"}";
			// JSONObject jsonObj= weixUtil.httpsRequest(url, "GET", kf);
			// log.info(jsonObj.toString());
			// 发送客服请求地址
			String requestUrl = "https://api.weixin.qq.com/customservice/kfsession/create?access_token="
					+ access_token;
			JSONObject json = new JSONObject();
			json.put("kf_account", kf_account + appid);
			json.put("openid", openid);
			JSONObject jsonObj = weixUtil.httpsRequest(requestUrl,requestMethod, json.toString());
			log.info(jsonObj.toString());
			if (jsonObj.getInt("errcode") == 0) {
				// 发送问候语
				String whUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
						+ access_token;
				String str = "{\"touser\":\""
						+ openid
						+ "\",\"msgtype\":\"text\",\"text\":{\"content\":\"您好，很高兴为您服务\"},\"customservice\":{\"kf_account\": \""
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
}