package org.business.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.business.entity.Message;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.WeixinUtil;

public class CreateKfAction {
	private static Logger log = Logger.getLogger(CreateKfAction.class);
	private Message message = new Message();  
	private String kf_content;
	private String nickname;
	public void setKf_content(String kf_content) {
		this.kf_content = kf_content;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Message getMessage() {
		return message;
	}
	/**
	 * 更新创建客服账号
	 */
	public String createKF(){
		try {
			log.info("进行客服同步！");
			message.setErrcode("-1");
			HttpServletRequest request = ServletActionContext.getRequest();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");  
			BufferedReader br = new BufferedReader(isr);
			String jsonstr=br.readLine();
			jsonstr=URLDecoder.decode(jsonstr,"UTF-8");
	 		isr.close();
			is.close();
			JSONObject jsonObj_1=JSONObject.fromObject(jsonstr);
			TokenThread t=new TokenThread();
			AccessToken acc=t.accessToken;
			String token=acc.getToken();
			String appid=acc.getAppid();
			JSONArray jsonArr_1=jsonObj_1.getJSONArray("kf_list");//BIP平台提交的客服列表
			String errmsg="";
			for(int j=0;j<jsonArr_1.size();j++){
				JSONObject kfJSON=jsonArr_1.getJSONObject(j);
				String kf_account=kfJSON.getString("kf_account");
				String nickname= kfJSON.getString("nickname");
				String kfwx_account = kfJSON.getString("kfwx_account");
				JSONObject josnFH=this.addKF(kf_account,nickname,kfwx_account, token, appid);
				if(josnFH.getInt("errcode")!=0){
					errmsg+="["+josnFH.getString("errcode")+"],";
				}
			}
			if(errmsg.equals("")){
				message.setErrcode("0");
			}
			message.setErrmsg(errmsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
	//进行添加客服账号
	public JSONObject addKF(String kf,String name,String kfwx_account,String token,String appid) throws Exception{
		String outputStr="{\"kf_account\" : \""+kf+"@"+appid+"\",\"nickname\" : \""+name+"\"}";
		String url="https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+token;
		JSONObject jsonObj= WeixinUtil.httpsRequest(url, "POST", outputStr);
		log.info("执行创建步骤成功,返回码："+jsonObj.getString("errcode"));
		if(jsonObj.getInt("errcode")==0){//0添加客服成功
			url="https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token="+token;
			outputStr="{\"kf_account\" : \""+kf+"@"+appid+"\",\"invite_wx\" : \""+kfwx_account+"\" }";
			jsonObj=WeixinUtil.httpsRequest(url, "POST", outputStr);
			log.info("创建客服账号成功，进行邀请绑定，返回码："+jsonObj.getString("errcode")+"客服账号："+kf);
		}else if(jsonObj.getInt("errcode")==65406){//客服已存在  进行客服昵称修改
			url="https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token="+token;
			outputStr="{\"kf_account\" : \""+kf+"@"+appid+"\",\"invite_wx\" : \""+name+"\"}";
			jsonObj = WeixinUtil.httpsRequest(url, "POST", outputStr);
			log.info("客服存在，进行修改客服昵称,修改结果："+jsonObj.toString());
		}
		//设置客服头像
		this.uploadheadimg(kf, appid, token);
		return jsonObj;
	}
	//上传客服头像
	public void uploadheadimg(String kf,String appid,String token) throws Exception{
		TokenThread t=new TokenThread();
		AccessToken acc=t.accessToken;
		String url="https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token="+token+"&kf_account="+kf+"@"+appid;
		String filePath=acc.getFileURL()+"/db_"+acc.getDbid()+"/kefu.png";
		String str=WeixinUtil.uploadMedia(url, filePath);
		log.info("设置客服头像"+str);
	}
}