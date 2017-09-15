package org.business.biz.impl;

import java.util.List;

import org.business.biz.IInsorgBiz;
import org.business.dao.IInsorgDao;
import org.business.dao.impl.InsorgDaoImpl;
import org.business.entity.Insorg;

public class InsorgBizImpl implements IInsorgBiz {
	IInsorgDao insdao=new InsorgDaoImpl();
	//��ȡȫ����΢�ſͷ��ĵ����б�
	public List<Insorg> getWxALl() throws Exception {
		return this.insdao.selectWxALl();
	}
	//��ȡȫ�������б�
	public List<Insorg> getALl() throws Exception {
		return this.insdao.selectALl();
	}

}
