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
<link href="./css/video-js.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<style type="text/css">
*{margin:0px;padding:0px;}
body{
}
.video-js{
    width:100%;
}
</style>
</head>
<body>
	<div>
		<video id="my-video" class="video-js vjs-big-play-centered" x5-video-player-type="h5"  x5-playsinline=""  controls webkit-playsinline="true" preload="auto"  playsinline>
			<source src="http://www.bip-soft.com/file/db_01/fj_reservationb/Y201709/97d5e75e606a0ea46443ac0e4ff6925.mp4" type='video/mp4'>
		</video>
	</div>
    <script src="http://cdn.bootcss.com/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://vjs.zencdn.net/5-unsafe/video.js"></script>
    <script>
        (function($){
            var list = [
                {name:"name", url: "http://www.bip-soft.com/file/db_01/fj_reservationb/Y201709/97d5e75e606a0ea46443ac0e4ff6925.mp4", lastTime:0}, 
            ];
            var resetVideoSize = function(myPlayer){
                var videoJsBoxWidth = $(".video-js").width();
                var ratio = 1440/900;
                var videoJsBoxHeight = videoJsBoxWidth/ratio;
                myPlayer.height(videoJsBoxHeight);
            };
            var myPlayer = videojs("my-video").ready(function(){
                var i = 0;
                var videoObj = list[i];
                var lastTime = localStorage.getItem(videoObj.name + ".currentTime") || 0;
                this.currentTime(lastTime); 
                this.width("100%");
                resetVideoSize(myPlayer);
                //绑定视频播放结束事件
                this.on("ended", function(){
                    if(i >= list.length - 1){
                      i = 0;
                      this.pause();
                      return;
                    }
                    i++;
                    this.src({type: "video/mp4", src: videoObj.url});
                    localStorage.setItem(videoObj.name+".currentTime", 0);
                    this.play();
                }); 
                //绑定视频播放中事件
                this.on("timeupdate", function(data){ 
                    if(this.currentTime() - lastTime > 2){
                        lastTime = this.currentTime();
                        localStorage.setItem(videoObj.name+".currentTime", lastTime);
                    }
                });
                this.play();
            });
            $(window).on("resize", function(){
                resetVideoSize(myPlayer); 
            }); 
        })(jQuery);
    </script>
</body>
</html>