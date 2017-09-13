package org.core.accesstoken;

public class AccessToken {
	// 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;
    // 客户服务器地址
    private String erviceURL;
    // 客户服务号id
    private String appid;
    // 客户服务号开发者密码(AppSecret)
    private String appsecret;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getErviceURL() {
		return erviceURL;
	}
	public void setErviceURL(String erviceURL) {
		this.erviceURL = erviceURL;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
}
