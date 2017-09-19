package org.business.biz.impl;

import java.util.List;

import org.business.biz.IOrderfromcBiz;
import org.business.dao.IOrderfromcDao;
import org.business.dao.impl.OrderfromcDaoImpl;
import org.business.entity.Orderfromc;

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

}
