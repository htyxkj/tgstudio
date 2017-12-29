package org.business.dao;

import org.business.entity.Phonelog;

public interface IPhonelogDao {
	/**
	 * 添加短信记录
	 * @param chat
	 * @throws Exception
	 */
	public void instChat(Phonelog phone) throws Exception;
}
