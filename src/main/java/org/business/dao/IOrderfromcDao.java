package org.business.dao;

import java.util.List;

import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;

public interface IOrderfromcDao {
	/**
	 * 根据手机号查询用户歌单
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<Orderfromc> selectAll(String tel) throws Exception;
	/**
	 * 根据sid查询单个歌曲
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public Orderfromc selectOne(String sid) throws Exception;
	/**
	 * 分页查询用户歌单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Orderfromc> selPageAll(PageInfo<Orderfromc> page) throws Exception;
}
