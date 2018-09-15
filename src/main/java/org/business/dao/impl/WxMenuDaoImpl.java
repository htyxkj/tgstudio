package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.business.dao.IWxMenuDao;
import org.business.entity.PastSongs;
import org.business.entity.WxMenuButton;

public class WxMenuDaoImpl extends BaseDaoImpl<WxMenuButton> implements IWxMenuDao {
	//查询全部菜单信息
	@Override
	public List<WxMenuButton> selectAll() throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<WxMenuButton> listm=new ArrayList<WxMenuButton>();
		try {
			String totalSQL=" select * from wxmenu ";
			statement = connection.prepareStatement(totalSQL);
			resultSet = statement.executeQuery();
			WxMenuButton wxm=null;
			while(resultSet.next()){
				wxm=new WxMenuButton();
				wxm.setKey(resultSet.getString("key1"));
				wxm.setName(resultSet.getString("name"));
				wxm.setType(resultSet.getString("type"));
				wxm.setUrl(resultSet.getString("url"));
				listm.add(wxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, statement, resultSet);
		}
		return listm;
	}
}
