package org.business.dao;

import org.business.entity.Reservation;

public interface IReservationDao {
	/**
	 * 查询客户手机号是否存在于数据库呢
	 * @param tel 手机号
	 * @return 
	 * @throws Exception
	 */
	public Integer selectTel(String tel) throws Exception;

}
