package org;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;









import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 public static void main(String[] args) {
		 System.out.println(new Test().cclm("å°"));;
		 System.out.println(new Test().cclm("äº"));;
		 System.out.println(new Test().cclm("äºº"));;
		 System.out.println(new Test().cclm("1111"));;
		 System.out.println(new Test().cclm("人"));;
	    }
	 
	    public boolean cclm(String source) {
	        boolean flag = true;
	        char ws[] = new char[] { '"', '?', ' ', '\'', '&' };
	        for (int i = 0; i < source.length(); i++) {
	            char c = source.charAt(i);
	            for (int j = 0; j < ws.length; j++) {
	                char v = ws[j];
	                if (c == v) {
	                    flag = false;
	                }
	            }
	            if ((int) c == 0xfffd) {
	                flag = false;
	            }
	        }
	        return flag;
	    }
	
	
	
//	   /**
//     * 判断字符是否是中文
//     *
//     * @param c 字符
//     * @return 是否是中文
//     */
//    public static boolean isChinese(char c) {
//        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
//        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
//                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
//            return true;
//        }
//        return false;
//    }
// 
//    /**
//     * 判断字符串是否是乱码
//     *
//     * @param strName 字符串
//     * @return 是否是乱码
//     */
//    public static boolean isMessyCode(String strName) {
//        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
//        Matcher m = p.matcher(strName);
//        String after = m.replaceAll("");
//        String temp = after.replaceAll("\\p{P}", "");
//        char[] ch = temp.trim().toCharArray();
//        float chLength = ch.length;
//        float count = 0;
//        for (int i = 0; i < ch.length; i++) {
//            char c = ch[i];
//            if (!Character.isLetterOrDigit(c)) {
//                if (!isChinese(c)) {
//                    count = count + 1;
//                }
//            }
//        }
//        float result = count / chLength;
//        if (result > 0.4) {
//            return true;
//        } else {
//            return false;
//        }
// 
//    }
// 
//    public static void main(String[] args) {
//        System.out.println(isMessyCode("44ç½"));
//        System.out.println(isMessyCode("你好"));
//    }
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String[] strArr = new String[] { "www.micmiu.com",
//				"!@#$%^&amp;*()_+{}[]|\"'?/:;&lt;&gt;,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊",
//				"やめて", "韩佳人", "한가인" };
//		for (String str : strArr) {
//			System.out.println("===========&gt; 测试字符串：" + str);
//			System.out.println("正则判断：" + isChineseByREG(str) + " -- "
//					+ isChineseByName(str));
//			System.out.println("Unicode判断结果 ：" + isChinese(str));
//			System.out.println("详细判断列表：");
//			char[] ch = str.toCharArray();
//			for (int i = 0; i< ch.length; i++) {
//				char c = ch[i];
//				System.out.println(c + " --&gt; " + (isChinese(c) ? "是" : "否"));
//			}
//		}
// 
//	}
// 
//	// 根据Unicode编码完美的判断中文汉字和符号
//	private static boolean isChinese(char c) {
//		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
//		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
//				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
//				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
//				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
//			return true;
//		}
//		return false;
//	}
// 
//	// 完整的判断中文汉字和符号
//	public static boolean isChinese(String strName) {
//		char[] ch = strName.toCharArray();
//		for (int i = 0; i < ch.length; i++) {
//			char c = ch[i];
//			if (isChinese(c)) {
//				return true;
//			}
//		}
//		return false;
//	}
// 
//	// 只能判断部分CJK字符（CJK统一汉字）
//	public static boolean isChineseByREG(String str) {
//		if (str == null) {
//			return false;
//		}
//		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
//		return pattern.matcher(str.trim()).find();
//	}
// 
//	// 只能判断部分CJK字符（CJK统一汉字）
//	public static boolean isChineseByName(String str) {
//		if (str == null) {
//			return false;
//		}
//		// 大小写不同：\\p 表示包含，\\P 表示不包含 
//		// \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
//		String reg = "\\p{InCJK Unified Ideographs}&amp;&amp;\\P{Cn}";
//		Pattern pattern = Pattern.compile(reg);
//		return pattern.matcher(str.trim()).find();
//	}
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
//	public static void main(String[] args) {
//		try {
//			String url1="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx515d2c7b6c6a2339&redirect_uri=";
//			String url2="http://www.bip-soft.com/tgstudio/marketService?kf_account=LiZhengguo@";
//			String url3="&response_type=code&scope=snsapi_base&state=tg123456789#wechat_redirect";
//			url2=URLEncoder.encode(url2,"UTF-8");
//			System.out.println(url1+url2+url3);
////			URL url = ClassLoader.getSystemResource("../../img/kefu.png");
////			System.out.println(url);
////		    File file = new File(url.getPath());
////			String filePath="";
////			File file = new File(filePath);
////			System.out.println(file.getPath());
////			if (!file.exists() || !file.isFile()) {
////				throw new IOException("文件不存在");
////			}
////		    System.out.println(Test.class.getResource("").getPath());
////			System.out.println(System.getProperty("user.dir"));
////			String token="lD6OdklZNK5TPsnpsl0CXLa306iEj_C3v9x8wnHCInAZZu4sqw1KERxP37P7M621xpPRBcgplavxfmD9SoEtSQJhwM-6Hz1ykTSeUKZZXWkPuwe-8IYvOs8efteNj6OMIVVeAGAZMM";
////			String url="https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+token;
////			String str="{\"kf_account\" : \"li_zhengguo@wx515d2c7b6c6a2339\",\"nickname\" : \"dd\"}";
////			
////			JSONObject jsonObj=WeixinUtil.httpsRequest(url, "POST", str);
////			System.out.println(jsonObj.toString());
////			String url="https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=_kzhO_NskPuURWtk7gVI1UOoUKrx0VuV2ksmXBbmF8SxUN7pb0KpYYXnDBhcrKqIr7tJsmGVWSlRphWgOFuRU2mSqXQZxlvhV9KhJsPzCr4CQVdADAJXC&kf_account=LiZhengguo@wx515d2c7b6c6a2339";
////			String filePath="F:/1.png";
////			String str=WeixinUtil.uploadMedia(url, filePath);
////			System.out.println(str);
////			String url="https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token="+token;
////			double doubleTime = (Math.floor(System.currentTimeMillis() / 60000L));
////	        //往下取整 1.9=> 1.0
////	        long floorValue = new Double(doubleTime).longValue()*60;
////			long endtime=floorValue;
////			long starttime=floorValue-(60*60*24);
////			Test t = new Test();
////			t.getChatRecord(url, starttime, endtime, 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
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