$(function(){
		window.setInterval(jishi,1000);
		//手指拖动
		var mask = document.getElementById('mask');
		mask.addEventListener('touchmove', function(event) {
			var myAudio = document.getElementById('audio');
			var time1=myAudio.duration;//总时长
			var scrollW=$("#scroll").width();//进度条的宽度
		     // 如果这个元素的位置内只有一个手指的话
		    if (event.targetTouches.length == 1) {
				event.preventDefault();// 阻止浏览器默认事件，重要 
		        var touch = event.targetTouches[0];
		        	var left=touch.pageX-$("#scroll").offset().left;
		        	var margleft=(left)/scrollW*100;
		        	if(margleft<0)
		        		margleft=0;
		        	if(margleft>100)
		        		margleft=100;
		        	var time2=time1*margleft/100;
		        	myAudio.currentTime=time2;//改变audio.currentTime的值
		        	mask.style.marginLeft = margleft+'%';
		        }
		}, false);
		//手指触摸
		var scroll = document.getElementById('scroll');
		scroll.addEventListener('touchstart', function(event) {
			var myAudio = document.getElementById('audio');
			var time1=myAudio.duration;//总时长
			var scrollW=$("#scroll").width();//进度条的宽度
		     // 如果这个元素的位置内只有一个手指的话
		    if (event.targetTouches.length == 1) {
				event.preventDefault();// 阻止浏览器默认事件，重要 
		        var touch = event.targetTouches[0];
		        	var left=touch.pageX-$("#scroll").offset().left;
		        	var margleft=(left)/scrollW*100;
		        	if(margleft<0)
		        		margleft=0;
		        	if(margleft>100)
		        		margleft=100;
		        	var time2=time1*margleft/100;
		        	myAudio.currentTime=time2;//改变audio.currentTime的值
		        	mask.style.marginLeft = margleft+'%';
		        }
		}, false);
		//手指触摸
		var bar = document.getElementById('bar');
		bar.addEventListener('touchstart', function(event) {
			var myAudio = document.getElementById('audio');
			var time1=myAudio.duration;//总时长
			var scrollW=$("#scroll").width();//进度条的宽度
		     // 如果这个元素的位置内只有一个手指的话
		    if (event.targetTouches.length == 1) {
				event.preventDefault();// 阻止浏览器默认事件，重要 
		        var touch = event.targetTouches[0];
		        	var left=touch.pageX-$("#scroll").offset().left;
		        	var margleft=(left)/scrollW*100;
		        	if(margleft<0)
		        		margleft=0;
		        	if(margleft>100)
		        		margleft=100;
		        	var time2=time1*margleft/100;
		        	myAudio.currentTime=time2;//改变audio.currentTime的值
		        	mask.style.marginLeft = margleft+'%';
		        }
		}, false);
	});
	function jishi(){
		var myAudio = document.getElementById('audio');
		var time1=myAudio.duration;//总时长
		var time=myAudio.currentTime;//播放时长
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
		
	};
	function playPause(){
		var element = document.getElementById('bfzt');
		var myAudio = document.getElementById('audio');
		if(myAudio.paused){
            myAudio.play();
            $('#imgxz').addClass("Rotation");
            element.src = "./img/one_zt.png";
        }else{
            myAudio.pause();
            $('#imgxz').removeClass("Rotation"); 
            element.src = "./img/one_bf.png";
        }
	};