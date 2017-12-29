package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.business.dao.IPhonelogDao;
import org.business.entity.Phonelog;

public class PhonelogDaoImpl extends BaseDaoImpl<Phonelog> implements IPhonelogDao {
	/**
	 * 添加短信记录
	 * @param chat
	 * @throws Exception
	 */
	@Override
	public void instChat(Phonelog phone) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection connection=this.getConnection();
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try{
			String sql="insert into phonelog(tel,number,submittime,sendtime,content,state,oid) values (?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, phone.getTel());
			statement.setInt(2, phone.getNumber());
			statement.setString(3, sdf.format(new Date()));
			statement.setString(4, sdf.format(new Date()));
			statement.setString(5, phone.getContent());
			statement.setInt(6, phone.getState());
			statement.setString(7, phone.getOid());
			statement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
	}

}
