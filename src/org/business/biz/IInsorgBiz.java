package org.business.biz;

import java.util.List;

import org.business.entity.Insorg;

public interface IInsorgBiz {
	//获取全部有微信客服的店铺列表
	public List<Insorg> getWxALl()throws Exception;
	//获取全部店铺列表
	public List<Insorg> getALl()throws Exception;
}
