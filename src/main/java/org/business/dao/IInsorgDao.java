package org.business.dao;

import java.util.List;

import org.business.entity.Insorg;

public interface IInsorgDao {
	//��ȡȫ����΢�ſͷ��ĵ����б�
	public List<Insorg> selectWxALl()throws Exception;
	//��ȡȫ�������б�
	public List<Insorg> selectALl()throws Exception;
}
