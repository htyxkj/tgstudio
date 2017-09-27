<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微信服务号菜单URL设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="./img/ico.ico" rel="shortcut icon" />
  </head>
  
  <body>
  		<div>
  			<div>
  				<div>联系客服</div>
  				<div><s:property value="kefu"/></div>
  			</div>
  			<div>
  				<div>一键取歌</div>
  				<div><s:property value="quge"/></div>
  			</div>
  			<div>
  				<div>歌曲试听</div>
  				<div><s:property value="shiting"/></div>
  			</div>
  			<div>
  				<div>一键拨号</div>
  				<div><s:property value="bohao"/></div>
  			</div>
  			<div>
  				<div>联系我们</div>
  				<div><s:property value="lianxiwm"/></div>
  			</div>
  		</div>
  </body>
</html>