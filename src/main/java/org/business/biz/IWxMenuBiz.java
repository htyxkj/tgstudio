package org.business.biz;

import java.util.List;
import org.business.entity.WxMenuButton;



public interface IWxMenuBiz {
	//查询全部按钮菜单
	public List<WxMenuButton> getAll() throws Exception;
}
