package org;

import org.business.entity.WxMenuResult;

import com.alibaba.fastjson.JSON;



public class Test {
	 public static void main(String[] args) {
	 	String json="{\"menu\":{\"button\":[{\"name\":\"公司介绍\",\"sub_button\":[{\"type\":\"view\",\"name\":\"官方网站\",\"url\":\"http://www.tangostudio.cn/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"商务合作\",\"key\":\"marketService\",\"sub_button\":[]}]}]}}";
	 	WxMenuResult sjon = JSON.parseObject(json, WxMenuResult.class);
	 	System.out.println(sjon);
    }
}