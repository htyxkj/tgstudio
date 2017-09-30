package org.business.entity;

import java.util.Date;

public class Wxchat {
	private String openid;
	private String nickname;
	private String opercode;
	private String text;
	private Date mkdate;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getMkdate() {
		return mkdate;
	}
	public void setMkdate(Date mkdate) {
		this.mkdate = mkdate;
	}
}
