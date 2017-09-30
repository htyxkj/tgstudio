package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.business.dao.IWxchatDao;
import org.business.entity.Reservation;
import org.business.entity.Wxchat;

public class WxchatDaoImpl extends BaseDaoImpl<Wxchat> implements IWxchatDao {
	/**
	 * 添加聊天记录
	 * @param chat
	 * @throws Exception
	 */
	@Override
	public void instChat(Wxchat chat) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection connection=this.getConnection();
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try{
			String sql="insert into wxchat(openid,nickname,opercode,text,mkdate) values (?,?,?,?,'"+sdf.format(chat.getMkdate())+"')";
			statement = connection.prepareStatement(sql);
			statement.setString(1, chat.getOpenid());
			statement.setString(2, chat.getNickname());
			statement.setString(3, chat.getOpercode());
			statement.setString(4, chat.getText());
			statement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
	}

}
