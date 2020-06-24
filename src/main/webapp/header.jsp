<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username==null}">
                <div><a href="login.jsp" target="_blank" class="buy_car">购物车</a></div>
                <div><a href="login.jsp">注册</a></div>
                <div><a href="login.jsp">登录</a></div>
            </c:if>
            <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username!=null}">
                <div><a href="cart" target="_blank" class="buy_car">购物车（${user.baby_num}）</a></div>
                <div><a href="goodOrder?state=1">我的订单</a></div>
                <div><a href="logout">退出</a></div>
                <div>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</div>
            </c:if>
        </div>
    </div>
</diV>
<!--搜索栏-->
<div id="search" class="all_width">
    <div id="search_content">
        <div id="search_content_log">
            <a href="index"><img src="https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/3c6d55fbb2fb431619c5f9ad2da4462308f7d30b.jpg" width="55" height="55" style="position: relative;top:20px;left: 12px"></a>
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
            <input id="search_content_input_text" style="height: 24px" type="text">
            <input id="search_content_input_button" type="button" value="搜索">
        </div>
    </div>
</div>