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
<meta name="format-detection" content="telephone=no">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="./img/ico.ico" rel="shortcut icon" />
<link href="./css/video-js.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="./js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="./js/shareSong.jsp.js"></script>
<script type="text/javascript">
$(function(){
	AndroidOriphone();
	share();
});
function share(){
	var url=location.href.split('#')[0];
	var data={'url':url};
	 $.ajax({
	        type:"post",
	        url:"ApiVerifu",
	        data:data,
	        dataType:'json',
	        async: false,
	        success: function(data) {
	            //拿到返回的id
	            var getAppId = data.appid;
	            var getTimestamp = data.timestamp;
	            var getNonceStr = data.nonceStr;
	            var getSignature = data.signature;
	            wx.config({
	                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。 
	                appId: getAppId, // 必填，公众号的唯一标识 
	                timestamp: getTimestamp, // 必填，生成签名的时间戳 
	                nonceStr: getNonceStr, // 必填，生成签名的随机串 
	                signature: getSignature,// 必填，签名，见附录1 
	                jsApiList: [ // 必填，需要使用的JS接口列表，所有JS接口列表见附录2   朋友圈，朋友
	                    'onMenuShareTimeline',
	                    'onMenuShareAppMessage', 
	                ] 
	            });
	            //config验证成功
	            wx.ready(function(){
	            	//document.getElementById('audio').play();
	                //分享到朋友圈               
	                wx.onMenuShareTimeline({
	                    title:"${orderfc.name}-${orderfc.singname}", // 分享标题 
	                    link: url, // 分享链接 
	                    imgUrl: "${fileUrl}/x_logo.jpg", // 分享图标
	                    success: function (res) { 
	                    	$("#yindao").hide();
	                    	alert('分享成功');
	                    }, 
	                    cancel: function (res) {
	                    	$("#yindao").hide();
	                    	//weui.toast('取消分享');
	                    }
	                });
	                //分享到朋友                 
	                wx.onMenuShareAppMessage({
	                    title:"${orderfc.singname}", // 分享标题 
	                    desc: "${orderfc.name}", // 分享描述
	                    link: url, // 分享链接 
	                    imgUrl: "${fileUrl}/x_logo.jpg", // 分享图标
	                    type: 'video', // 分享类型,music、video或link，不填默认为link 
	                    dataUrl:"${fileUrl}${orderfc.fj_root}${orderfc.fj_name}", // 如果type是music或video，则要提供数据链接，默认为空 
	                    success: function () { 
	                    	$("#yindao").hide();
							alert('分享成功');
	                    },
	                    cancel: function () {
	                    	$("#yindao").hide();
	                    }
	                });
	        });
	        //验证失败
            wx.error(function(res){
            	//alert(res);
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
            });
	 	}
	});
};	
function fx(){
	$("#yindao").show();
	
	setTimeout("hidexz()","4000");
} 
function hidexz(){
	$("#yindao").hide();
}
function AndroidOriphone(){
	var u = navigator.userAgent;
	if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
		
	} else if (u.indexOf('iPhone') > -1||u.indexOf('iPad') > -1) {//苹果手机
		$("img[alt='下载']").hide();
	}
}
</script>
<style type="text/css">
*{margin:0px;padding:0px;}
body{
}
.video-js{
            width:100%;
        }
 .yindao{
	display:none;
	z-index: 99;
	background-position: center center;
	width: 100%;
    position:fixed;
    top:0;
}
</style>
</head>
<body>
	<div style="margin:15px 0px 10px 0px">
		<img style="width:100%" src="./img/videojsp.png" />
	</div>
	<div style="width:100%; ">
		<video id="my-video" class="video-js vjs-big-play-centered" x5-playsinline="" controls preload="auto" autoplay>
			<source src="${fileUrl}${orderfc.fj_root}${orderfc.fj_name}" type='video/mp4'>
		</video>
	</div>
<div style="padding:0 20px;">
	<div>
		<div style="font-size:20px;padding-top:0.6em;">
		<b><s:property value="orderfc.singname"/>&nbsp;</b>
		<span style="font-size:12px;padding-top:0.1em;"><s:property value="orderfc.name"/></span>
		<img style="float:right;width:28px;" alt="分享" src="./img/v_fx.png" onclick="javascript:fxMV()">
		<img style="float:right;width:28px;padding-right:10px;"  alt="下载" src="./img/v_xz.png" onclick="javascript:xiazaiMV('${dowUrl}${orderfc.fj_root}${orderfc.fj_name}')">
		</div>
	</div>
	<div style="padding-top:1em;">
		
	</div>
</div>
	<div class="yindao" id="yindao" >  
		<img id="yindaoimg" style="width:100%;height:100%;z-index:100;background: rgba(21,22,25,0.4);" src='./img/v_fxyd.png'/>
	</div>
    <script src="http://cdn.bootcss.com/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://vjs.zencdn.net/5-unsafe/video.js"></script>
    <script>
        (function($){
            var list = [
                {name:"${orderfc.fj_name}", url: "${fileUrl}${orderfc.fj_root}${orderfc.fj_name}", lastTime:0}, 
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
                this.autoPlay=true;
                myPlayer.play();
            });
            $(window).on("resize", function(){
                resetVideoSize(myPlayer); 
            }); 
        })(jQuery);
    </script>
</body>
</html>