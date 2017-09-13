package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.business.dao.ITellDao;
import org.business.entity.Insorg;
import org.business.entity.Tell;

public class TellDaoImpl extends BaseDaoImpl<Tell> implements ITellDao {
	/**
	 * ≤È—ØµÁª∞∫≈¬Î
	 */
	@Override
	public Tell selectOne() throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Tell tell=null;
		try{
			String sql="select * from tell where mark =0";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tell=new Tell();
				tell=(Tell) this.Field(tell, resultSet);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return tell;
	}

}
