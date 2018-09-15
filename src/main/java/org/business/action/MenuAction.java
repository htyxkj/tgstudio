package org.business.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.business.biz.IWxMenuBiz;
import org.business.biz.impl.WxMenuBizImpl;
import org.business.dao.IWxMenuDao;
import org.business.entity.WxMenu;
import org.business.entity.WxMenuButton;
import org.business.entity.WxMenuResult;
import org.core.accesstoken.AccessToken;
import org.core.accesstoken.TokenThread;
import org.core.util.WeixinUtil;
import org.core.util.WxConsts;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport{
	//1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
	//2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
	//菜单操作
	protected static Logger log = Logger.getLogger(MenuAction.class);
	private List<WxMenuButton> listM;
	private WxMenuResult wxMenu;
	private String yes;
	
	private String z_0;
	private String z_1;
	private String z_2;
	
	private List<String> z_0_zcd;
	private List<String> z_1_zcd;
	private List<String> z_2_zcd;
	
	
	public String getYes() {
		return yes;
	}
	public void setYes(String yes) {
		this.yes = yes;
	}
	public void setZ_0(String z_0) {
		this.z_0 = z_0;
	}
	public void setZ_1(String z_1) {
		this.z_1 = z_1;
	}
	public void setZ_2(String z_2) {
		this.z_2 = z_2;
	}
	public void setZ_0_zcd(List<String> z_0_zcd) {
		this.z_0_zcd = z_0_zcd;
	}
	public void setZ_1_zcd(List<String> z_1_zcd) {
		this.z_1_zcd = z_1_zcd;
	}
	public void setZ_2_zcd(List<String> z_2_zcd) {
		this.z_2_zcd = z_2_zcd;
	}
	public WxMenuResult getWxMenu() {
		return wxMenu;
	}
	public List<WxMenuButton> getListM() {
		return listM;
	}
	//菜单操作页面
	public String menuJsp() {
		try {
			IWxMenuBiz wxMb = new WxMenuBizImpl();
			this.wxMenu=getMenu();
			this.listM=wxMb.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "menuJsp";
    }
	//获取菜单信息
	public WxMenuResult getMenu(){
		WeixinUtil weixinUtil=new WeixinUtil();
		AccessToken accTok = TokenThread.accessToken;
		String url = WxConsts.URL_GET_MENU.replace("ACCESS_TOKEN", accTok.getToken());
		JSONObject json = weixinUtil.httpsRequest(url, "GET", null);
		WxMenuResult wxMenu = JSON.parseObject(json.toJSONString(), WxMenuResult.class);
//		String json="{\"menu\":{\"button\":[{\"name\":\"公司介绍\",\"sub_button\":[{\"type\":\"view\",\"name\":\"官方网站\",\"url\":\"http://www.tangostudio.cn/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"商务合作\",\"key\":\"marketService\",\"sub_button\":[]}]},{\"name\":\"客服\",\"sub_button\":[{\"type\":\"click\",\"name\":\"联系客服\",\"key\":\"insorg\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"投诉建议\",\"key\":\"marketService\",\"sub_button\":[]}]}]}}";
//	 	WxMenuResult wxMenu = JSON.parseObject(json, WxMenuResult.class);
	 	if(wxMenu.getMenu().getButton().size()<3){
	 		int num = 3-wxMenu.getMenu().getButton().size();
	 		for(int j=0;j<num;j++){
	 			WxMenuButton wmenu=new WxMenuButton();
	 			wxMenu.getMenu().getButton().add(wmenu);
 			}
			
	 	}
	 	for(int i=0;i<wxMenu.getMenu().getButton().size();i++){
	 		int sbu_num=0;
	 		if(wxMenu.getMenu().getButton().get(i)==null)
	 			sbu_num=0;
	 		else
	 			sbu_num=wxMenu.getMenu().getButton().get(i).getSub_button().size();
	 		if(sbu_num<5){
	 			int num=5-sbu_num;
	 			for(int j=0;j<num;j++){
	 				wxMenu.getMenu().getButton().get(i).getSub_button().add(null);
	 			}
	 		}
	 	}
	 	
	 	
	 	wxMenu.getMenu().getButton().get(0).getSub_button().size();
		return wxMenu;
	}
	//菜单操作页面
	public String saveMenu() {
		try {
			IWxMenuBiz wxMb = new WxMenuBizImpl();
			this.listM=wxMb.getAll();
			WxMenuButton wxMenu= null ;
			Map<String,WxMenuButton> mapMenu=new HashMap<String,WxMenuButton>();
			for(int i=0;i<listM.size();i++){
				wxMenu=listM.get(i);
				mapMenu.put(wxMenu.getName(), wxMenu);
			}
			WxMenuButton wxMenu0 = mapMenu.get(z_0);
			WxMenuButton wxMenu1 = mapMenu.get(z_1);
			WxMenuButton wxMenu2 = mapMenu.get(z_2);
			List<WxMenuButton> button0 = new ArrayList<WxMenuButton>();
			List<WxMenuButton> button1 = new ArrayList<WxMenuButton>();
			List<WxMenuButton> button2 = new ArrayList<WxMenuButton>();
			for (int i = 0; i < z_0_zcd.size(); i++) {
				if(mapMenu.get(z_0_zcd.get(i))!=null){
					WxMenuButton wxbtnz0=mapMenu.get(z_0_zcd.get(i));
					wxbtnz0.setSub_button(null);
					button0.add(wxbtnz0);
				}
			}
			for (int i = 0; i < z_1_zcd.size(); i++) {
				if(mapMenu.get(z_1_zcd.get(i))!=null){
					WxMenuButton wxbtnz1=mapMenu.get(z_1_zcd.get(i));
					wxbtnz1.setSub_button(null);
					button1.add(wxbtnz1);
				}
			}
			for (int i = 0; i < z_2_zcd.size(); i++) {
				if(mapMenu.get(z_2_zcd.get(i))!=null){
					WxMenuButton wxbtnz2=mapMenu.get(z_2_zcd.get(i));
					wxbtnz2.setSub_button(null);
					button2.add(wxbtnz2);
				}
			}
			List<WxMenuButton> button = new ArrayList<WxMenuButton>();
			if(wxMenu0!=null){
				wxMenu0.setSub_button(button0);
				button.add(wxMenu0);
			}
			if(wxMenu1!=null){
				wxMenu1.setSub_button(button1);
				button.add(wxMenu1);
			}
			if(wxMenu2!=null){
				wxMenu2.setSub_button(button2);
				button.add(wxMenu2);
			}
			
			WxMenu wxMenuBtn = new WxMenu();
			wxMenuBtn.setButton(button);
			log.info(JSON.toJSONString(wxMenuBtn));
			AccessToken accTok = TokenThread.accessToken;
			String url=WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", accTok.getToken());
			JSONObject jsonObj= WeixinUtil.httpsRequest(url, "POST", JSON.toJSONString(wxMenuBtn));
			if(jsonObj.getIntValue("errcode")==0){
				this.yes="yes";
			}else{
				log.info(jsonObj.toJSONString());
				this.yes="no";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.yes="no";
		}
		return "menuList";
    }
}
