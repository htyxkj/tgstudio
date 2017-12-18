<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html style="height: 100%">
  <head>
    <base href="<%=basePath%>">
    <title>糖果录音棚客户取歌系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <link href="./img/ico.ico" rel="shortcut icon" />
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
				url: "checkXcode",
				data: data,
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				async: false,
				success: function (data) {
					if(data.errcode==0){
						$("#form").submit();
					}else{
						alert(data.errmsg);
					}
				}
			 });
		 }
		 function getcode(tel){
		  	data={'tels':tel};
			$.ajax({
				type: 'post',
				url: "sendXcode",
				data:data,
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				async: true,
				success: function (data) { 
				}
	        });
		}
		//判断手机号是否存在于系统内
		function selectTel(aa){
			var tel=$("#tel").val();
			data={'tel':tel};
			$.ajax({
				type: 'post',
				url: "checkTel",
				data:data,
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				async: false,
				success: function (data) { 
				  	if(data.errcode==0){
				  		time(aa);
				  		getcode(tel);
				  	}else{
				  		alert("抱歉，没有找到您的消息，请确认手机号无误!");
				  	}
				}
	        });
		 }
		var wait=60;
		function time(o){
			 if (wait == 0) {  
		            o.removeAttribute("disabled");            
		            o.value="获取";  
		            wait = 60;  
		        } else {  
		            o.setAttribute("disabled", true);  
		            o.value="（" + wait + "s）";  
		            wait--;  
		            setTimeout(function() {  
		                time(o);  
		            },  
		            1000);  
		        }  
		}
    </script>
    <style type="text/css">
    	body{ background: url(./img/quge.png) right no-repeat;
        	  background-size: auto auto;
    		  background-size: 100% 100%;
    		  margin: 0 0 0 0;
    		  padding: 0 0 0 0;
			}
		.body{height: 100%;}
		.div1{height: 100%;}
    </style>
    </head>
    <body class="body">
    	<div>
		   	<div style="width:70%;position:absolute;left:15%;top:43%">
				<form id="form" action="selectAll?type=A" name="f" method="post">
					<div class="input_outer">
						<span class="u_user"></span>
						<input name="tel" id="tel" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入手机号">
					</div>
					<div>
						<div class="input_outer" style="width: 60%;float:left;">
							<span class="us_uer"></span>
							<input id="code" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;width: 60%;"value="" type="text" placeholder="请输入验证码">
						</div>
						<div style="float:right;text-align:center;width:30%;margin-top:0.1em;line-height:2.4em;font-size:5px">
							<input type="button" class="act-but submit" value="获取" style="width:100% ;margin:0px;color:white;border:0px;height:2.4em; " onclick="javascript:selectTel(this);"/>  
						</div>
					</div>
					<div class="mb2" style="clear:both;"><a class="act-but submit" href="javascript:submit();" style="color: #FFFFFF">登录</a></div>
				</form>
			</div>
		</div>
    </body>
</html>
