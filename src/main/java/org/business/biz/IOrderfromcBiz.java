package org.business.biz;

import java.util.List;

import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;
import org.business.entity.Reservation;

public interface IOrderfromcBiz {
	/**
	 * 根据手机号查询用户歌单
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<Orderfromc> getAll(String tel) throws Exception;
	/**
	 * 根据sid查询单个歌曲
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public Orderfromc getOne(String sid) throws Exception;
	/**
	 * 分页查询用户歌单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Orderfromc> getPageAll(PageInfo<Orderfromc> page) throws Exception;
	/**
	 * 查询所有符合条件的歌曲
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<Orderfromc> getAll(Orderfromc order)throws Exception;
}
