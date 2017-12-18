package org.business.biz.impl;


import org.business.biz.IReservationBiz;
import org.business.dao.IReservationDao;
import org.business.dao.impl.ReservationDaoImpl;

public class ReservationBizImpl implements IReservationBiz {
	IReservationDao res=new ReservationDaoImpl();
	/**
	 * 查询客户手机号是否存在于数据库呢
	 * @param tel 手机号
	 * @return 
	 * @throws Exception
	 */
	@Override
	public Integer getTel(String tel) throws Exception {
		return this.res.selectTel(tel);
	}
	

}
