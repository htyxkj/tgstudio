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
<title>糖果录音-我的封面</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
<link href="./img/ico.ico" rel="shortcut icon" />
<link rel="stylesheet" href="./css/weui.min.css">
<link rel="stylesheet" href="./css/jquery-weui.css">
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="./js/jquery-weui.js"></script> 
<script type="text/javascript">
	$(function(){
		
	});
	var currentPage=${currentPage};//当前页数
	var totalPage=${totalPage};//总页数
	function playPause(obj){
		var bfzt=$(obj).next().next();//播放状态  1播放  2暂停
		var root=$(obj).next().val();//歌曲路径
		var audio= document.getElementById("audio");
		audio.pause();
		audio.src = root;
		audio.play();
		if(bfzt.val()=='1'){
			bfzt.val("2");
			$(obj).attr("src","./img/music.png");
			audio.pause();
		}else if(bfzt.val()=='2'){
			bfzt.val("1");
			$('img[id="ztimg"]').attr("src","./img/music.png");
			$(obj).attr("src","./img/list_zt.png");
		}
	}
	function xiazai(root){
		window.location.href=root; 
	}
	//加载数据
	function getpage(currentPage){
		var data={'currentPage':currentPage,'type':'v'};
		$.ajax({
			type: 'POST',
			url: "nextpage",
			data: data,
			dataType: 'json',
			success: function (data) {
				for (var i = 0; i < data.rows.length; i++) {
					var row=data.rows[i];
					var html = '';
					html = html + ['<div class="div1" >'+
					               '<s:hidden name="sid" value="'+row.sid+'"></s:hidden>'+
					               '<div class="img">'+
					               '<img src="./img/list_zhuanji.png" style="height: 40px;">'+
					               '</div>'+
					               '<div class="div2">'+
								   '<div onclick="javascript:xiangqing('+"'"+row.sid+"'"+')">'+
								   '<div style="font-size:16px;">'+
								   row.singname+
								   '</div>'+
								   '<div style="color:#72726F;font-size:14px;">'+
								   row.name+
								   '</div>'+
								   '</div>'+
								   '</div>'+
								   '<div style="float:right;text-align:center;line-height:40px;">'+
								   '<img alt="下载" src="./img/list_xz.png" style="width:36px;margin-top:15px;margin-right:8px"'+
								   'onclick="javascript:xiazai('+"'${dowUrl}"+row.fj_root+row.fj_name+"'"+')"/>'+
								   '</div></div>'].join("");
							$("#liebiao").append($(html));
				};
			},
			error:function (data) {
				alert("出错了");
			}
        });
	}
	function xiangqing(sid){
		window.location.href ='selectOne?sid='+sid;
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
	width:calc(100% - 170px);
	float: left;
	margin: 10px 0px 0px 10px;
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
		<p style="text-align: center;">您暂无可下载的封面!</p>
	</s:if>
	<s:else>
		<s:iterator value="listO" var="list">
			<div class="div1" >
				<div onclick="javascript:xiangqing('${list.sid}')">
					<s:hidden name="sid" value="%{#list.sid}"></s:hidden>
					<div class="img">
						<img src="./img/list_zhuanji.png" style="height:40px;"/>
					</div>
					<div class="div2">
						<div>
							<div style="font-size:16px;">
								<s:property value="%{#list.singname}" />
							</div>
							<div style="color:#72726F;font-size:14px;" >
								<s:property value="%{#list.name}" />
							</div>
						</div>
					</div>
				</div>
				<div style="float:right;text-align:center;line-height:40px;">
					<img alt="下载" src="./img/list_xz.png" style="width:36px;margin-top:15px;margin-right:8px" onclick="javascript:xiazai('${dowUrl}${list.fj_root}${list.fj_name}')"/>
				</div>
			</div>
		</s:iterator>
		<div id="liebiao">
		</div>
		</s:else> 
		<!-- 滚动加载 -->
		<div id="jiazhai" class="weui-infinite-scroll" style="display:none;">
			<div class="infinite-preloader"></div>
				正在加载
		</div>
		<!-- 数据加载完成  -->
		<div id="wan" class="weui-infinite-scroll" style="display:none;font-size:12px;color:#787878;">
				----------我是有底线的----------
		</div>
		<audio  id="audio" src=""></audio>
<script type="text/javascript">
var loading = false;
$(document.body).infinite().on("infinite", function() {
	$("#jiazhai").show();
	if(loading){ 
		return;
	}
	if(currentPage==totalPage){
		$("#jiazhai").hide();
		$("#wan").show();
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