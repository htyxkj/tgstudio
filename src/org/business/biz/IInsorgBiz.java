package org.business.biz;

import java.util.List;

import org.business.entity.Insorg;

public interface IInsorgBiz {
	//��ȡȫ����΢�ſͷ��ĵ����б�
	public List<Insorg> getWxALl()throws Exception;
	//��ȡȫ�������б�
	public List<Insorg> getALl()throws Exception;
}
