package org.business.dao;

import org.business.entity.Tell;

public interface ITellDao {
	/**
	 * 查询公司电话号码
	 */
	public Tell selectOne() throws Exception;
}
