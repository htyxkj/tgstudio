<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html  style="height: 100%">
  <head>
    <base href="<%=basePath%>">
    <title>出错了</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
    <style type="text/css">
    	body{ background: url(./img/error.png) right no-repeat;
        	  background-size: auto auto;
    		  background-size: 100% 100%;
    		  margin: 0 0 0 0;
    		  padding: 0 0 0 0;
			}
    </style>
  </head>
  <body>
  
  </body>
</html>
