package org.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.IOrderfromcBiz;
import org.business.biz.IPastSongsBiz;
import org.business.biz.impl.OrderfromcBizImpl;
import org.business.biz.impl.PastSongsBizImpl;
import org.business.entity.Message;
import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;
import org.business.entity.PastSongs;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;

public class PastSongsAction extends ActionSupport{
	protected static Logger log = Logger.getLogger(OrderfromcAction.class);
	private List<PastSongs> listP=new ArrayList<PastSongs>();
	private PageInfo<PastSongs> page=new PageInfo<PastSongs>();
	private Message message=new Message();
	private String sid; //歌曲编号
	private PastSongs pastSongs;
	private Integer rows=10;//每页显示条数 默认10
	private Integer currentPage;//当前页数
	private Integer totalPage;//总页数
	private String fileUrl;
	private String dowUrl;
	private String condition;//条件
	private Orderfromc orderfc;//根据编码查询歌曲
	
	public Orderfromc getOrderfc() {
		return orderfc;
	}
	public List<PastSongs> getListP() {
		return listP;
	}
	public void setListP(List<PastSongs> listP) {
		this.listP = listP;
	}
	public PageInfo<PastSongs> getPage() {
		return page;
	}
	public void setPage(PageInfo<PastSongs> page) {
		this.page = page;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public PastSongs getPastSongs() {
		return pastSongs;
	}
	public void setPastSongs(PastSongs pastSongs) {
		this.pastSongs = pastSongs;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getDowUrl() {
		return dowUrl;
	}
	public void setDowUrl(String dowUrl) {
		this.dowUrl = dowUrl;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	//查询一页
	public String getOnePage(){
		try {
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			PastSongs pastSongs=new PastSongs();
			pastSongs.setSingname(condition);
			IPastSongsBiz pastbiz =new PastSongsBizImpl();
			page.setPageSize(rows);
			currentPage=currentPage==null?1:currentPage;
			page.setCurrentPage(currentPage);
			page.setCondition(pastSongs);
			page=pastbiz.getOnePage(page);
			this.fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
			page.setFileUrl(fileUrl);
			this.dowUrl=acc.getBipServiceURL() +"/fileupdown?fud=1&rid=4&isweb=1&dbid="+acc.getDbid()+"&filepath=";
			page.setDowUrl(dowUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "onePast";
	}
	//根据单个编码查询
	public String selOnePast(){
		TokenThread t=new TokenThread();
		AccessToken acc=t.accessToken;
		IPastSongsBiz pastbiz =new PastSongsBizImpl();
		try {
			PastSongs past=pastbiz.getOneSid(sid);
			if(past!=null){
				orderfc=new Orderfromc();
				orderfc.setFj_name(past.getFj_name());
				orderfc.setFj_root(past.getFj_root());
				orderfc.setSingname(past.getSingname());
				orderfc.setName(past.getOrname());
			}
			String singName=orderfc.getSingname();
			if(singName!=null&&!singName.equals(""))
			singName=singName.split("-")[0];
			orderfc.setSingname(singName);
			if(orderfc==null)
				return "error";
			this.fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
			this.dowUrl=acc.getBipServiceURL() +"/fileupdown?fud=1&rid=4&isweb=1&dbid="+acc.getDbid()+"&filepath=";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "oneSong";
	}
}
