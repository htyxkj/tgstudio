package org.business.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.servlet.CoreServlet;
import org.core.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(CoreServlet.class); 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUtil weixUtil=new WeixinUtil();
		TokenThread t=new TokenThread();
		AccessToken accTok=t.accessToken;
		HttpSession session = request.getSession();
		//�����
		OutputStream outputStream = response.getOutputStream();
		//��΢�ŷ�����������������  Ĭ��ΪPOST����  GET��Ҫ�����޸�
		String requestMethod = "POST";
		//�ͻ���������ַ
		String serviceURL=accTok.getErviceURL();
		//�����APPID
		String appid=accTok.getAppid();
		//�ͻ�����ſ���������appsecret
//		String appsecret=accTok.getAppsecret();
		//�û�ͬ����Ȩ���ܻ�ȡ��code
//		String code = request.getParameter("code");
		//�ͷ���ʶ
		String kf_account=request.getParameter("kf_account");
		//�û�Ψһ��ʶ openid
		String openid=(String) session.getAttribute("openid");
		
//		if (!"authdeny".equals(code) && code != null) {
//			//��ȡcode��,�����������ӻ�ȡaccess_token,�Լ��û� openid
//			String acc_url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//			acc_url= acc_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
//			//{ "access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":"REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE" } 
//			JSONObject jsonObj=weixUtil.httpsRequest(acc_url,requestMethod, null);
//			log.info(jsonObj.toString());
//			log.info(acc_url);
//			openid =jsonObj.getString("openid");
//		}
		String access_token=accTok.getToken()==null?"":accTok.getToken();
		log.info(kf_account+appid);
		//��ȡ�ͷ�δ�Ӵ��û��б�
//		String url="https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token="+access_token;
//		String kf="{\"kf_account\":\""+kf_account+appid+"\"}";
//		JSONObject jsonObj= weixUtil.httpsRequest(url, "GET", kf);
//		log.info(jsonObj.toString());
		//���Ϳͷ������ַ
		String requestUrl = "https://api.weixin.qq.com/customservice/kfsession/create?access_token="+access_token;
		JSONObject json=new JSONObject();
		json.put("kf_account", kf_account+appid);
		json.put("openid", openid);
		JSONObject jsonObj= weixUtil.httpsRequest(requestUrl, requestMethod, json.toString());
		log.info(jsonObj.toString());
		if(jsonObj.getInt("errcode")==0){
			//�����ʺ���
			String whUrl="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;
			String str="{\"touser\":\""+openid+"\",\"msgtype\":\"text\",\"text\":{\"content\":\"���ã��ܸ���Ϊ������\"},\"customservice\":{\"kf_account\": \""+kf_account+appid+"\"}}";
			log.info(str);
			JSONObject _jsonObj= weixUtil.httpsRequest(whUrl, requestMethod, str);
			log.info(_jsonObj.toString());
			// ע������ʽ����ֹ��������  
			outputStream.write(jsonObj.toString().getBytes("UTF-8"));
			outputStream.close();
			// ע������ʽ����ֹ��������
//			request.getRequestDispatcher("").forward(request, response);
//			response.sendRedirect("jsp/service/serviceClose.jsp");
			
		}else{
			request.getRequestDispatcher("jsp/service/serviceError.jsp").forward(request, response);
		}
	}
}