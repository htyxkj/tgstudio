package org.sms.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class XcodeValidTool extends Thread {
	private static final Log log = LogFactory.getLog(XcodeValidTool.class);
	public HashMap<String, String> _keycode = new HashMap<String, String>();
	public HashMap<String, Long> _keycodeValidt = new HashMap<String, Long>();

	public String getXcode(String phone,String content){
		String xcode = SMSTool.createRandom(true, 5);
		String phon = phone;
		content=content.replaceAll("\\[code\\]", xcode);
		String result = SendIM(phon,content);
//		String result = "success:ok";
		if(result.startsWith("success")){
			if(_keycode.containsKey(phon)){
				String xx = _keycode.get(phon);
				_keycodeValidt.remove(phon+"_"+xx);
			}
			_keycode.put(phon, xcode);
			Calendar c = Calendar.getInstance();
//			c.add(Calendar.MINUTE, 60000);
			c.add(Calendar.MINUTE, 10);
			long edd = c.getTimeInMillis();
			_keycodeValidt.put(phon + "_" + xcode, edd);
		}
		return result;
	}
	public static String SendIM(String phonnum, String content) {
		// 连接超时及读取超时设置
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000"); // 连接超时：30秒
		System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时：30秒
		// 新建一个StringBuffer链接
		StringBuffer buffer = new StringBuffer();
		// String encode = "GBK";
		// //页面编码和短信内容编码为GBK。重要说明：如提交短信后收到乱码，请将GBK改为UTF-8测试。如本程序页面为编码格式为：ASCII/GB2312/GBK则该处为GBK。如本页面编码为UTF-8或需要支持繁体，阿拉伯文等Unicode，请将此处写为：UTF-8
		String encode = "UTF-8";
		String username = "bjstd"; // 用户名
		String password_md5 = "asdf12345"; // 密码
		String mobile = phonnum; // 手机号,只发一个号码：13800000001。发多个号码：13800000001,13800000002,...N// 。使用半角逗号分隔。
		String apikey = "84d49d09365e4dd5800c366c42ced943"; // apikey秘钥（请登录 http://m.5c.com.cn
							// 短信平台-->账号管理-->我的信息 中复制apikey）
//		String content = "【华泰益兴科技】您好，您的验证码是："+xcode+",验证码的有效时间为10分钟"; // 要发送的短信内容，特别注意：签名必须设置，网页验证码应用需要加添加【图形识别码】。
		try {

			String contentUrlEncode = URLEncoder.encode(content, encode); // 对短信内容做Urlencode编码操作。注意：如

			// 把发送链接存入buffer中，如连接超时，可能是您服务器不支持域名解析，请将下面连接中的：【m.5c.com.cn】修改为IP：【115.28.23.78】
			buffer.append("http://m.5c.com.cn/api/send/index.php?username="
					+ username + "&password=" + password_md5 + "&mobile="
					+ mobile + "&apikey=" + apikey + "&content="
					+ contentUrlEncode + "&encode=" + encode);
			// System.out.println(buffer); //调试功能，输入完整的请求URL地址
			// 把buffer链接存入新建的URL中
			URL url = new URL(buffer.toString());
			// 打开URL链接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 使用POST方式发送
			connection.setRequestMethod("POST");
			// 使用长链接方式
			connection.setRequestProperty("Connection", "Keep-Alive");
			// 发送短信内容
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			// 获取返回值
			String result = reader.readLine();
			// 输出result内容，查看返回值，成功为success，错误为error，详见该文档起始注释
			//System.out.println(result);
			log.info(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return "error:错误了;";
	}
	/***
	 * 校验验证码是否过期线程，10秒执行一次
	 */
	@Override
	public void run() {
		while(true){
			if (_keycode.size() > 0) {
				Iterator<Entry<String, Long>> iter = _keycodeValidt
						.entrySet().iterator();
				while (iter.hasNext()) {
					Calendar c = Calendar.getInstance();
					long eddx = c.getTimeInMillis();
					Entry<String, Long> entry = iter.next();
					String xcode = (String) entry.getKey();
					long edd = entry.getValue();
					long sub = eddx - edd;
					if (sub >= 0) {
						String phone = xcode.split("_")[0];
						
						_keycode.remove(phone);
						//_keycodeValidt.remove(xcode);
						log.info("移除验证码以后个数：" + _keycode.size());
						iter.remove();
					}
				}
			}
			try {
				Thread.sleep(5000);
				//log.info("验证码个数：" + _keycode.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static long getNow(){
		return Calendar.getInstance().getTimeInMillis();
	}

}
