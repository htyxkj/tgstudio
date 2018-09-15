package org.business.biz.impl;

import java.util.List;

import org.business.biz.IWxMenuBiz;
import org.business.dao.IWxMenuDao;
import org.business.dao.impl.WxMenuDaoImpl;
import org.business.entity.WxMenuButton;

public class WxMenuBizImpl implements IWxMenuBiz {
	IWxMenuDao wxMenu = new WxMenuDaoImpl();
	//查询菜单列表
	@Override
	public List<WxMenuButton> getAll() throws Exception {
		return wxMenu.selectAll();
	}
}
