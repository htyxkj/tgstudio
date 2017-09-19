package org.business.biz.impl;

import java.util.List;

import org.business.biz.IInsorgBiz;
import org.business.dao.IInsorgDao;
import org.business.dao.impl.InsorgDaoImpl;
import org.business.entity.Insorg;

public class InsorgBizImpl implements IInsorgBiz {
	IInsorgDao insdao=new InsorgDaoImpl();
	//查询微信客服
	public List<Insorg> getWxALl() throws Exception {
		return this.insdao.selectWxALl();
	}
	//查询全部部门
	public List<Insorg> getALl() throws Exception {
		return this.insdao.selectALl();
	}

}
