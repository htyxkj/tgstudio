package org.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.IInsorgBiz;
import org.business.biz.impl.InsorgBizImpl;
import org.business.entity.Insorg;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;

public class ContactAction  extends ActionSupport{
	/**
	 * 获取店铺信息
	 */
	protected static Logger log = Logger.getLogger(CallAction.class);
	List<Insorg> listI=new ArrayList<Insorg>();	
    public List<Insorg> getListI() {
		return listI;
	}
	public void setListI(List<Insorg> listI) {
		this.listI = listI;
	}
	public String contact() {
    	try {
    		TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			IInsorgBiz insorgB=new InsorgBizImpl();
			listI=insorgB.getALl();
			for (int i = 0; i < listI.size(); i++) {
				listI.get(i).setFj_root(acc.getFileURL()+"/db_"+acc.getDbid()+"/"+listI.get(i).getFj_root()+listI.get(i).getFj_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "contact";
    }
}
