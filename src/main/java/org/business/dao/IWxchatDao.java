package org.business.dao;

import org.business.entity.Wxchat;

public interface IWxchatDao {
	/**
	 * 添加聊天记录
	 * @param chat
	 * @throws Exception
	 */
	public void instChat(Wxchat chat) throws Exception;
}
