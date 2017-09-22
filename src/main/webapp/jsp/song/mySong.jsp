<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>糖果录音-我的歌曲</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
<link rel="stylesheet" href="./css/weui.min.css">
<link rel="stylesheet" href="./css/jquery-weui.css">
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="./js/jquery-weui.js"></script> 
<script type="text/javascript">
	$(function(){
		$("#jiazhai").hide();
	});
	var i=0;
	var audio;
	var currentPage=${currentPage};//当前页数
	var totalPage=${totalPage};//总页数
	var loading = false;
	function playPause(obj){
		var bfzt=$(obj).next().next();//播放状态  1播放  2暂停
		var root=$(obj).next().val();//歌曲路径
		if(i==0){
			audio = document.createElement("audio");
		}else{
			audio.pause();
			audio = document.createElement("audio");
		}
		if (audio != null && audio.canPlayType && audio.canPlayType("audio/mpeg")){
			audio.src = root;
			if(bfzt.val()=='1'){
				bfzt.val("2");
				$(obj).attr("src","./img/bf.png");
				audio.pause();
			}else if(bfzt.val()=='2'){
				bfzt.val("1");
				$('img[name="ztimg"]').attr("src","./img/bf.png");
				$(obj).attr("src","./img/zt.png");
				audio.play();
			}
		}
		i++;
	}
	function xiazai(root){
		window.location=root; 
	}
	//加载数据
	function getpage(currentPage){
		var data={'currentPage':currentPage};
		$.ajax({
			type: 'POST',
			url: "nextpage",
			data: data,
			dataType: 'json',
			success: function (data) {
				for (var i = 0; i < data.rows.length; i++) {
					var row=data.rows[i];
					var html = '';
					//初始化
	                html = html + ['<div class="div1" >'+
					'<s:hidden name="sid" value="'+row.sid+'"></s:hidden>'+
					'<div class="img">'+
					'<img src="${fileUrl}'+row.fj_troot+row.fj_tname+'" style="height: 40px;">'+
					'<img name="ztimg" src="./img/bf.png" style="height: 40px;position:relative;top:-47px;" onclick="javascript:playPause(this)" >'+
					'<s:hidden name="fj_root" value="'+row.fj_bfroot+'"></s:hidden>'+
					'<s:hidden name="bfzt" value="2"></s:hidden>'+
					'</div>'+
					'<div class="div2">'+
					'<a href="selectOne?sid='+row.sid+'" style="text-decoration:none;color:black;">'+
					'<div style="font-size:16px;">'+
					row.singname+
					'</div>'+
					'<div>'+
					row.name+
					'</div>'+
					'</a>'+
					'</div>'+
					'<div style="width:40px;float:left;text-align:center;line-height:40px;">'+
					'<img alt="下载" src="./img/xiazai.png" style="width:40px;margin-top:10px;" onclick="javascript:xiazai("'+row.fj_root+'")"/>'+
					'</div>'+
					'</div>'].join("");
					$("#liebiao").append($(html));
				};
			},
			error:function (data) {
				alert("出错了");
			}
        });
		
	}
    </script>
<style type="text/css">
body {
	background-color: #EFEFEF;
	margin: 0px;
	padding: 0px;
}
.ul1 {
	width: 100%;
	margin: 0px;
	padding: 0px;
	background-color: white;
}
.ul1 li {
	width: 100%;
	text-align: center;
	margin: 0px;
	padding: 0px;
	font-size: 30px;
	padding-top: 8px;
	padding-bottom: 0px;
	list-style-type: none;
}
.div1 {
	height: 60px;
	background-color: white;
	margin: 10px 10px 0 10px;
	width: auto;
}
.div2 {
	float: left;
	margin: 10px 0px 0px 10px;
	width: calc(100% - 120px);
}
.img {
	height: 40px;
	width: 40px;
	float: left;
	padding: 10px 10px 10px 10px;
}
</style>
</head>
<body>
	<ul class="ul1">
		<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png">
		</li>
	</ul>
	<s:if test="listO.size()==0">
		<p style="text-align: center;">您暂无可下载的歌曲!</p>
	</s:if>
	<s:else>
		<s:iterator value="listO" var="list">
			<div class="div1" >
				<s:hidden name="sid" value="%{#list.sid}"></s:hidden>
				<div class="img">
					<img src="${fileUrl}${list.fj_troot}${list.fj_tname}" style="height:40px;"/>
					<s:if test='%{#list.type=="m"}'>
						<img name="ztimg" src="./img/bf.png" style="height: 30px;position:relative;top:-42px;left:5px;" onclick="javascript:playPause(this)" >
						<s:hidden name="fj_root" value="%{fileUrl}%{#list.fj_root}%{#list.fj_name}"></s:hidden>
						<s:hidden name="bfzt" value="2"></s:hidden>
					</s:if>
				</div>
				<div class="div2">
					<a href="selectOne?sid=${list.sid}" style="text-decoration:none;color:black;">
						<div style="font-size:16px;">
							<s:property value="%{#list.singname}" />
						</div>
						<div style="color:#72726F;font-size:14px;" >
							<s:property value="%{#list.name}" />
						</div>
					</a>
				</div>
				<div style="width:40px;float:left;text-align:center;line-height:40px;">
					<img alt="下载" src="./img/xiazai.png" style="width:36px;margin-top:15px;" onclick="javascript:xiazai('${dowUrl}${list.fj_root}${list.fj_name}')"/>
				</div>
			</div>
		</s:iterator>
		<div id="liebiao">
		</div>
		</s:else> 
		<!-- 滚动加载 -->
		<div id="jiazhai" class="weui-infinite-scroll">
			<div class="infinite-preloader"></div>
				正在加载
		</div>
<script type="text/javascript">
$(document.body).infinite().on("infinite", function() {
	$("#jiazhai").show();
	if(loading){ 
		return;
	}
	if(currentPage==totalPage){
		$("#jiazhai").hide();
		return;
	}
	currentPage=currentPage+1;
	loading=true;
	getpage(currentPage);
	loading=false;
	$("#jiazhai").hide();
});
</script>
</body>
</html>