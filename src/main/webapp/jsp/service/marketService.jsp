<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>市场部</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
    <link href="./img/ico.ico" rel="shortcut icon" />
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
		$(function(){
			setTimeout(function(){
					WeixinJSBridge.call('closeWindow'); 
				},1500); //只有这个可以使用
		});
    </script>
  </head>
  <body>
  </body>
</html>
