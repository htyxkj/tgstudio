package org.core.action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.SignUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 核心请求处理类
 * 
 * @author lizhengguo
 * @date 2017-09-08
 */
public class CoreAction extends ActionSupport {
	private static Logger log = Logger.getLogger(CoreAction.class);
	private static final long serialVersionUID = 4440739483644821986L;
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String signature;// 微信加密签名
	private String timestamp;// 时间戳
	private String nonce;// 随机数
	private String echostr;// 随机字符串
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	/**
	 * 确认请求来自微信服务器
	 */
	public void coreRequest() {
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String method = ServletActionContext.getRequest().getMethod();
			if (method.equals("POST")) {// 注意全部大写
				
				TokenThread t=new TokenThread();
				AccessToken acc=t.accessToken;
				String service=acc.getServiceURL();
				// 将解析结果存储在HashMap中
				Map<String, String> map = new HashMap<String, String>();
				// 从request中取得输入流
				InputStream inputStream = request.getInputStream();
				// 读取输入流
				SAXReader reader = new SAXReader();
				Document document = reader.read(inputStream);
				// 得到xml根元素
				Element root = document.getRootElement();
				// 得到根元素的所有子节点
				List<Element> elementList = root.elements();
				// 遍历所有子节点
				for (Element e : elementList)
					map.put(e.getName(), e.getText());
				// 释放资源
				inputStream.close();
				inputStream = null;
				PrintWriter out = response.getWriter();
				// 开发者微信号
				String fromUserName =  map.get("ToUserName");
				// 接收方帐号
				String toUserName =map.get("FromUserName");
				String xml="";
				String imgUrl="http://123.206.90.95/tgstudio/img/1501035711.png";
				if(map.get("EventKey").equals("insorg")){
					String insorg=service+"/insorg?openid="+toUserName;
					xml=this.toXml(toUserName, fromUserName, insorg,imgUrl);
					out.print(xml);
				}else if(map.get("EventKey").equals("marketService")){
					String marketService=service+"/marketService?openid=?"+toUserName;
					xml=this.toXml(toUserName, fromUserName, marketService,imgUrl);
					out.print(xml);
				}
				out.close();
				out = null;
			} else {
				PrintWriter out = response.getWriter();
				// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {
					out.print(echostr);
				}
				out.close();
				out = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toXml(String toUser,String fromUser,String url,String imgUlr) {
		String xml = "<xml>" 
				+ "<ToUserName><![CDATA["+toUser+"]]></ToUserName>"//接收方帐号（收到的OpenID）
				+ "<FromUserName><![CDATA["+fromUser+"]]></FromUserName>"//开发者微信号
				+ "<CreateTime>"+new Date().getTime()+"</CreateTime>"//消息创建时间 （整型）
				+ "<MsgType><![CDATA[news]]></MsgType>"//news
				+ "<ArticleCount>1</ArticleCount>" //图文消息个数，限制为8条以内
					+ "<Articles>" 
						+ "<item>"
							+ "<Title><![CDATA[咨询在线客服]]></Title>"//图文消息标题
							+ "<Description><![CDATA[咨询在线客服]]></Description>"//图文消息描述
							+ "<PicUrl><![CDATA["+imgUlr+"]]></PicUrl>"//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
							+ "<Url><![CDATA["+url+"]]></Url>" //点击图文消息跳转链接
						+ "</item>" 
				+ "</Articles>"
				+ "</xml>";
		log.info(xml);
		return xml;
	}
}
