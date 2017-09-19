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
    <title>糖果录音-我的歌曲</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
	$.ajax({
		type: 'post',
		url: url+"api/",
		data: dataSelect,
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		async: false,
		success: function (data) {
			console.log(data);
			//子表标签名
			cels=data.data.layCels.subLayCells[j].cels;
			//总页数
			totalPage=data.data.pages.totalPage; 
		}
    });
    </script>
    <style type="text/css">
	    .ul1{width: 100%;margin:0px;padding:0px;background-color:white;}
    	.ul1 li{width: 100%;text-align: center;margin:0px;padding:0px;font-size:30px;padding-top:8px;padding-bottom:8px;list-style-type:none; }
    	.div1 {height:50px;background-color:white;margin:20px 10px 0 10px;width:auto;}
    	.div2 {float: left;margin: 16px 0px 0px 10px;width:calc(100% - 80px);}
    	.img{height: 50px;width: 50px;float: left;padding: 10px 10px 10px 10px;}
    </style>
    </head>
    <body>
    <ul class="ul1">
	  	<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png"> </li>
	</ul>
	     <s:iterator value="listO" var="list">
		  	<div class="div1">
		  		<div class="img">
		  			<img src="./img/11.png" style="height: 50px;">
		  		</div>
			  	<div class="div2">
			  		<div style="font-size:16px;">
			  			<s:property value="%{#list.singname}"/>
			  		</div>
			  		<div><s:property value="%{#list.name}"/></div>
				</div>
			</div>
		  </s:iterator>
    </body>
</html>
