package org.business.biz.impl;

import org.business.biz.ITellBiz;
import org.business.dao.ITellDao;
import org.business.dao.impl.TellDaoImpl;
import org.business.entity.Tell;

public class TellBizImpl implements ITellBiz {
	ITellDao telldao=new TellDaoImpl();
	/**
	 * 查询公司电话
	 */
	public Tell getOne() throws Exception {
		return this.telldao.selectOne();
	}
	
}
