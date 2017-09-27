package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.business.dao.IOrderfromcDao;
import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;

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
			String sql="select name,tel,fj_root,fj_name,singname,sid from downladorder where tel=? order by mkdata desc";
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
	/**
	 * 根据sid查询单个歌曲
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@Override
	public Orderfromc selectOne(String sid) throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Orderfromc orderfromc=null;
		try{
			String sql="select name,tel,fj_root,fj_name,singname,type from downladorder where sid=? order by mkdata desc";
			statement = connection.prepareStatement(sql);
			statement.setString(1, sid);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				orderfromc=new Orderfromc();
				orderfromc=(Orderfromc) this.Field(orderfromc, resultSet);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return orderfromc;
	}
	/**
	 * 分页查询用户歌单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<Orderfromc> selPageAll(PageInfo<Orderfromc> page)
			throws Exception {
		Orderfromc order=page.getCondition();//条件对象
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Orderfromc> listo=new ArrayList<Orderfromc>();
		
		String totalSQL="select count (sid) as sid from downladorder where tel=?";
		statement = connection.prepareStatement(totalSQL);
		statement.setString(1, order.getTel());
		resultSet = statement.executeQuery();
		Integer totalSize =0;
		if(resultSet.next())
			totalSize = resultSet.getInt("sid");//总条数
		Integer rows=(page.getCurrentPage()-1)* page.getPageSize();
		String dataSQL=" select top "+page.getPageSize()+" name,tel,fj_root,fj_name,singname,sid,type from downladorder where tel=? and sid not in( select top "+rows+" sid from downladorder order by mkdata desc) order by mkdata desc";
		statement = connection.prepareStatement(dataSQL);
		statement.setString(1, order.getTel());
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			order=new Orderfromc();
			order=(Orderfromc) this.Field(order, resultSet);
			listo.add(order);
		}
		page.setTotalSize(totalSize);
		page.setRows(listo);
		return page;
	}
	/**
	 * 查询所有符合条件的歌曲
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Orderfromc> selAll(Orderfromc order) throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Orderfromc orderfromc=null;
		List<Orderfromc> listo=new ArrayList<Orderfromc>();
		try{
			String sql="select name,tel,fj_root,fj_name,singname,sid from downladorder where type1=? order by mkdata desc";
			statement = connection.prepareStatement(sql);
			statement.setString(1, order.getType1());
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
