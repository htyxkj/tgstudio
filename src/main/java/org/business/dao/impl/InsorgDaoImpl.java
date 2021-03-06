package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.business.dao.IInsorgDao;
import org.business.entity.Insorg;


public class InsorgDaoImpl extends BaseDaoImpl<Insorg> implements IInsorgDao {
	
	//查询微信客服
	public List<Insorg> selectWxALl() throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Insorg insorg=null;
		List<Insorg> listI=new ArrayList<Insorg>();
		try{
			String sql="select orgcode,orgname,address,tel,service,fj_root,fj_name,epointx,epointy,worktime from insorg where scm is not null and service1 is not null and service is not null";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				insorg=new Insorg();
				insorg=(Insorg) this.Field(insorg, resultSet);
				listI.add(insorg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return listI;
	}
	//查询全部部门
	public List<Insorg> selectALl() throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Insorg insorg=null;
		List<Insorg> listI=new ArrayList<Insorg>();
		try{
			String sql="select orgcode,orgname,address,tel,service,fj_root,fj_name from insorg where scm is not null and corg<>0 ";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				insorg=new Insorg();
				insorg=(Insorg) this.Field(insorg, resultSet);
				listI.add(insorg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return listI;
	}
	//查询市场部微信客服
	@Override
	public String selM() throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String  service=null;
		try{
			String sql="select  service from insorg where orgname like '%市场部%' ";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				service=resultSet.getString("service");
			}
		}catch(Exception e){ 
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return service;
	}

}
