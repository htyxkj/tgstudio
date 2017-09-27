<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="./img/ico.ico" rel="shortcut icon" />
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
  </head>
  
  <body>
    <script type="text/javascript">
     	$(function(){
     		var phone=$("#phone").val();
     		window.location.href = 'tel://'+phone;
     	}); 
    </script>
    <s:hidden id="phone" value="%{phone}"></s:hidden>
  </body>
</html>
