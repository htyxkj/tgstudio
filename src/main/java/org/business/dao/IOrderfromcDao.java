package org.business.dao;

import java.util.List;

import org.business.entity.Orderfromc;

public interface IOrderfromcDao {
	/**
	 * 根据手机号查询用户歌单
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<Orderfromc> selectAll(String tel) throws Exception;
}
