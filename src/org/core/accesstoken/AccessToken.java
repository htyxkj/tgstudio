package org.core.accesstoken;

public class AccessToken {
	// ��ȡ����ƾ֤  
    private String token;  
    // ƾ֤��Чʱ�䣬��λ����  
    private int expiresIn;
    // �ͻ���������ַ
    private String erviceURL;
    // �ͻ������id
    private String appid;
    // �ͻ�����ſ���������(AppSecret)
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
