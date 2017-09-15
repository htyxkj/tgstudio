<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>糖果录音棚客户曲歌系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/getSong.css" />
	<script type="text/javascript">
		 function tel(){
			 var tel=$("#tel").val();
			 tel(tel);
		 }
		 
		 function submit(){
			 var tel=$("#tel").val();
			 var code=$("#code").val();
			 if(tel==""){
				 alert("请输入手机号!");
				 return;
			 }
			 if(code==""){
				 alert("请输入验证码!");
				 return;
			 }
			 var data={'tels':tel,'xcode':code};
			 $.ajax({
				type: 'post',
				url: "ckxcode",
				data: data,
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				async: false,
				success: function (data) {
					if(data.errcode==0){
						alert("验证码输入正确");
					}else{
						alert(errcode);
					}
				}
			 });
		 }
		 function getcode(){
			var tel=$("#tel").val();
		 	if(selectTel(tel)){
			  	data={'tels':tel};
				$.ajax({
					type: 'post',
					url: "xcode",
					data:data,
					contentType: 'application/x-www-form-urlencoded',
					dataType: 'json',
					async: true,
					success: function (data) { 
					  	alert(data.errcode);
					}
		        })
		 	}
		}
		function selectTel(tel){
			 return true;
		 }
    </script>
    </head>
    <body style="margin:0px;padding:0px;">
    	<img src="./img/quge.png" style="z-index:-10; margin: 0px;padding:0px;width:100%;">
    	<div style="width:70%;position:absolute;left:15%;top:28%">
			<form id="form" action="#" name="f" method="post">
				<div class="input_outer">
					<span class="u_user"></span>
					<input id="tel" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入手机号" onchange="javascript:tel()">
				</div>
				<div>
					<div class="input_outer" style="width: 60%;float:left;">
						<span class="us_uer"></span>
						<input id="code" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;width: 60%;"value="" type="text" placeholder="请输入验证码">
					</div>
					<div style="float:left;width:30%;padding-left:6px;">
						<a class="act-but submit" style="margin:0px;color:white;" onclick="javascript:getcode();"> 获取</a>
					</div>
				</div>
				<div class="mb2" style="clear:both;"><a class="act-but submit" href="javascript:submit();" style="color: #FFFFFF">登录</a></div>
			</form>
		</div>
    </body>
</html>
