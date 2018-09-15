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
    
    <title>菜单编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="./img/ico.ico" rel="shortcut icon" />
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<style type="text/css">
		td{width:120px;}
		.div1{text-align: center;}
	</style>
	<script type="text/javascript">
		$(function(){
			if('${yes}'=="yes")
				alert("保存成功!");
			if('${yes}'=="no")
				alert("保存失败!");
		});
	</script>
  </head>
  <body>
  <div class="div1">
  	
	<h2>微信订阅号菜单设置</h2>
	1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单（5个可以不全部选择）。
	<form action="saveMenu" method="post">
		<center> 
		<table>
			<tr>
		    <td>一级菜单</td>
		    <td>二级菜单</td>
		  </tr>
		  <s:iterator value="wxMenu.menu.button" var="btn" status="i">
	     		<tr>
				    <td><s:select name="z_%{#i.index}" list="listM"  listValue="name" listKey="name" headerKey="0" headerValue="--请选择--" value="%{#btn.name}"></s:select></td>
			     	<s:iterator value="#btn.sub_button" var="btn2" status="i2">
			     		<td><s:select  id="%{#i.index}-zcd%{#i2.index}"  name="z_%{#i.index}_zcd" list="listM"  listValue="name" listKey="name" headerKey="0" headerValue="--请选择--"  value="%{#btn2.name}"></s:select></td>
					</s:iterator>
				</tr>
		  </s:iterator>
		</table>
		</center>
		<s:submit value="保存"></s:submit>
		</form>
	</div>
  </body>
</html>