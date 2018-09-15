package org.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 微信菜单栏查询返回结果
 * @author antgan
 *
 */
public class WxMenuResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private WxMenu menu;
	private List<WxMenu> conditionalmenu;

	public WxMenu getMenu() {
		return menu;
	}
	public void setMenu(WxMenu menu) {
		this.menu = menu;
	}
	
	public List<WxMenu> getConditionalmenu() {
		return conditionalmenu;
	}
	public void setConditionalmenu(List<WxMenu> conditionalmenu) {
		this.conditionalmenu = conditionalmenu;
	}
	@Override
	public String toString() {
		return "WxMenuResult [menu=" + menu + ", conditionalmenu=" + conditionalmenu + "]";
	}
}
