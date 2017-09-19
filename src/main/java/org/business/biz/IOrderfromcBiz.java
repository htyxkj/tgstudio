package org.business.biz;

import java.util.List;

import org.business.entity.Orderfromc;
import org.business.entity.Reservation;

public interface IOrderfromcBiz {
	/**
	 * 根据手机号查询用户歌单
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<Orderfromc> getAll(String tel) throws Exception;
}
