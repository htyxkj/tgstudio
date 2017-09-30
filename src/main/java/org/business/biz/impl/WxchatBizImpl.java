package org.business.biz.impl;

import org.business.biz.IWxchatBiz;
import org.business.dao.IWxchatDao;
import org.business.dao.impl.WxchatDaoImpl;
import org.business.entity.Wxchat;

public class WxchatBizImpl implements IWxchatBiz {
	IWxchatDao chat=new WxchatDaoImpl();
	@Override
	public void addChat(Wxchat chat) throws Exception {
		this.chat.instChat(chat);
	}


}
