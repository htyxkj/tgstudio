package org.business.entity;

public class Insorg {
	private String orgcode;//店铺编码
	private String orgname;//店铺名称
	private String scm;//上级部门
	private String corg;//公司
	private String service;//客服微信
	private String address;//地址
	private String tel;//联系方式
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getScm() {
		return scm;
	}
	public void setScm(String scm) {
		this.scm = scm;
	}
	public String getCorg() {
		return corg;
	}
	public void setCorg(String corg) {
		this.corg = corg;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}