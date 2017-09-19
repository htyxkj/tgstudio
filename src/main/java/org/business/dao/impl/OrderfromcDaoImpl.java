package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.business.dao.IOrderfromcDao;
import org.business.entity.Orderfromc;

public class OrderfromcDaoImpl extends BaseDaoImpl<Orderfromc> implements IOrderfromcDao{
	/**
	 * 根据手机号查询用户歌单
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Orderfromc> selectAll(String tel) throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Orderfromc orderfromc=null;
		List<Orderfromc> listo=new ArrayList<Orderfromc>();
		try{
			String sql="select name,tel,fj_root,fj_name,singname from downladorder where tel=? order by mkdata desc";
			statement = connection.prepareStatement(sql);
			statement.setString(1, tel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				orderfromc=new Orderfromc();
				orderfromc=(Orderfromc) this.Field(orderfromc, resultSet);
				listo.add(orderfromc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return listo;
	}
}
