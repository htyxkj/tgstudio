<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html style="width:100%;height:100%">
<head>
<base href="<%=basePath%>">
<title>糖果录音-<s:property value="orderfc.singname"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="./js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	 
    </script>
<style type="text/css">
 
</style>
</head>
<body>
	<div>
		<video  src="${fileUrl}${orderfc.fj_root}${orderfc.fj_name}" controls="controls" x5-playsinline="" style="width:94%;height:100%;">
			<source src="${fileUrl}${orderfc.fj_root}${orderfc.fj_name}" type="video/mp4" >
		</video>
	</div>
</body>
</html>