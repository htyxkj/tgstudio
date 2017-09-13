<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			alert(url);
			$.ajax({
				type: 'POST',
				url: url,
				data: null,
				dataType: 'json',
				success: function (data) {
					if(data.errcode==0){
						WeixinJSBridge.call('closeWindow');
					}
					if(data.errcode==65415){
						alert("当前指定客服未在线,请您稍后再来!");
					}
				},
				error:function (data) {
					
				}
	        });
		}
    </script>
    <style type="text/css">
    	li{list-style-type: none;}
    	body{background-color:#EFEFEF;margin:0px;padding:0px; }
    	.ul1{width: 100%;margin:0px;padding:0px;background-color:white;}
    	.ul1 li{width: 100%;text-align: center;margin:0px;padding:0px;font-size:30px;padding-top:8px;padding-bottom:8px;}
    </style>
  </head>
  <body>
  <ul class="ul1">
  	<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png"> </li>
  </ul>
  <c:forEach var="list" items="${listI}" varStatus="status">
  	 <div onclick="javascript:kf('${list.service}');">
		 <div style="background-color:white;padding:10px 10px 10px 10px;margin:5px 8px 0px 8px;">
		 		<h3>${list.orgname}</h3>
		 		<p>${list.address}</p>
		 </div>
  	 </div>
  </c:forEach>
  </body>
</html>
