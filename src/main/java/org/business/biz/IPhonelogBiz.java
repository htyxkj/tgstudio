package org.business.biz;

import org.business.entity.Phonelog;

public interface IPhonelogBiz {
	/**
	 * 添加短信发送记录记录
	 * @param chat
	 * @throws Exception
	 */
	public void addChat(Phonelog phone) throws Exception;
}
