package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.business.dao.IReservationDao;
import org.business.entity.Reservation;

public class ReservationDaoImpl extends BaseDaoImpl<Reservation> implements IReservationDao {
	/**
	 * 查询客户手机号是否存在于数据库呢
	 * @param tel 手机号
	 * @return 
	 * @throws Exception
	 */
	@Override
	public Reservation selectTel(String tel) throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Reservation reservation=null;
		try{
			String sql="select count(tel) as tel from reservation where tel=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, tel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				reservation=new Reservation();
				reservation=(Reservation) this.Field(reservation, resultSet);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return reservation;
	}
}
