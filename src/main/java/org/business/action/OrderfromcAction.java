package org.business.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.business.biz.IOrderfromcBiz;
import org.business.biz.IReservationBiz;
import org.business.biz.impl.OrderfromcBizImpl;
import org.business.biz.impl.ReservationBizImpl;
import org.business.entity.Message;
import org.business.entity.Orderfromc;
import org.business.entity.Reservation;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;

public class OrderfromcAction extends ActionSupport{
	protected static Logger log = Logger.getLogger(OrderfromcAction.class);
	private List<Orderfromc> listO=new ArrayList<Orderfromc>();
	private Message message=new Message();
	private String tel;
	public List<Orderfromc> getListO() {
		return listO;
	}
	public Message getMessage() {
		return message;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	/***
	 * 查询用户输入的手机号是否存在于系统内
	 */
	public String checkTel(){
		try {
			IReservationBiz res=new ReservationBizImpl();
			Reservation re=res.getTel(tel);
			if(Integer.parseInt(re.getTel())>0){
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
			IOrderfromcBiz order=new OrderfromcBizImpl();
			listO=order.getAll(tel);
			for(int i =0;i<listO.size();i ++){
				TokenThread t=new TokenThread();
				AccessToken acc=t.accessToken;
				String url=acc.getServiceURL() +"fileupdown?fud=1&rid=4&isweb=1&dbid="+acc.getDbid()+"&filepath=" + listO.get(i).getFj_root() + listO.get(i).getFj_name();
				listO.get(i).setFj_root(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mysong";
	}
}