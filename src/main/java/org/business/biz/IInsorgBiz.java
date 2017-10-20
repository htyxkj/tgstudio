package org.business.biz;

import java.util.List;

import org.business.entity.Insorg;

public interface IInsorgBiz {
	//查询微信客服
	public List<Insorg> getWxALl()throws Exception;
	//查询全部部门
	public List<Insorg> getALl()throws Exception;
	//查询市场部微信客服
	public String getM()throws Exception;
}
