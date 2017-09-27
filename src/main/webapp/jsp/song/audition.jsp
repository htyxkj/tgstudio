<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html style="width:100%;height:100%">
  <head>
    <base href="<%=basePath%>">
    <title>糖果录音棚歌曲试听系统</title>
    <link href="./img/ico.ico" rel="shortcut icon" />
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="./js/audition.jsp.js"></script>
	<link rel="stylesheet" href="./css/audition.jsp.css">
	<script type="text/javascript">
	var i=1;
	var jsonSong;
	$(function(){
		var myAudio=document.getElementById('audio');//播放控件
		var singname=document.getElementById('singname');//歌曲名称
		var name=document.getElementById('name');//演唱者
		allSong();
		singname.innerHTML=jsonSong[1].singname;
		name.innerHTML=jsonSong[1].name;
		myAudio.src=jsonSong[0].fj_root+jsonSong[1].fj_root+jsonSong[1].fj_name;
		myAudio.play();
		
		if(myAudio){
			myAudio.loop = false;
			myAudio.addEventListener('ended', function () {  
				xys();
			}, false);
		}
		
		
		i++;
	});
	//上一首
	function sys(){
		if(i==1){
			return;
		}
		$("#xy").attr("src","./img/xy1.png");
		if(i==2){
			$("#sy").attr("src","./img/sy2.png");
		}
		var myAudio=document.getElementById('audio');//播放控件
		var singname=document.getElementById('singname');//歌曲名称
		var name=document.getElementById('name');//演唱者
		singname.innerHTML=jsonSong[i-1].singname;
		name.innerHTML=jsonSong[i-1].name;
		myAudio.src=jsonSong[0].fj_root+jsonSong[i-1].fj_root+jsonSong[i-1].fj_name;
		myAudio.play();
		i--;
	}
	//下一首
	function xys(){
		if(i==jsonSong.length-1){
			return;
		}
		$("#sy").attr("src","./img/sy1.png");
		if(i==jsonSong.length-2){
			$("#xy").attr("src","./img/xy2.png");
		}
		var myAudio=document.getElementById('audio');//播放控件
		var singname=document.getElementById('singname');//歌曲名称
		var name=document.getElementById('name');//演唱者
		singname.innerHTML=jsonSong[i+1].singname;
		name.innerHTML=jsonSong[i+1].name;
		myAudio.src=jsonSong[0].fj_root+jsonSong[i+1].fj_root+jsonSong[i+1].fj_name;
		myAudio.play();
		i++;
	}
	//加载全部试听歌曲
	function allSong(){
		$.ajax({
			type: 'POST',
			url: "audition",
			data: null,
			dataType: 'json',
			async: false,
			success: function (data) {
				jsonSong=data;
			},
			error:function (data) {
				alert("出错了");
			}
        });
	};
	</script>
  </head>
    <body>
	    <div class="main">
			<div class="singname">
				<div style="text-align:center;color: white;font-size:20px;" id="singname"></div>
				<div style="text-align:center;color: #E4EBF3;font-size:12px;" id="name"></div>
			</div>
			<div class="ta_c">
				<img id="imgxz" class="Rotation img" src="./img/one_xuanzhuan.png"/>
			</div>
			<div class="jdt">
				<div id="timeN" class="time" style="margin-left:1em;">0:00</div>
				<div class="scroll" id="scroll">
					<div class="bar" id="bar"></div>
					<div class="mask" id="mask"></div> 
				</div>
				<div id="timeAll" class="time" >0:00</div>
			</div>
			<div class="bottom">
				<div class="bottomDIV"><img id="sy" alt="上一首" src="./img/sy2.png" onclick="javascript:sys();"/></div>
				<div class="bottomDIV"><img id="bfzt" alt="播放/暂停" src="./img/one_zt.png" onclick="javascritp:playPause();"/></div>
				<div class="bottomDIV"><img id="xy" alt="下一首" src="./img/xy1.png" onclick="javascript:xys();"/></div>
			</div>
		</div>
		<audio id="audio" autoplay src=""></audio>
    </body>
</html>