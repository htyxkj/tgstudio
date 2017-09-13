package org.business.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.business.dao.IBaseDao;

public class BaseDaoImpl<T> implements IBaseDao<T>{
	Locale locale = Locale.getDefault();  
	ResourceBundle bundle = ResourceBundle.getBundle("db", locale);
	//��ȡ���ݿ�����
	public Connection getConnection() {
	    String className = bundle.getString("classname");
	    String url = bundle.getString("url");
	    String user = bundle.getString("user");
	    String password = bundle.getString("password");
		Connection connection=null;
		try {
			Class.forName(className);
			connection=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	//�ر����ݿ�����
	public void closeAll(Connection connection,Statement statement,ResultSet resultSet) {
		try {
			if(resultSet!=null){
			resultSet.close();
			}
			if (statement!=null) {
				statement.close();
			}
			if (connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������   Ϊ���Զ�̬��ֵ
	 * ʵ������������ ��������ݿ��ֶ� һ��  ����ȡ����ֵ
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public T Field(T t,ResultSet resultSet) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, NoSuchMethodException, SecurityException{
		Field[] field =t.getClass().getDeclaredFields(); // ��ȡʵ������������ԣ�����Field����
		 for (int j = 0; j < field.length; j++) { // ������������
             String name = field[j].getName(); // ��ȡ���Ե�����
             name = name.substring(0, 1).toUpperCase() + name.substring(1); // �����Ե����ַ���д�����㹹��get��set����
             String type = field[j].getGenericType().toString(); // ��ȡ���Ե�����
             if (type.equals("class java.lang.String")) { // ���type�������ͣ���ǰ�����"class "�����������
            	 Method m = t.getClass().getMethod("get" + name);
                 String value = (String) m.invoke(t);
                 if (value == null) {
                     m = t.getClass().getMethod("set"+name,String.class);
                     if(isExistColumn(resultSet, name)==true)
                     	m.invoke(t,resultSet.getString(name));
                 }
             }
             if (type.equals("class java.lang.Integer")) {
            	 Method m = t.getClass().getMethod("get" + name);
            	 Integer value = (Integer) m.invoke(t);
                 if (value == null) {
                     m = t.getClass().getMethod("set"+name,Integer.class);
                     if(isExistColumn(resultSet, name)==true)
                     	m.invoke(t,resultSet.getInt(name));
                 }
             }
             if (type.equals("class java.lang.Short")) {
            	 Method m = t.getClass().getMethod("get" + name);
            	 Short value = (Short) m.invoke(t);
                 if (value == null) {
                     m = t.getClass().getMethod("set"+name,Short.class);
                     if(isExistColumn(resultSet, name)==true)
                     	m.invoke(t,resultSet.getShort(name));
                 }
             }
             if (type.equals("class java.util.Date")) {
            	 Method m = t.getClass().getMethod("get" + name);
            	 java.util.Date value = (java.util.Date) m.invoke(t);
                 if (value == null) {
                     m = t.getClass().getMethod("set"+name,java.util.Date.class);
                     if(isExistColumn(resultSet, name)==true)
                    	 m.invoke(t,resultSet.getDate(name));
                 }
             }
             if (type.equals("class java.sql.Date")) {
            	 Method m = t.getClass().getMethod("get" + name);
            	 Date value = (Date) m.invoke(t);
                 if (value == null) {
                     m = t.getClass().getMethod("set"+name,Date.class);
                     if(isExistColumn(resultSet, name)==true)	
                    	 m.invoke(t,resultSet.getDate(name));
                 }
             }
             if(type.equals("class java.lang.Long")){
            	 Method m = t.getClass().getMethod("get" + name);
            	 Long value = (Long) m.invoke(t);
                 if (value == null) {
                     m = t.getClass().getMethod("set"+name,Long.class);
                     if(isExistColumn(resultSet, name)==true)
                    	 m.invoke(t,resultSet.getLong(name));
                 }
             }
         }
		 return t;
	}
	public boolean isExistColumn(ResultSet rs, String columnName) {  
	    try {  
	        if (rs.findColumn(columnName) > 0 ) {  
	            return true;  
	        }   
	    }  
	    catch (SQLException e) {  
	        return false;  
	    }  
	    return false;  
	}
}
