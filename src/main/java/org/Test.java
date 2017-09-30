package org;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.core.util.WeixinUtil;


public class Test {
//	public static void main(String[] args) {
//		try {
//			String url="http://127.0.0.1:8080/tgstudio/createKF";
//			Map<String, String> map=new HashMap<String, String>();
//			String[] value=new String[3];
//			value[0]="asd";
//			value[1]="asd";
//			value[2]="asd";
//			map.put("kf_content", value.toString());
//			Test.httpclient(url, map);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/** 
//     * 通过httpclient发起http请求并获取结果 
//     *  
//     * @param url 请求地址 
//     * @param Map 提交的数据 
//     * @return String 
//     */
//	public static String httpclient(String url,Map<String, String > map) throws Exception{
//			String strfhz = null;
//			CloseableHttpClient httpclient = HttpClients.createDefault();
//			HttpPost httpPost = new HttpPost(url);
//			CloseableHttpResponse response2 =null;
//		try {
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			//设置参数  
//	        Iterator iterator = map.entrySet().iterator();  
//	        while(iterator.hasNext()){  
//	            Entry<String,String> elem = (Entry<String, String>) iterator.next();  
//	            nvps.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
//	        }
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//			response2 = httpclient.execute(httpPost);
//		    HttpEntity entity2 = response2.getEntity();
//		    strfhz=EntityUtils.toString(entity2);
//		}catch(Exception e){
//		}finally {
//			response2.close();
//		}
//        return strfhz;
//	}
	public static void main(String[] args) {
		try {
//			String token="lD6OdklZNK5TPsnpsl0CXLa306iEj_C3v9x8wnHCInAZZu4sqw1KERxP37P7M621xpPRBcgplavxfmD9SoEtSQJhwM-6Hz1ykTSeUKZZXWkPuwe-8IYvOs8efteNj6OMIVVeAGAZMM";
//			String url="https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+token;
//			String str="{\"kf_account\" : \"li_zhengguo@wx515d2c7b6c6a2339\",\"nickname\" : \"dd\"}";
//			
//			JSONObject jsonObj=WeixinUtil.httpsRequest(url, "POST", str);
//			System.out.println(jsonObj.toString());
//			String url="https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=_kzhO_NskPuURWtk7gVI1UOoUKrx0VuV2ksmXBbmF8SxUN7pb0KpYYXnDBhcrKqIr7tJsmGVWSlRphWgOFuRU2mSqXQZxlvhV9KhJsPzCr4CQVdADAJXC&kf_account=LiZhengguo@wx515d2c7b6c6a2339";
//			String filePath="F:/1.png";
//			String str=WeixinUtil.uploadMedia(url, filePath);
//			System.out.println(str);
//			String url="https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token="+token;
//			double doubleTime = (Math.floor(System.currentTimeMillis() / 60000L));
//	        //往下取整 1.9=> 1.0
//	        long floorValue = new Double(doubleTime).longValue()*60;
//			long endtime=floorValue;
//			long starttime=floorValue-(60*60*24);
//			Test t = new Test();
//			t.getChatRecord(url, starttime, endtime, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
//	public void getChatRecord(String url,long starttime,long endtime,Integer msgid){
//		String str="{\"starttime\":"+starttime+",\"endtime\" : "+endtime+",\"msgid\" :"+msgid+" ,\"number\" : 10000}";
//		JSONObject jsonObj = WeixinUtil.httpsRequest(url, "POST", str);
//		if(jsonObj.getInt("number")==10000){
//			//可能还有数据为获取
//			this.getChatRecord(url, starttime, endtime, jsonObj.getInt("msgid"));
//		}
//		//opercode	操作码，2002（客服发送信息），2003（客服接收消息）
//		JSONArray jsonArr=jsonObj.getJSONArray("recordlist");
//		for(int i=0;i<jsonArr.size();i++){
//			JSONObject json=(JSONObject) jsonArr.get(i);
//			System.out.println(json.getString("time"));
//		}
//	}
}