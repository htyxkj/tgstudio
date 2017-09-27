package org.business.biz.impl;

import java.util.List;

import org.business.biz.IOrderfromcBiz;
import org.business.dao.IOrderfromcDao;
import org.business.dao.impl.OrderfromcDaoImpl;
import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;

public class OrderfromcBizImpl implements IOrderfromcBiz {
	IOrderfromcDao order=new OrderfromcDaoImpl();
	/**
	 * 根据手机号查询用户歌单
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Orderfromc> getAll(String tel) throws Exception {
		return this.order.selectAll(tel);
	}
	/**
	 * 根据sid查询单个歌曲
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@Override
	public Orderfromc getOne(String sid) throws Exception {
		return this.order.selectOne(sid);
	}
	/**
	 * 分页查询用户歌单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<Orderfromc> getPageAll(PageInfo<Orderfromc> page)
			throws Exception {
		return this.order.selPageAll(page);
	}
	/**
	 * 查询所有符合条件的歌曲
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Orderfromc> getAll(Orderfromc order) throws Exception {
		return this.order.selAll(order);
	}

}
