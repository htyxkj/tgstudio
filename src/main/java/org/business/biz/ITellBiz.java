package org.business.biz;


import org.business.entity.Tell;

public interface ITellBiz {
	/**
	 * 查询公司电话号码
	 */
	public Tell getOne() throws Exception;
}
