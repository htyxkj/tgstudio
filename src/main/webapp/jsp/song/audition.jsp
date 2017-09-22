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
    <!-- 
	    <div>
			<video  src="./mp4/video.mp4" controls="controls" x5-playsinline="" style="width:94%;height:100%;">
			  <source src="./mp4/video.mp4" type="video/mp4" >
			</video>
		</div>
		 -->
		<div>
		<audio id="1" src="http://192.168.0.106:9999/jd/fileupdown?fud=1&rid=4&isweb=1&dbid=101&filepath=fj_reservationb/Y201709/123.mp3" controls="controls" preload="auto">
		</audio>  
		<input type="button" onclick="javascript:playPause(1);" value="播放/暂停"/>
		
		<a href="http://192.168.0.106:9999/jd/fileupdown?fud=1&rid=4&isweb=1&dbid=101&filepath=fj_reservationb/Y201709/123.mp3">sasa</a>
		</div>
    </body>
</html>
