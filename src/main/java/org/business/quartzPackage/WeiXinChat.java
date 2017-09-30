package org.business.quartzPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.business.biz.IWxchatBiz;
import org.business.biz.impl.WxchatBizImpl;
import org.business.entity.Wxchat;
import org.core.accesstoken.AccessToken;
import org.core.util.WeixinUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WeiXinChat implements Job{
	/***
	 * 从微信服务器调取24小时内的聊天记录
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			AccessToken acc=new AccessToken();
			double doubleTime = (Math.floor(System.currentTimeMillis() / 60000L));
	        //往下取整 1.9=> 1.0
	        long floorValue = new Double(doubleTime).longValue()*60;
			long endtime=floorValue;
			long starttime=floorValue-(60*60*24);
			String requestUrl="https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token="+acc.getToken();
			String requestMethod="POST";
			String otputStr="";
			this.getChatRecord(requestUrl, requestMethod, otputStr, starttime, endtime, 1,  acc.getToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取微信24小时内的聊天记录
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求类型
	 * @param otputStr	传递数据
	 * @param starttime	开始时间
	 * @param endtime	结束时间
	 * @param msgid		开始条数
	 * @throws Exception 
	 */
	public void getChatRecord(String requestUrl,String requestMethod,String otputStr,long starttime,long endtime,Integer msgid,String token) throws Exception{
		otputStr="{\"starttime\":"+starttime+",\"endtime\" : "+endtime+",\"msgid\" :"+msgid+" ,\"number\" : 10000}";
		JSONObject jsonObj = WeixinUtil.httpsRequest(requestUrl, "POST", otputStr);
		if(jsonObj.containsKey("number"))
		if(jsonObj.getInt("number")==10000){
			//可能还有数据为获取
			this.getChatRecord(requestUrl,requestMethod,otputStr, starttime, endtime, jsonObj.getInt("msgid"),token);
		}
		//opercode	操作码，2002（客服发送信息），2003（客服接收消息）
		if(jsonObj.containsKey("recordlist")){
			JSONArray jsonArr=jsonObj.getJSONArray("recordlist");
			String openid="";
			String nickname="";
//			List<Wxchat> listChat=new ArrayList<Wxchat>();
			Wxchat chat=null;
			for(int i=0;i<jsonArr.size();i++){
				JSONObject json=(JSONObject) jsonArr.get(i);
				if(!openid.equals(json.getString("openid"))){
					openid=json.getString("openid");
					nickname = this.getWxUser(openid, token);
				}
				chat=new Wxchat();
				chat.setNickname(nickname);
				chat.setOpenid(openid);
				chat.setOpercode(json.getString("opercode"));
				chat.setText(json.getString("text"));
				Long timestamp = json.getLong("time")*1000;
	  		    String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
	  		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  		    Date d=sdf.parse(date);
				chat.setMkdate(d);
				IWxchatBiz chatBiz=new WxchatBizImpl();
				chatBiz.addChat(chat);
//				listChat.add(chat);
			}
		}
	}
	/**
	 * 获取微信用户昵称
	 * @param openid 用户唯一标识
	 * @param token	 token码
	 * @return
	 */
	public String getWxUser(String openid,String token){
		String requestUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openid+"&lang=zh_CN";
		String requestMethod="GET";
		JSONObject jsonobj = WeixinUtil.httpsRequest(requestUrl, requestMethod, "");
		if(jsonobj.has("nickname")){
			return jsonobj.getString("nickname");
		}
		return "";
	}
}
