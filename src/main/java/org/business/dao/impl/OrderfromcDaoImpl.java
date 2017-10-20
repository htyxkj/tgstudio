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
			String sql="select orname,ortel,fj_root,fj_name,singname,stype from orderfromc  where onlyid=? order by mkdate desc";
			statement = connection.prepareStatement(sql);
			statement.setString(1, sid);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				orderfromc=new Orderfromc();
				orderfromc.setName(resultSet.getString("orname"));
				orderfromc.setTel(resultSet.getString("ortel"));
				orderfromc.setFj_name(resultSet.getString("fj_name"));
				orderfromc.setFj_root(resultSet.getString("fj_root"));
				orderfromc.setSingname(resultSet.getString("singname")); 
				orderfromc.setType(resultSet.getString("stype")); 
//				orderfromc=(Orderfromc) this.Field(orderfromc, resultSet);
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
		
		String totalSQL="select count (onlyid) as onlyid from orderfromc  where ortel=? and stype=? and isdown=1";
		statement = connection.prepareStatement(totalSQL);
		statement.setString(1, order.getTel());
		statement.setString(2, order.getType());
		resultSet = statement.executeQuery();
		Integer totalSize =0;
		if(resultSet.next())
			totalSize = resultSet.getInt("onlyid");//总条数
		Integer rows=(page.getCurrentPage()-1)* page.getPageSize();
		String dataSQL="select top "+page.getPageSize()+" orname,ortel,fj_root,fj_name, substring(fj_name,1,(LEN(fj_name)-CHARINDEX('.', REVERSE(fj_name)))) as singname,onlyid,stype from orderfromc where ortel=? and stype=? and isdown=1 and onlyid not in( select top "+rows+" onlyid from orderfromc where ortel=? and stype=? and isdown=1 order by mkdate desc) order by mkdate desc";
		statement = connection.prepareStatement(dataSQL);
		statement.setString(1, order.getTel());
		statement.setString(2, order.getType());
		statement.setString(3, order.getTel());
		statement.setString(4, order.getType());
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			order=new Orderfromc();
			//order=(Orderfromc) this.Field(order, resultSet);
			order.setName(resultSet.getString("orname"));
			order.setTel(resultSet.getString("ortel"));
			order.setFj_name(resultSet.getString("fj_name"));
			order.setFj_root(resultSet.getString("fj_root"));
			order.setSingname(resultSet.getString("singname"));
			order.setSid(resultSet.getString("onlyid"));
			order.setType(resultSet.getString("stype"));
			listo.add(order);
		}
		page.setTotalSize(totalSize);
		page.setRows(listo);
		return page;
	}
	/**
	 * 查询所有符合条件的歌曲 试听
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
			String sql="select orname,ortel,fj_root,fj_name,singname,onlyid from orderfromc  where stype=?  order by mkdate desc";
			statement = connection.prepareStatement(sql);
			statement.setString(1, order.getType());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				orderfromc=new Orderfromc();
//				orderfromc=(Orderfromc) this.Field(orderfromc, resultSet);
				orderfromc.setName(resultSet.getString("orname"));
				orderfromc.setTel(resultSet.getString("ortel"));
				orderfromc.setFj_name(resultSet.getString("fj_name"));
				orderfromc.setFj_root(resultSet.getString("fj_root"));
				orderfromc.setSingname(resultSet.getString("singname"));
				orderfromc.setSid(resultSet.getString("onlyid"));
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
