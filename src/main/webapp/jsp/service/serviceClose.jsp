<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>客服列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="./js/jweixin-1.2.0.js"></script>
	<script type="text/javascript">
		$(function(){
			document.addEventListener('WeixinJSBridgeReady', function(){ 
				WeixinJSBridge.call('closeWindow'); 
			}, false);
		});
    </script>
  </head>
  <body>
  	
  </body>
</html>
