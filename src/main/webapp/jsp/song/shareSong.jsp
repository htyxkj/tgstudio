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
<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="./js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	$(function(){
		share();
		window.setInterval(jishi,1000);
	});
	function jishi(){
		var myAudio = document.getElementById('audio');
		var time=myAudio.currentTime;//播放时长
		var time1=myAudio.duration;//总时长
		var timeAll=document.getElementById('timeAll');//总时长
		if(timeAll==null||timeAll=="")
			timeAll=0;
		var zfm=Math.floor(time1/60)+":"+(time1%60/100).toFixed(2).slice(-2);
		if(time==null||time==""||time==null||time=="")
			zfm="0:00";
		timeAll.innerHTML=zfm;
		var timeN=document.getElementById('timeN');//已播放时间
		var fm=Math.floor(time/60)+":"+(time%60/100).toFixed(2).slice(-2);
		timeN.innerHTML=fm;
		var bar=document.getElementById('bar');//进度条
		bar.style.width=(time/time1*100)+'%';
		var mask=document.getElementById('mask');//小圆点
		mask.style.marginLeft = (time/time1*100)+'%';
		
	} 
	
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
		                //分享到朋友圈               
		                wx.onMenuShareTimeline({
		                    title:"${orderfc.name}-${orderfc.singname}", // 分享标题 
		                    link: url, // 分享链接 
		                    imgUrl: "${fileUrl}${orderfc.fj_troot}${orderfc.fj_tname}", // 分享图标
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
		                    link: "${fileUrl}${orderfc.fj_root}${orderfc.fj_name}", // 分享链接 
		                    imgUrl: "${fileUrl}${orderfc.fj_troot}${orderfc.fj_tname}", // 分享图标
		                    type: 'music', // 分享类型,music、video或link，不填默认为link 
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
	            	alert(res);
	                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	            });
		 }
	});
	};
	function playPause(){
		var element = document.getElementById('bfzt');
		var myAudio = document.getElementById('audio');
		if(myAudio.paused){
            myAudio.play();
            element.src = "./img/zt.png";
        }else{
            myAudio.pause();
            element.src = "./img/bf.png";
        }
	}
	function fx(){
		$("#yindao").show();
	}
    </script>
<style type="text/css">
body{
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    overflow-y:visible;
	overflow-x:visible;
}
.song_cover {
	overflow:hidden;
    position: absolute;
    z-index: -10;
    top:-15%;
    left: -15%;
    width: 100%;
    height: 100%;
    background: url(about:blank) no-repeat;
    background-position: center center;
    background-size: cover;
    filter: blur(6px);
    -webkit-filter: blur(6px);
    -webkit-transform: scale(1.3);
}
.yindao{
	display:none;
	z-index: 99;
	background-position: center center;
	width: 100%;
    height: 100%;
    background-size: 100% 100%;
    position:fixed;
    top:0;
}
.bg_mask {
    position: absolute;
    z-index: -9;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(21,22,25,0.4);
}
.div2{
	float: left;
	color: white;
	width: auto;
	margin:0px 10px 0 10px;
}

.scroll{ 
	margin:0.45em 0.2em 0em 0.2em;
	float:left;
	height:0.12em;
	background-color:#D0D0D0;
	z-index:10;
	width: calc(100% - 80px);
	top:-4px;
}
.bar{
	height:0.12em;
	background-color:white;
	width:0%;
}
.mask{
	margin-top:-7px;
	height:0.12em;
	width:12px;
	height:12px;
	border-radius:50px;
	border:solid rgb(100,100,100) 0px;
	background-color:white;
	margin-right:-6px;
}
.div3{
	float: left;
	color: white;
	margin-top:1em;
	width: calc(100% - 100px);
}
</style>
</head>
<body>
	<div style="margin-top:80%;">
		<div class="div2"><img style="width:80px;height:80px;" src="${fileUrl}${orderfc.fj_troot}${orderfc.fj_tname}"/></div>
		<div class="div2" style="width: calc(100% - 125px);">
			<div class="div2">
				<s:property value="orderfc.singname"/><br/>
				<span style="color:#72726F"><s:property value="orderfc.name"/></span>
			</div>
			<div class="div2" style="float: right;">
				<img id="bfzt" alt="" src="./img/zt.png" style="width: 2em;height: 2em;float:right;" onclick="javascritp:playPause();">
			</div>
		</div>
		<div class="div3">
			<div id="timeN" style="float: left;font-size:6px;margin-right:4px;margin-top:4px;">0:00</div>
			<div class="scroll" id="scroll">
				<div class="bar" id="bar"></div>
				<div class="mask" id="mask"></div> 
			</div> 
			<div id="timeAll" style="float:left;font-size:6px;margin-left:12px;margin-top:4px;">0:00</div>
		</div>
	</div>
	<audio id="audio" autoplay loop="loop" src="${fileUrl}${orderfc.fj_root}${orderfc.fj_name}"></audio>
	<div class="bg_mask" id="bg_mask" style="background: rgba(0, 0, 0, 0.2);"></div>
	<div class="song_cover" id="song_cover" style="background-image:url('${fileUrl}${orderfc.fj_troot}${orderfc.fj_tname}')"></div>
	<div class="yindao" id="yindao" >  
		<img style="width:100%;height:100%;z-index:100;background: rgba(21,22,25,0.4);" src='./img/fxyd.png'/>
	</div>
	<div style="position:fixed;bottom:0;width:100%;height:4em;text-align: center;color:white;">
		<a onclick="javascript:fx()">分享这首歌</a>
	</div>
</body>
</html>