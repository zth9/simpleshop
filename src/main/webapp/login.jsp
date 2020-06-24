<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <link type="text/css" href="css/login.css" rel="stylesheet" charset="utf-8"/>

</head>

<body>
<!--wapper-->
<div class="wrapper">
    <!--顶栏-->
    <div class="logo">
        <a href="detail?id=4" target="_blank"><img src="img/登录logo.png" width="205px" height="98px"/></a>
    </div>
    <!--主栏-->
    <div class="main">
        <a href="detail?id=4">
            <div class="background">
                <img src="pic/other/denglu.png" width="760" height="554">
            </div>
        </a>
        <!--登录-->
        <div class="login_frame">
            <div class="login_info" style="border-right: 1px solid rgba(168,168,168,0.54)">
                <span class="login_text"
                      onclick="document.getElementById('login_frame').style.display = 'block';
                      document.getElementById('register_frame').style.display = 'none';
                      document.getElementById('login_text').style.color='#f56600';
                      document.getElementById('register_text').style.color='#666666';
                        ">
                    <span id="login_text" >登录</span>
                </span>
            </div>
            <div class="register_info" >
                <span class="register_text" onclick="document.getElementById('login_frame').style.display = 'none';
            document.getElementById('register_frame').style.display = 'block';
            document.getElementById('register_text').style.color='#f56600';
            document.getElementById('login_text').style.color='#666666';
                        ">
                    <span id="register_text" >注册</span>
                </span>
            </div>
            <div class="login" id="login_frame">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="account_password">
                        <input class="account" type="text" value="guests" id="login_name" name="username" placeholder="邮箱/手机号码/小米ID" autocomplete="off"/>
                        <input class="password" type="password" id="login_password" value="guests" name="password" placeholder="密码" autocomplete="off"/>
                    </div>
                    <input class="loginBtn" type="submit" id="login" value="登录"/>
                </form>
            </div>
        </div>
        <!--注册-->
        <div id="register_frame">
            <form action="${pageContext.request.contextPath}/register" method="post">
                <div class="register_account_password">
                    <input id="register_name" class="account" type="text" name="name" placeholder="邮箱/手机号码/小米ID" autocomplete="off"/>
                    <input id="register_pwd1" class="password" type="password" name="password" placeholder="密码" autocomplete="off"/>
                    <input id="register_pwd2" class="password2" type="password" name="password2" placeholder="来，再输一次" autocomplete="off"/>
                    <input id="register_btn" class="loginBtn" type="submit" value="注册"/>
                </div>
            </form>
        </div>
    </div>
</div>

</div>
<!--footer
<div class="footer">
    <div class="option">
        <a href="" target="_blank">简体</a><i>|</i>
        <a href="" target="_blank">繁体</a><i>|</i>
        <a href="" target="_blank">English</a><i>|</i>
        <a href="" target="_blank">常见问题</a><i>|</i>
        <a href="" target="_blank">隐私政策</a>
    </div>
    <div class="webInfo">
        <p class="Infotext">小米公司版权所有-京ICP备10046444-京公网安备11010802020134号-京ICP证110507号</p>
    </div>
</div>-->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/my.js">
    window.onload=function () {
        if(${msg!=null}){
            window.alert("${msg}")
        }
    }
</script>
</body>

</html>