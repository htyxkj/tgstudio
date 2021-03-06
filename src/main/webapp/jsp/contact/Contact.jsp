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
    
    <title>糖果录音棚店铺列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
    <link href="./img/ico.ico" rel="shortcut icon" />
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
		 
    </script>
    <style type="text/css">
    	body{background-color:#EFEFEF;margin:0px;padding:0px; }
    	.ul1{width: 100%;margin:0px;padding:0px;background-color:white;}
    	.ul1 li{width: 100%;text-align: center;margin:0px;padding:0px;font-size:30px;padding-top:8px;padding-bottom:8px;list-style-type:none; }
    	a{text-decoration:none;}
    	.div1 {height:120px;background-color:white;margin:20px 10px 0 10px;width:auto;}
    	.div2 {float: left;margin: 30px 0px 0px 10px;width:calc(100% - 130px);}
    	.img{height: 100px;width: 100px;float: left;margin: 10px 10px 10px 10px;}
    	.dz{color: #868686;word-break:break-word;padding:0px;margin:0px;}
    </style>
  </head>

  <body>
  <ul class="ul1">
  	<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png"> </li>
  </ul>
  <s:iterator value="listI" var="list">
  	<div class="div1">
  		<div class="img">
  			<img src="${fileUrl}${list.fj_root}${list.fj_name}">
  		</div>
	  	<div class="div2">
	  		<div style="font-size:16px;">
	  			<s:property value="%{#list.orgname}"/> 电话:  <a style="color: #000;" href="tel://${list.tel}"><s:property value="%{#list.tel}"/></a>
	  		</div>
	  		<div class="dz">地址:<s:property value="%{#list.address}"/></div>
		</div>
	</div>
  </s:iterator>
  </body>
</html>