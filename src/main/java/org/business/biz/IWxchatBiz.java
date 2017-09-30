package org.business.biz;

import org.business.entity.Wxchat;

public interface IWxchatBiz {
	/**
	 * 添加聊天记录
	 * @param chat
	 * @throws Exception
	 */
	public void addChat(Wxchat chat) throws Exception;
}
