package org.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IBaseDao<T> {
	//��ȡ���ݿ�����
	public Connection getConnection() throws Exception;
	//�ر����ݿ�����
	public void closeAll(Connection connection,Statement statement,ResultSet resultSet)  throws Exception;
}
