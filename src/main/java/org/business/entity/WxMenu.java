package org.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 菜单栏
 * 详情：http://mp.weixin.qq.com/wiki/10/0234e39a2025342c17a7d23595c6b40a.html
 */
public class WxMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WxMenuButton> button = new ArrayList<WxMenuButton>();
	public List<WxMenuButton> getButton() {
		return button;
	}
	public void setButton(List<WxMenuButton> button) {
		this.button = button;
	}
}
