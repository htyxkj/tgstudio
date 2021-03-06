package org.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.biz.IInsorgBiz;
import org.business.biz.impl.InsorgBizImpl;
import org.business.entity.Insorg;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;

import com.opensymphony.xwork2.ActionSupport;

public class InsorgAction extends ActionSupport {
	/**
	 * 查询店铺列表
	 */
	protected static Logger log = Logger.getLogger(InsorgAction.class);
    private String kfURL;
    private String fileUrl;
    private String openid;
    List<Insorg> listI=new ArrayList<Insorg>();
    public List<Insorg> getListI() {
		return listI;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public String getKfURL() {
		return kfURL;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOpenid() {
		return openid;
	}
	public String insorg() {
    	try {
    		log.info("openid"+openid);
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			IInsorgBiz insorgB=new InsorgBizImpl();
			listI=insorgB.getWxALl();
			log.info(listI.size());
			HttpSession session =  ServletActionContext.getRequest().getSession();
			session.setAttribute("openid", openid);
			kfURL=acc.getServiceURL()+"/service?kf_account=";
			fileUrl=acc.getFileURL()+"/db_"+acc.getDbid()+"/";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "insorg";
    }
	
}
