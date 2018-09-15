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
<title>糖果录音-往期歌曲</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
<link href="./img/ico.ico" rel="shortcut icon" />
<link rel="stylesheet" href="./css/weui.min.css">
<link rel="stylesheet" href="./css/jquery-weui.css">
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="./js/jquery-weui.js"></script> 
<script type="text/javascript">
	$(function(){
		AndroidOriphone();
	});
	var currentPage=1;//当前页数
	var totalPage=1;//总页数
	var fileUrl="";//当前页数
	var dowUrl="";//总页数
	function playPause(obj){
		var bfzt=$(obj).next().next();//播放状态  1播放  2暂停
		var root=$(obj).next().val();//歌曲路径
		var audio= document.getElementById("audio");
		audio.pause();
		audio.src = root;
		if(bfzt.val()=='1'){
			bfzt.val("2");
			$(obj).attr("src","./img/music.png");
			audio.pause();
		}else if(bfzt.val()=='2'){
			audio.play();
			$("input[name='bfzt']").val("2");
			bfzt.val("1");
			$('img[id="ztimg"]').attr("src","./img/music.png");
			$(obj).attr("src","./img/list_zt.png");
		}
	}
	//查询按钮点击事件
	function butClick(currentPage){
		$("#liebiao").empty();
		currentPage=1;//当前页数
		totalPage=1;//总页数
		getPast(currentPage);
	}
	function getPast(currentPage){
		var condition=$("#condition").val();
		var data={'currentPage':currentPage,'condition':condition};
		$.ajax({
			type: 'POST',
			url: "onePast",
			data: data,
			dataType: 'json',
			success: function (data) {
				currentPage=data.currentPage;//当前页数
				totalPage=data.totalPage;//总页数
				console.log(currentPage);
				console.log(totalPage);
				fileUrl=data.fileUrl;
				dowUrl=data.dowUrl; 
				if(data.rows.length==0){
					$("#noxz").show();
				}
				for (var i = 0; i < data.rows.length; i++) {
					$("#noxz").hide();
					var row=data.rows[i];
					var html = '';
					html = html + ['<div class="div1" >'+
					               '<div onclick="javascript:xiangqing('+"'"+row.sid+"'"+')">'+
					               '<s:hidden name="sid" value="'+row.sid+'"></s:hidden>'+
					               '<div class="img">'+
					               '<img src="./img/list_zhuanji.png" style="height: 40px;">'+
					               '</div>'+
					               '<div class="div2">'+
								   '<div onclick="javascript:xiangqing('+"'"+row.sid+"'"+')">'+
								   '<div style="height:1.5em;overflow:hidden;">'+
								   row.singname+
								   '</div>'+
								   '<div style="color:#72726F;font-size:14px;">'+
								   row.orname+
								   '</div>'+
								   '</div>'+
								   '</div>'+
								   '</div>'+
								   '<div style="float:right;text-align:center;line-height:40px;">'+
								   '<img id="ztimg" src="./img/music.png"  style="width:36px;margin-top:15px;margin-right:8px"  onclick="javascript:playPause(this)" >'+
								   '<s:hidden name="fj_root" value="'+fileUrl+row.fj_root+row.fj_name+'"></s:hidden>'+
								   '<s:hidden name="bfzt"   value="2"></s:hidden>'+
								   '<img alt="下载" src="./img/list_xz.png" style="width:36px;margin-top:15px;margin-right:8px"'+
								   'onclick="javascript:xiazai('+"'"+dowUrl+row.fj_root+row.fj_name+"'"+')"/>'+
								   '</div></div>'].join("");
							$("#liebiao").append($(html));
				};
				AndroidOriphone();
			},
			error:function (data) {
				alert("出错了");
			}
        });
	}
	function xiangqing(sid){
		window.location.href ='selOnePast?sid='+sid;
	}
	function AndroidOriphone(){
		var u = navigator.userAgent;
		if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
			$("img[alt='下载']").hide();
		} else if (u.indexOf('iPhone') > -1||u.indexOf('iPad') > -1) {//苹果手机
			$("img[alt='下载']").hide();
		}
	}
	function xiazai(root){
		window.location.href=root; 
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
.ul2 {
	width: 100%;
	margin: 0px;
	padding: 0px;
	padding-top:5px;
}
.ul2 li {
	width: 100%;
	text-align: center;
	font-size: 30px;
	padding-top: 8px;
	height:30px;
	list-style-type: none;
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
	width:calc(100% - 120px);
	float: left;
	margin: 10px 0px 0px 10px;
}
.img {
	height: 40px;
	width: 40px;
	float: left;
	padding: 10px 10px 10px 10px;
}
.input_search_key{ float:left; width:65%; height:25px; padding:3px; margin-right:5px; border:1px solid #cccccc; line-height:18px; background:#FFFFFF;}
.search_btn{ float:left; width:68px; height:33px; overflow:hidden; border:1px solid #cccccc; text-align:center; color:#AACD06; letter-spacing:5px; cursor:pointer; font-weight:bold;}
.search_t{ float:left; width:5%; line-height:26px; color:#AACD06;}      
</style>
</head>
<body>
	<ul class="ul1">
		<li><img alt="糖果录音" style="height:30px;" src="./img/logo.png"></li>
	</ul>
	<ul class="ul2">
		<li>
			<span class="search_t">&nbsp;&nbsp;&nbsp;&nbsp;</span>  
			<input type="text" class="input_search_key" id="condition" name="condition" placeholder="请输入关键词进行搜索" />  
            <input type="button" class="search_btn" onclick="butClick(1)" value="搜索"/> 
        </li>
	</ul>
	<p id="noxz" style="text-align: center;display:none;">暂无符合条件的歌曲!</p>
	<div id="liebiao">
	</div> 
	<!-- 滚动加载 -->
	<div id="jiazhai" class="weui-infinite-scroll" style="display:none;">
		<div class="infinite-preloader"></div>
			正在加载
	</div>
	<!-- 数据加载完成  -->
	<div id="wan" class="weui-infinite-scroll" style="display:none;font-size:12px;color:#787878;">
			----------我是有底线的----------
	</div>
	<audio  id="audio"></audio>
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
	getPast(currentPage);
	loading=false;
	$("#jiazhai").hide();
});
</script>
</body>
</html>