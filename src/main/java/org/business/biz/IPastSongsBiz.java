package org.business.biz;

import org.business.entity.PageInfo;
import org.business.entity.PastSongs;

public interface IPastSongsBiz {
	//查询一页数据往期歌曲
	public PageInfo<PastSongs> getOnePage(PageInfo<PastSongs> info) throws Exception;
	//根据编码查询往期歌曲
	public PastSongs getOneSid(String sid) throws Exception;
}
