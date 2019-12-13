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
<!--导航栏-->
<diV id="top" class="all_width">
    <div id="top_menu">
        <div id="top_menu_left" class="top_menu_left_a">
            <div><a href="">小米商城</a></div>
            <div><a href="">MIUI</a></div>
            <div><a href="">loT</a></div>
            <div><a href="">云服务</a></div>
            <div><a href="">金融</a></div>
            <div><a href="">有品</a></div>
            <div><a href="">小爱开放平台</a></div>
            <div><a href="">企业团购</a></div>
            <div><a href="">资质证照</a></div>
            <div><a href="">协议规则</a></div>
            <div><a href="">下载app</a></div>
            <div><a href="">Select Region</a></div>
        </div>
        <div id="top_menu_right" class="top_menu_right_a">
            <c:if test="${name==null}">
                <div><a href="login.jsp" target="_blank" class="buy_car">购物车</a></div>
                <div><a href="login.jsp">注册</a></div>
                <div><a href="login.jsp">登录</a></div>
            </c:if>
            <c:if test="${name!=null}">
                <div><a href="cart" target="_blank" class="buy_car">购物车（${baby_num}）</a></div>
                <div>我的订单</div>
                <div><a href="logout">退出</a></div>
                <div><a href="index" target="_blank" class="index">首页</a></div>
                <div>${sessionScope.name}</div>
            </c:if>
        </div>
    </div>
</diV>
<!--搜索栏-->
<div id="search" class="all_width">
    <div id="search_content">
        <div id="search_content_log">
            <a href="index"><img
                    src="https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/3c6d55fbb2fb431619c5f9ad2da4462308f7d30b.jpg"
                    width="55" height="55" style="position: relative;top:20px;left: 12px"></a>
        </div>
        <div id="search_content_key">
            <div><a href="" class="search_content_key_word">红米手机</a></div>
            <div><a href="" class="search_content_key_word">小米笔记本</a></div>
            <div><a href="" class="search_content_key_word">电视</a></div>
            <div><a href="" class="search_content_key_word">笔记本</a></div>
            <div><a href="" class="search_content_key_word">家电</a></div>
            <div><a href="" class="search_content_key_word">路由器</a></div>
            <div><a href="" class="search_content_key_word">智能硬件</a></div>
            <div><a href="" class="search_content_key_word">服务</a></div>
            <div><a href="" class="search_content_key_word">社区</a></div>
        </div>
        <div id="search_content_input">
            <input id="search_content_input_text" type="text">
            <input id="search_content_input_button" type="button" value="搜索">
        </div>
    </div>
</div>
<!--当前位置-->
<div class="local">
    <div class="local_text">
        <a href="index">首页</a>
        <span class="sep">></span>
        <span class="sep">我的订单</span>
    </div>
</div>

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
        <c:if test="${orderList.get(0).size()==0&&state==1}">
            <div class="noOrder">
                <a href="index"><img src="pic/other/kongchaxun.png" width="1230" height="630"/></a>
            </div>
        </c:if>
        <!--有订单时，显示代支付订单列表-->
        <c:if test="${orderList.get(0).size()!=0&&state==1}">
            <c:forEach items="${orderList}" var="goodOrderList" varStatus="idxStatus">
                <div class="order_info">
                    <div class="order_head">
                        <div class="help_info">等待付款</div>
                        <div class="order_content">
                            <span>提交时间：${goodOrderList.get(0).orderId}</span>
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
                        <input class="go_pay" type="button" onclick="window.location.href='pay?orderId=${goodOrderList.get(0).orderId}'" value="支付订单"/>
                        <input class="delete" type="button" onclick="window.location.href='deleteOrder?orderId=${goodOrderList.get(0).orderId}'" value="删除订单"/>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <!--显示待发货订单列表-->
        <c:if test="${orderList.get(0).size()!=0&&state==2}">
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
                                <div class="pic"><img src="${goodOrder.good_pic}" width="80" height="80"/></div>
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
