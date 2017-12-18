package org.business.entity;

public class PastSongs {
	private String sid;//编号
	private String fj_root;//附件路径
	private String fj_name;//附件名称
	private String singname;//歌曲名称
	private String orname;//演唱者
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getFj_root() {
		return fj_root;
	}
	public void setFj_root(String fj_root) {
		this.fj_root = fj_root;
	}
	public String getFj_name() {
		return fj_name;
	}
	public void setFj_name(String fj_name) {
		this.fj_name = fj_name;
	}
	public String getSingname() {
		return singname;
	}
	public void setSingname(String singname) {
		this.singname = singname;
	}
	public String getOrname() {
		return orname;
	}
	public void setOrname(String orname) {
		this.orname = orname;
	}
}
