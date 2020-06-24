<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link type="text/css" href="css/pay.css" rel="stylesheet" charset="utf-8"/>
</head>
<body>
<div class="topbar">
    <div class="topbar_content">
        <a href="https://help.alipay.com/lab/help_detail.htm?help_id=258086" target="_blank">常见问题</a>
        <span>你好，欢迎使用支付宝支付！</span>
    </div>
</div>
<div class="header">
    <div class="header_content">
        <div class="logo"><img src="pic/other/zhifubao.png" width="114" height="40"></div>
        <div class="text">我的收银台</div>
    </div>
</div>
<div class="main">
    <div class="order_info">
        <div class="time">
            <span class="span1">
                正在使用即时到账交易,请及时付款
            </span>
        </div>
        <div class="order_money">
            <span class="span1">${all_money}</span>
            <span class="span2">元</span>
        </div>
    </div>
    <div class="pay_frame">
        <div class="pay_frame_left">
            <div class="QRcode">
                <div class="tip">扫一扫付款（元）</div>
                <div class="money">${all_money}</div>
                <div class="QRpic">
                    <div class="pic">
                        <img src="pic/other/QR.png" width="168px" height="168px">
                    </div>
                    <div class="help">
                        <div class="help_pic">
                            <img src="pic/other/saomiao.png">
                        </div>
                        <div class="help_text">打开手机支付宝扫一扫继续付款</div>
                    </div>
                </div>
            </div>
            <div class="help"></div>
        </div>
        <div class="pay_frame_right">
            <div class="help">
                <img src="pic/other/zhifubao2.png" width="150" height="150">
            </div>
            <div class="paynow">
                <input class="paynow_btn" type="button"
                       onclick="window.location.href='pay?orderId=${orderId}'"
                       value="立刻支付"/><br>
                <input class="paynow_btn" type="button" onclick="" value="立刻充值"/>
            </div>
            <div class="user_money">
                我的余额：<span>${user.money}</span>元
            </div>
        </div>
    </div>
</div>
</body>
</html>
