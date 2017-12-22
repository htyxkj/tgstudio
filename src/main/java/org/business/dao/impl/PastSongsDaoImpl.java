package org.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.business.dao.IPastSongsDao;
import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;
import org.business.entity.PastSongs;

public class PastSongsDaoImpl extends BaseDaoImpl<PastSongs> implements IPastSongsDao{
	//查询一页数据往期歌曲
	@Override
	public PageInfo<PastSongs> selOnePage(PageInfo<PastSongs> page)
			throws Exception {
		PastSongs pastSongs=page.getCondition();//条件对象
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<PastSongs> listo=new ArrayList<PastSongs>();
		
		String totalSQL="select count(sid) as sidnum from pastsongs where fj_name like ? or singname like ? or orname like ? ";
		statement = connection.prepareStatement(totalSQL);
		statement.setString(1, "%"+pastSongs.getSingname()+"%");
		statement.setString(2, "%"+pastSongs.getSingname()+"%");
		statement.setString(3, "%"+pastSongs.getSingname()+"%");
		resultSet = statement.executeQuery();
		Integer totalSize =0;
		if(resultSet.next())
			totalSize = resultSet.getInt("sidnum");//总条数
		Integer rows=(page.getCurrentPage()-1)* page.getPageSize();
		String dataSQL="select top "+page.getPageSize()+" sid,fj_root,fj_name,singname,orname from pastsongs where (fj_name like ? or singname like ? or orname like ?) and sid not in( select top "+rows+" sid from pastsongs where (fj_name like ? or singname like ? or orname like ?) order by sid desc) order by sid desc";
		statement = connection.prepareStatement(dataSQL);
		statement.setString(1, "%"+pastSongs.getSingname()+"%");
		statement.setString(2, "%"+pastSongs.getSingname()+"%");
		statement.setString(3, "%"+pastSongs.getSingname()+"%");
		statement.setString(4, "%"+pastSongs.getSingname()+"%");
		statement.setString(5, "%"+pastSongs.getSingname()+"%");
		statement.setString(6, "%"+pastSongs.getSingname()+"%");
		resultSet = statement.executeQuery();
		PastSongs pastSong=null;
		while (resultSet.next()) {
			pastSong=new PastSongs();
			pastSong=(PastSongs) this.Field(pastSong, resultSet);
			listo.add(pastSong);
		}
		page.setTotalSize(totalSize);
		page.setRows(listo);
		return page;
	}
	//根据编码查询往期歌曲
	@Override
	public PastSongs selOneSid(String sid) throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PastSongs pastsongs=null;
		try{
			String sql="select * from pastsongs  where sid=? ";
			statement = connection.prepareStatement(sql);
			statement.setString(1, sid);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				pastsongs=new PastSongs();
				pastsongs=(PastSongs) this.Field(pastsongs, resultSet);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeAll(connection, statement, resultSet);
		}
		return pastsongs;
	}

}
