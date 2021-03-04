<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Cache-Control" content="max-age=72000"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="applicable-device" content="pc,mobile">
    <meta name="MobileOptimized" content="width"/>
    <meta name="HandheldFriendly" content="true"/>
    <link rel="shortcut icon" href="/img/logo.png">
    <rapid:block name="description">
        <meta name="description" content="${options.optionMetaDescrption}"/>
    </rapid:block>
    <rapid:block name="keywords">
        <meta name="keywords" content="${options.optionMetaKeyword}"/>
    </rapid:block>
    <rapid:block name="title">
        <title>
                ${options.optionSiteTitle}-${options.optionSiteDescrption}
        </title>
    </rapid:block>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">

    <rapid:block name="header-style">

    </rapid:block>

<%--    小火箭--返回至顶部--格式      --%>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="http://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js"></script>
    <style>
        .header {
            color: rgb(243, 152, 6);
            width: 100%;
            height: 200px;
            margin: 0 auto;
            text-align: center;
            font-size: 20px;
        }
        .footer {
            position: fixed;
            right: 50px;
            bottom: 50px;
            width: 54px;
            height: 54px;
        }
    </style>

<%--  背景图片全图设置Begin--%>
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            background: url(/resource/assets/img/indexBg.png);
            width:100%;
            height:100%;
        }
    </style>
    <style>
        body {
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }
    </style>
    <%--  背景图片全图设置End--%>
</head>
<body>
<div id="page" class="site" style="transform: none;">

    <%@ include file="part/header.jsp" %>
    <div id="content" class="site-content" style="transform: none;">
        <rapid:block name="left"></rapid:block>
        <rapid:block name="right">
            <%@ include file="part/sidebar-1.jsp" %>
        </rapid:block>
    </div>
    <div class="clear"></div>
    <rapid:block name="link"></rapid:block>
    <%@ include file="part/footer.jsp" %>

</div>

<script src="/js/jquery.min.js"></script>
<script src="/js/superfish.js"></script>
<script src='/js/sticky.js'></script>
<script src="/js/script.js"></script>
<script src="/plugin/layui/layui.all.js"></script>


<rapid:block name="footer-script"></rapid:block>

<img class="footer" id="gotoTop" src="/img/4f3b2858eec9e2cf.jpg" alt="小火箭">
<script>
    function gotoTop(minHeight){

        // 定义点击返回顶部图标后向上滚动的动画
        $("#gotoTop").click(
            function(){$('html,body').animate({scrollTop:'0px'},'slow');
            })

        // 获取页面的最小高度，无传入值则默认为600像素
        minHeight? minHeight = minHeight:minHeight = 600;

        // 为窗口的scroll事件绑定处理函数
        $(window).scroll(function(){

            // 获取窗口的滚动条的垂直滚动距离
            var s = $(window).scrollTop();

            // 当窗口的滚动条的垂直距离大于页面的最小高度时，让返回顶部图标渐现，否则渐隐
            if( s > minHeight){
                $("#gotoTop").fadeIn(500);
            }else{
                $("#gotoTop").fadeOut(500);
            };
        });
    };
    gotoTop();
</script>
</body>
</html>