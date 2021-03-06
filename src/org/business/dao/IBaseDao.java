package org.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IBaseDao<T> {
	//获取数据库连接
	public Connection getConnection() throws Exception;
	//关闭数据库连接
	public void closeAll(Connection connection,Statement statement,ResultSet resultSet)  throws Exception;
}
