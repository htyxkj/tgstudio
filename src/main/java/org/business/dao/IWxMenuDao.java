package org.business.dao;

import java.util.List;

import org.business.entity.WxMenuButton;


public interface IWxMenuDao {
	/**
	 * 查询所有菜单
	 */
	public List<WxMenuButton> selectAll() throws Exception;
}
