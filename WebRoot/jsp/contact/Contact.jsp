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
    
    <title>糖果录音棚店铺列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
		 
    </script>
    <style type="text/css">
    	body{background-color:#EFEFEF;margin:0px;padding:0px; }
    	.ul1{width: 100%;margin:0px;padding:0px;background-color:white;}
    	.ul1 li{width: 100%;text-align: center;margin:0px;padding:0px;font-size:30px;padding-top:8px;padding-bottom:8px;list-style-type:none; }
    	a{text-decoration:none;}
    	.dz{color: #D0D5D9;}
    </style>
  </head>

  <body>
  <ul class="ul1">
  	<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png"> </li>
  </ul>
  <c:forEach var="list" items="${listI}" varStatus="status">
		 <div style="background-color:white;padding:10px 10px 10px 10px;margin:5px 8px 0px 8px;">
		 		<h3>${list.orgname}——<a href="tel://${list.tel}">${list.tel}</a></h3>
		 		<p class="dz">地址:${list.address}</p>
		 </div>
  </c:forEach>
  </body>
</html>
