package org.business.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;

public class URLAction extends ActionSupport{
	private String kefu;//客服
	private String quge;//取歌
	private String qumv;//取MV
	private String imgurl;//封面
	private String shiting;//试听
	private String bohao;//拨号
	private String lianxiwm;//联系我们
	private String coerURL;//微信服务器认证地址
	private String marketS;//市场部客服
	
	public String getImgurl() {
		return imgurl;
	}
	public String getMarketS() {
		return marketS;
	}
	public String getCoerURL() {
		return coerURL;
	}
	public String getQumv() {
		return qumv;
	}
	public String getKefu() {
		return kefu;
	}
	public String getQuge() {
		return quge;
	}
	public String getShiting() {
		return shiting;
	}
	public String getBohao() {
		return bohao;
	}
	public String getLianxiwm() {
		return lianxiwm;
	}
	public String getURL(){
		try {
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			String service=acc.getServiceURL();
			quge=service+"/jsp/song/getSong.jsp";
			qumv=service+"/jsp/song/getMV.jsp";
			imgurl=service+"/jsp/song/getImg.jsp";
			shiting=service+"/jsp/song/audition.jsp";
			bohao=service+"/call";
			lianxiwm=service+"/contact";
			coerURL=service+"/core";
			String url1="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+acc.getAppid()+"&redirect_uri=";
			String url2=service+"/insorg";
			String url3="&response_type=code&scope=snsapi_base&state=tg123456789#wechat_redirect";
			url2=URLEncoder.encode(url2,"UTF-8");
			kefu=url1+url2+url3;
			String marketService=service+"/marketService";
			marketService=URLEncoder.encode(marketService,"UTF-8");
			marketS=url1+marketService+url3;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "url";
	}
}
