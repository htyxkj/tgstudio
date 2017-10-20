package org.business.dao;

import java.util.List;

import org.business.entity.Insorg;

public interface IInsorgDao {
	//查询微信客服
	public List<Insorg> selectWxALl()throws Exception;
	//查询全部部门
	public List<Insorg> selectALl()throws Exception;
	//查询市场部微信客服
	public String selM()throws Exception;
}
