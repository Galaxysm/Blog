<%--
  Created by IntelliJ IDEA.
  User: 王一凡
  Date: 2021/1/13
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<!--[if IE 8]>
<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 8) ]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<!--<![endif]-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${options.optionSiteTitle} &lsaquo; 注册</title>
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="/img/logo.png">
    <link rel='stylesheet' id='dashicons-css' href='/plugin/login/dashicons.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='buttons-css' href='/plugin/login/buttons.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='forms-css' href='/plugin/login/forms.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='l10n-css' href='/plugin/login/l10n.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='login-css' href='/plugin/login/login.min.css' type='text/css' media='all'/>
    <style type="text/css">
        body {
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            background: url(/resource/assets/img/loginBg.jpg);
            width: 100%;
            height: 100%;
        }

        .login h1 a {
            background-size: 220px 50px;
            width: 220px;
            height: 50px;
            padding: 0;
            margin: 0 auto 1em;
        }

        .login form {
            background: #fff;
            background: rgba(255, 255, 255, 0.6);
            border-radius: 2px;
            border: 1px solid #fff;
        }

        .login label {
            color: #000;
            font-weight: bold;
        }

        #backtoblog a, #nav a {
            color: #fff !important;
        }

    </style>
    <meta name='robots' content='noindex,follow'/>
    <meta name="viewport" content="width=device-width"/>
    <style>
        body {
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }
    </style>
</head>
<body class="login login-action-login wp-core-ui  locale-zh-cn">
<%
    String username = "";
    String password = "";
    String repassword = "";
    String useremail = "";
    String usernickname = "";

%>
<div id="login">
    <form name="registerForm" id="registerForm" method="post">
        <p>
            <label for="user_login">用户名<br/>
                <input type="text" name="username" id="user_login" class="input" value="<%=username%>" size="20"
                       required/></label>
        </p>
        <p>
            <label for="user_email">电子邮件地址<br/>
                <input type="email" name="useremail" id="user_email" class="input" value="<%=useremail%>" size="20"
                       required/></label>
        </p>
        <p>
            <label for="user_nickname">昵称<br/>
                <input type="text" name="usernickname" id="user_nickname" class="input" value="<%=usernickname%>" size="20"
                       required/></label>
        </p>
        <p>
            <label for="user_pass">输入密码<br/>
                <input type="password" name="password" id="user_pass" class="input" value="<%=password%>" size="20"
                       required/>
            </label>
        </p>
        <p>
            <label for="user_pass_again">确认密码<br/>
                <input type="password" name="repassword" id="user_pass_again" class="input" value="<%=repassword%>"
                       size="20"
                       required/>
            </label>
        </p>

        <p class="submit">
            <input type="button" onclick="window.location.href='login';" name="wp-submit" id="submit-btn-login" class="button button-primary button-large"
                   style="margin-left: 5px"
                   value="返回登录"/>

            <input type="button" name="wp-submit" id="submit-btn-register" class="button button-primary button-large"
                   style="margin-left: 5px"
                   value="确认注册"/>
        </p>

    </form>

</div>
<div class="clear"></div>
<script src="/js/jquery.min.js"></script>
<script type="text/javascript">

    <%--注册验证--%>
    $("#submit-btn-register").click(function () {
        var username = $("#user_login").val();
        var useremail = $("#user_email").val();
        var usernickname = $("#user_nickname").val();
        var password = $("#user_pass").val();
        var repassword = $("#user_pass_again").val();

        if (username == "") {
            alert("用户名不可为空!");
        } else if (useremail == "") {
            alert("email不可为空!");
        } else if (usernickname == "") {
            alert("昵称不可为空!");
        } else if (password == "") {
            alert("输入密码不可为空!");
        } else if (repassword == "") {
            alert("确认密码不可为空!");
        } else if (repassword !== password) {
            alert("密码不一致!");
        } else {
            $.ajax({
                async: false,//同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/registerVerify',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $("#registerForm").serialize(),
                dataType: "json",
                success: function (data) {
                    if(data.code==0) {
                        alert(data.msg);
                    } else {
                        alert("注册成功")
                        window.location.href="/login";
                    }
                },
                error: function () {
                    alert("注册失败")

                }
            })
        }
    })

</script>
</body>
</html>

