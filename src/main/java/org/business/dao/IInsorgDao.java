package org.business.dao;

import java.util.List;

import org.business.entity.Insorg;

public interface IInsorgDao {
	//获取全部有微信客服的店铺列表
	public List<Insorg> selectWxALl()throws Exception;
	//获取全部店铺列表
	public List<Insorg> selectALl()throws Exception;
}
