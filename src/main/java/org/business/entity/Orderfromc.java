package org.business.entity;

public class Orderfromc {
	private String sid;//歌曲唯一码
	private String singname;//演唱曲目
	private String fj_name;//附件名称
	private String fj_root;//附件下载路径
	private String name;//演唱者
	private String tel;//手机号
	private String type;//类型  音频 视频      m/v
	private String type1;// 可否试听  0t/1f
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSingname() {
		return singname;
	}
	public void setSingname(String singname) {
		this.singname = singname;
	}
	public String getFj_name() {
		return fj_name;
	}
	public void setFj_name(String fj_name) {
		this.fj_name = fj_name;
	}
	public String getFj_root() {
		return fj_root;
	}
	public void setFj_root(String fj_root) {
		this.fj_root = fj_root;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
}
