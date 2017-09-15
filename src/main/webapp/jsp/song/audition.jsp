<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>糖果录音棚歌曲试听系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
    <link rel="stylesheet" href="./css/video.css" />
    <script type="text/javascript" src="./js/video.js"></script>
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
		function playPause(id){
			var myAudio = document.getElementById(id);
			alert(myAudio.duration);//总时长
			alert(myAudio.currentTime);//已播放时间-+
			 if(myAudio.paused){
		            myAudio.play();
		        }else{
		            myAudio.pause();
		        }
		} 
    </script>
    <style type="text/css">
    </style>
    </head>
    <body>
	    <div>
			<video  src="./mp4/video.mp4" controls="controls" x5-playsinline="" style="width:94%;height:100%;">
			  <source src="./mp4/video.mp4" type="video/mp4" >
			</video>
		</div>
		<div>
			<audio id="1" controls  src="http://p2.music.126.net/qYw9SZcdJ-gJ5UT2kzGmMw==/8898347603732273.mp3" preload="auto"></audio>
			<input type="button" onclick="javascript:playPause(1);" value="播放/暂停"/>
		</div>
    </body>
</html>
