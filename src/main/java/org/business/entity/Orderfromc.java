package org.business.entity;

public class Orderfromc {
	private String singname;//演唱曲目
	private String fj_name;//附件名称
	private String fj_root;//附件路径
	private String name;//演唱者
	private String tel;//手机号
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
}
