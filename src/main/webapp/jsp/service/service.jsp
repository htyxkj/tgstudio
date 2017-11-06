<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>糖果录音棚客服列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
		function kf(url){
			if(url!=null&&url!=""){
				$.ajax({
					type: 'POST',
					url: url,
					data: null,
					dataType: 'json',
					success: function (data) {
						if(data.errcode==0){
							WeixinJSBridge.call('closeWindow');
						}else if(data.errcode==65415){
							alert("当前指定客服未在线,请您稍后再来!");
						}else if(data.errcode==65401){
							alert("无效客服帐号");
						}else if(data.errcode==65414){
							alert("您当前正在被其他客服接待,请回复“转接+店名”,客服稍后会为您服务!");
						}
					},
					error:function (data) {
						alert("出错了");
					}
		        });
			}else{
				alert("当前指定客服未在线,请您稍后再来!");
			}
		}
		function call(tel){
			window.location.href = 'tel://'+tel;
		}
		function weizi(title,addr,epoint){ 
			var url="http://apis.map.qq.com/tools/poimarker?type=0&marker=coord:"+epoint+";title:"+title+";addr:"+addr+"&key=JA3BZ-WWLRK-4UQJG-ADDJB-5CDJH-X6B7G&referer=tgweixin";
			window.location.href = url;
		}
    </script>
    <style type="text/css">
    	*{margin:0; padding:0;}
    	body{background-color:#EFEFEF;margin:0px;padding:0px; }
    	.ul1{width: 100%;margin:0px;padding:0px;background-color:white;}
    	.ul1 li{width: 100%;text-align: center;margin:0px;padding:0px;font-size:30px;padding-top:8px;padding-bottom:8px;list-style-type:none; }
    	a{text-decoration:none;}
    	.div1 {margin:10px 0px 0 0px;width: 48%;float:left;margin-left:1%}
    	.div2 {margin: 0px 0px 0px 3%;width:calc(94% + 2px);color:white;background-color:#5e9900;line-height:30px;margin-top:-40px;border-radius:0px 0px 10px 10px;opacity: 0.8;}
    	.img{height: 16px;width: 16px;vertical-align:text-bottom;}
    	.dz{color: #868686;word-break:break-word;padding:0px;margin:0px;}
    </style>
  </head>

  <body>
  <ul class="ul1">
  	<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png"> </li>
  </ul>
  <s:iterator value="listI" var="list">
    
  <div class="div1">
  	<div style="margin-left:3%;width:94%;border-radius:5px;border:1px solid #5e9900;">
  		<img onclick="javascript:kf('${kfURL}${list.service}@')" alt="" src="${fileUrl}${list.fj_root}${list.fj_name}" style="margin-bottom:-4px;width:100%;height:100%;padding:0px;">
  	</div>
  	<div class="div2">
		<div style="margin-left:10px; font-size:16px;line-height:20px">
			<div style="width:80px;float:left;"><s:property value="%{#list.orgname}"/></div>
			&nbsp;&nbsp;
			<img class="img" alt="电话" src="./img/dianhua.png" onclick="javascript:call('${list.tel}')">
			&nbsp;&nbsp;
			<img class="img" alt="位置" src="./img/dingwei.png" onclick="javascript:weizi('${list.orgname}','${list.address}','${list.epointy},${list.epointx}')">
			<br/>
			<img onclick="javascript:kf('${kfURL}${list.service}@')" class="img" alt="位置" src="./img/xiaoxi.png" >
			<span>:<s:property value="%{#list.worktime}"/></span>
		</div>
  	</div>
  </div>
  </s:iterator>
  <br/>
  </body>
</html>