<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>糖果录音棚客户曲歌系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript">
		 
    </script>
    <style type="text/css">
            .parent{
                position: fixed;
                top: 50%;
                bottom: 50%;
                left: 50%;
                right: 50%;
                width: 0;
                height: 0;
            }
            .show{
                border: 0px;
                height: 10rem;
                width: 10rem;
                /* 以下两个属性必须为width,height值的负一半 */
                margin-left: -5rem;
                margin-top: -5rem;
            }
            table{
                width: 100%;
                height: 100%;
                text-align: center;
            }
            td{
                border: 0px;
            }
        </style>
    </head>
    <body>
        <div class="parent">
            <div class="show">
                <table>
                    <tr>
                        <td><input type="text" placeholder="请输入您的手机号码"/></td>
                    </tr>
                    <tr>
                        <td><input type="text" placeholder="请输入验证码"/></td>
                    </tr>
                    <tr>
                        <td><input type="button" value="登录"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
