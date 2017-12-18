package org.business.biz.impl;

import java.util.List;

import org.business.biz.IPastSongsBiz;
import org.business.dao.IPastSongsDao;
import org.business.dao.impl.PastSongsDaoImpl;
import org.business.entity.PageInfo;
import org.business.entity.PastSongs;

public class PastSongsBizImpl implements IPastSongsBiz{
	
	IPastSongsDao pastDao = new PastSongsDaoImpl();
	//查询一页往期歌曲
	@Override
	public PageInfo<PastSongs> getOnePage(PageInfo<PastSongs> info) throws Exception {
		return this.pastDao.selOnePage(info);
	}
	//查询一个往期歌曲
	@Override
	public PastSongs getOneSid(String sid) throws Exception {
		return this.pastDao.selOneSid(sid);
	}

}
