<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ch">
<head>
    <meta charset="utf-8"/>
    <title>我的订单</title>
    <link type="text/css" href="css/index_style.css" rel="stylesheet" charset="utf-8"/>
    <link type="text/css" href="css/order.css" rel="stylesheet" charset="utf-8"/>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="main">
    <!--订单名-->
    <c:if test="${state==1}">
        <div class="order_title">
            <a class="cur_page" href="goodOrder?state=1">待付款</a>
            <span class="partition"></span>
            <a href="goodOrder?state=2">待发货</a>
            <span class="partition"></span>
            <a href="goodOrder?state=3">运输中</a>
            <span class="partition"></span>
            <a href="goodOrder?state=4">购买历史</a>
        </div>
    </c:if>
    <c:if test="${state==2}">
        <div class="order_title">
            <a href="goodOrder?state=1">待付款</a>
            <span class="partition"></span>
            <a class="cur_page" href="goodOrder?state=2">待发货</a>
            <span class="partition"></span>
            <a href="goodOrder?state=3">运输中</a>
            <span class="partition"></span>
            <a href="goodOrder?state=4">购买历史</a>
        </div>
    </c:if>
    <c:if test="${state==3}">
        <div class="order_title">
            <a href="goodOrder?state=1">待付款</a>
            <span class="partition"></span>
            <a href="goodOrder?state=2">待发货</a>
            <span class="partition"></span>
            <a class="cur_page" href="goodOrder?state=3">运输中</a>
            <span class="partition"></span>
            <a href="goodOrder?state=4">购买历史</a>
        </div>
    </c:if>
    <c:if test="${state==4}">
        <div class="order_title">
            <a href="goodOrder?state=1">待付款</a>
            <span class="partition"></span>
            <a href="goodOrder?state=2">待发货</a>
            <span class="partition"></span>
            <a href="goodOrder?state=3">运输中</a>
            <span class="partition"></span>
            <a class="cur_page" href="goodOrder?state=4">购买历史</a>
        </div>
    </c:if>
    <!--订单页显示内容循环-->
    <div class="order_content">
        <!--当没有订单时，在待付款页显示图片提示-->
        <c:if test="${state==1&&orderList.size()==0}">
            <div class="noOrder">
                <a href="index"><img src="http://123.57.236.58/pic/other/kongchaxun.png" width="1230" height="630"/></a>
            </div>
        </c:if>
        <!--有订单时，显示代支付订单列表-->
        <c:if test="${state==1&&orderList.size()!=0}">
            <c:forEach items="${orderList}" var="goodOrderList" varStatus="idxStatus">
                <div class="order_info">
                    <div class="order_head">
                        <div class="help_info">等待付款</div>
                        <div class="order_content">
                            <span>订单号：${goodOrderList.get(0).orderId}</span>
                            <span>${sessionScope.name}</span>
                            <span>在线支付</span>
                        </div>
                        <div class="order_money">
                            <span>共 <span class="money">${all_num[idxStatus.index]}</span>件商品 应付金额：<span class="money">${all_money[idxStatus.index]}</span>元</span>
                        </div>
                    </div>
                    <div class="order_list">
                        <c:forEach items="${goodOrderList}" var="goodOrder">
                            <div class="order_item">
                                <div class="pic"><img src="${goodOrder.good_pic}" width="80" height="80"/></div>
                                <div class="detail">${goodOrder.good_name}</div>
                                <div class="price">${goodOrder.price}元</div>
                                <div class="num">x${goodOrder.buy_num}</div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="submit">
                        <input class="go_pay" type="button" onclick="window.location.href='goPay?orderId=${goodOrderList.get(0).orderId}'" value="支付订单"/>
                        <input class="delete" type="button" onclick="window.location.href='deleteOrder?orderId=${goodOrderList.get(0).orderId}'" value="删除订单"/>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <!--显示待发货订单列表-->
        <c:if test="${state==2&&orderList.size()!=0}">
            <c:forEach items="${orderList}" var="goodOrderList" varStatus="idxStatus">
                <div class="order_info" style="border: 1px solid #9de0fe;">
                    <div class="order_head" style="background: rgba(157,224,254,0.4);">
                        <div class="help_info">等待发货</div>
                        <div class="order_content">
                            <span>付款时间：${goodOrderList.get(0).buy_time}</span>
                            <span>${sessionScope.name}</span>
                            <span>在线支付</span>
                        </div>
                        <div class="order_money">
                            <span>支付金额：<span class="money">${all_money[idxStatus.index]}</span>元</span>
                        </div>
                    </div>
                    <div class="order_list">
                        <c:forEach items="${goodOrderList}" var="goodOrder">
                            <div class="order_item">
                                <div class="pic"><img src="http://123.57.236.58/${goodOrder.good_pic}" width="80" height="80"/></div>
                                <div class="detail">${goodOrder.good_name}</div>
                                <div class="price">${goodOrder.price}元</div>
                                <div class="num">x${goodOrder.buy_num}</div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="submit">
                        <input class="go_pay" type="button" value="提醒发货"/>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>
