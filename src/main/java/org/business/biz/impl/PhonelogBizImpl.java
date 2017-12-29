package org.business.biz.impl;

import org.business.biz.IPhonelogBiz;
import org.business.dao.IPhonelogDao;
import org.business.dao.impl.PhonelogDaoImpl;
import org.business.entity.Phonelog;

public class PhonelogBizImpl implements IPhonelogBiz {
	IPhonelogDao phone=new PhonelogDaoImpl();
	
	@Override
	public void addChat(Phonelog phone) throws Exception {
		this.phone.instChat(phone);
	}

}
