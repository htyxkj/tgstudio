package org.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.xslt.ArrayAdapter;
import org.business.biz.IInsorgBiz;
import org.business.biz.impl.InsorgBizImpl;
import org.business.entity.Insorg;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.WeixinUtil;

import com.opensymphony.xwork2.ActionSupport;

public class InsorgAction extends ActionSupport {
	/**
	 * 查询店铺列表
	 */
	protected static Logger log = Logger.getLogger(InsorgAction.class);
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();
    List<Insorg> listI=new ArrayList<Insorg>();
    public List<Insorg> getListI() {
		return listI;
	}

	public String insorg() {
    	try {
			Insorg insorg=null;
			WeixinUtil weixUtil=new WeixinUtil();
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			IInsorgBiz insorgB=new InsorgBizImpl();
			listI=insorgB.getWxALl();
			//客户服务器地址
			String serviceURL=acc.getErviceURL();
			//服务号APPID
			String appid=acc.getAppid();
			//客户服务号开发者密码appsecret
			String appsecret=acc.getAppsecret();
			//用户同意授权后，能获取到code
			String code = request.getParameter("code");
			String openid="";
			if (!"authdeny".equals(code) && code != null) {
				//获取code后,请求以下链接获取access_token,以及用户 openid
				String acc_url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
				acc_url= acc_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
				//{ "access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":"REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE" } 
				JSONObject jsonObj=weixUtil.httpsRequest(acc_url,"POST", null);
				log.info(jsonObj.toString());
				log.info(acc_url);
				openid = jsonObj.getString("openid");
			}
			HttpSession session = request.getSession();
			session.setAttribute("openid", openid);
			String kurl=acc.getErviceURL()+"/service?kf_account=";
			String getURL="https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token="+acc.getToken();
			for(int i = 0; i < listI.size(); i ++){
				insorg=new Insorg();
				insorg=listI.get(i);
				String kf=insorg.getService()+"@";
				kurl = kurl+kf;
				listI.get(i).setService(kurl);
				JSONObject jsonObj= weixUtil.httpsRequest(getURL, "GET",null);
				if(jsonObj.containsKey("kf_online_list")){
//					if(jsonObj.getJSONArray("kf_online_list").size()==0){
//						listI.get(i).setService("");
//					}
//					for( int j = 0;j < jsonObj.getJSONArray("kf_online_list").size(); j ++){
//						JSONObject json_Obj=(JSONObject) jsonObj.getJSONArray("kf_online_list").get(j);
//						if(json_Obj.getString("kf_account").equals(kf)){
//							if(json_Obj.getInt("kf_account")==1){
//								
//							}else{
//								listI.get(i).setService("");	
//							}
//						}
//					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "insorg";
    }
}
