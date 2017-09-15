<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    </head>
    <body>
	     
    </body>
</html>
