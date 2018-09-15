package org.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WxMenuButton  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String type;
	private String name;
	private String key;
	private String url;
	private List<WxMenuButton> sub_button = new ArrayList<WxMenuButton>();

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<WxMenuButton> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WxMenuButton> sub_button) {
		this.sub_button = sub_button;
	}

	@Override
	public String toString() {
		return "WxMenuButton [type=" + type + ", name=" + name + ", key=" + key + ", url=" + url + ", sub_button="
				+ sub_button + "]";
	}
}
