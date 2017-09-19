package org.business.biz;

import org.business.entity.Reservation;

public interface IReservationBiz {
	/**
	 * 查询客户手机号是否存在于数据库呢
	 * @param tel 手机号
	 * @return 
	 * @throws Exception
	 */
	public Reservation getTel(String tel) throws Exception;
}
