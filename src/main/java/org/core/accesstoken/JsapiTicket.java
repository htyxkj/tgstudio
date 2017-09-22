package org.core.accesstoken;

public class JsapiTicket {
	private String ticket;//api_ticket，卡券接口中签名所需凭证
	private int expires_in;//有效时间
	private String errcode;//错误码
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
}
