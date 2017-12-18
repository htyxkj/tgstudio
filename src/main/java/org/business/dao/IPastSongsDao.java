package org.business.dao;

import java.util.List;

import org.business.entity.PageInfo;
import org.business.entity.PastSongs;

public interface IPastSongsDao {
	//查询一页数据往期歌曲	
	public PageInfo<PastSongs> selOnePage(PageInfo<PastSongs> info) throws Exception;

	//根据编码查询往期歌曲
	public PastSongs selOneSid(String sid) throws Exception;
}
