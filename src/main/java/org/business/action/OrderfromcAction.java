package org.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.IOrderfromcBiz;
import org.business.biz.IReservationBiz;
import org.business.biz.impl.OrderfromcBizImpl;
import org.business.biz.impl.ReservationBizImpl;
import org.business.entity.Message;
import org.business.entity.Orderfromc;
import org.business.entity.PageInfo;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户歌曲列表
 * @author Administrator
 *
 */
public class OrderfromcAction extends ActionSupport{
	protected static Logger log = Logger.getLogger(OrderfromcAction.class);
	private List<Orderfromc> listO=new ArrayList<Orderfromc>();
	private PageInfo<Orderfromc> page=new PageInfo<Orderfromc>();
	private Message message=new Message();
	private String tel;	//手机号
	private String sid; //歌曲编号
	private Orderfromc orderfc;
	private Integer rows=10;//每页显示条数 默认10
	private Integer currentPage;//当前页数
	private Integer totalPage;//总页数
	private String type;//类型 m音乐vMV i图片封面
	private String fileUrl;
	private String dowUrl;
	
	public void setType(String type) {
		this.type = type;
	}
	public PageInfo<Orderfromc> getPage() {
		return page;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public List<Orderfromc> getListO() {
		return listO;
	}
	public Message getMessage() {
		return message;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Orderfromc getOrderfc() {
		return orderfc;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public String getDowUrl() {
		return dowUrl;
	}
	/***
	 * 查询用户输入的手机号是否存在于系统内
	 */
	public String checkTel(){
		try {
			IReservationBiz res=new ReservationBizImpl();
			Integer num=res.getTel(tel);
			if(num>0){
				message.setErrcode("0");
			}else{
				message.setErrcode("-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
	/**
	 * 根据手机号查询用户歌单
	 */
	public String selectAll(){
		try {
			HttpSession session =  ServletActionContext.getRequest().getSession();
			String tel1=(String) session.getAttribute("tel");
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			Orderfromc orderfc=new Orderfromc();
			orderfc.setTel(tel1);
			orderfc.setType(type);
			IOrderfromcBiz order =new OrderfromcBizImpl();
			page.setPageSize(rows);
			currentPage=currentPage==null?1:currentPage;
			page.setCurrentPage(currentPage);
			page.setCondition(orderfc);
			page=order.getPageAll(page);
			listO=page.getRows();
			this.fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
			this.dowUrl=acc.getBipServiceURL() +"/fileupdown?fud=1&rid=4&isweb=1&dbid="+acc.getDbid()+"&filepath=";
			currentPage=page.getCurrentPage();
			totalPage=page.getTotalPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(type.equals("B"))
			return "mymv";
		else if(type.equals("C"))
			return "myimg";
		else 
			return "mysong";
	}
	/***
	 * 查询下一页的歌曲
	 * @return
	 */
	public String nextPage(){
		try {
			HttpSession session =  ServletActionContext.getRequest().getSession();
			String tel1=(String) session.getAttribute("tel");
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			Orderfromc orderfc=new Orderfromc();
			orderfc.setTel(tel1);
			orderfc.setType(type);
			IOrderfromcBiz order =new OrderfromcBizImpl();
			page.setPageSize(rows);
			currentPage=currentPage==null?1:currentPage;
			page.setCurrentPage(currentPage);
			page.setCondition(orderfc);
			page=order.getPageAll(page);
			this.fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
			this.dowUrl=acc.getBipServiceURL() +"/fileupdown?fud=1&rid=4&isweb=1&dbid="+acc.getDbid()+"&filepath=";
			currentPage=page.getCurrentPage();
			totalPage=page.getTotalPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "nextpage";
	}
	/**
	 * 根据歌曲编码查询单个歌曲
	 */
	public String selOne(){
		TokenThread t=new TokenThread();
		AccessToken acc=t.accessToken;
		IOrderfromcBiz order=new OrderfromcBizImpl();
		try {
			orderfc=order.getOne(sid);
			String singName=orderfc.getSingname();
			singName=singName.split("-")[0];
			orderfc.setSingname(singName);
			if(orderfc==null)
				return "error";
			this.fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
			this.dowUrl=acc.getBipServiceURL() +"/fileupdown?fud=1&rid=4&isweb=1&dbid="+acc.getDbid()+"&filepath=";
			if(orderfc.getType().equals("A"))
				return "oneSong";
			if(orderfc.getType().equals("B"))
				return "oneMV";
			if(orderfc.getType().equals("C"))
				return "oneImg";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "oneSong";
	}
	/**
	 * 查询全部可试听歌曲
	 */
	public String Audition(){
		try {
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			this.fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
			IOrderfromcBiz orderf=new OrderfromcBizImpl();
			Orderfromc order=new Orderfromc();
			order.setType1("A");
			listO=orderf.getAll(order);
			Orderfromc element=new Orderfromc();
			element.setFj_root(fileUrl);
			listO.add(0, element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "audition";
	}
}