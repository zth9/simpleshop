<%@ page import="entity.Cart" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf8">
    <title>小米商城</title>
    <link type="text/css" rel="stylesheet" href="css/cart.css" charset="utf-8"/>
    <script>
        window.onload = function main() {

            sub.onclick = function () {
                buy_num.value = parseInt(buy_num.value) - 1;
            }

            add.onclick = function () {
                buy_num.value = parseInt(buy_num.value) + 1;
            }
        }

    </script>
</head>
<body>

<!--头部显示栏-->
<div class="header">
    <div class="header_main">
        <div class="left">
            <img src="https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/3c6d55fbb2fb431619c5f9ad2da4462308f7d30b.jpg"
                 onclick="window.location.href='index'" width="55" height="55">
            <div class="header_text">我的购物车</div>
        </div>
        <div class="right">
            <span class="user_order"><a href="goodOrder?state=1">我的订单</a></span>
            <span class="user_order"><a href="index">首页</a></span>
            <span class="user_name"><a href="">${sessionScope.name}</a></span>
        </div>
    </div>
</div>
<!--主要显示栏-->
<div class="main">
    <div class="cart_header">
        <div class="header_text"></div>
        <div class="user_name"></div>
        <div class="my_order"></div>
    </div>
    <c:if test="${cartList.size()==0}">
        <div class="nocart">
            <img src="pic/other/kongCart.png" width="1230" height="490">
            <input class="buy" onclick="window.location.href='index'" value="马上去购物">
        </div>
    </c:if>
    <c:if test="${cartList.size()!=0}">
        <div class="order_head">
            <div class="head_select">
                <c:if test="${flag==null}">
                    <c:if test="${flag2==true}">
                        <span onclick="window.location.href='select'" style="cursor: pointer">
                            <img src="pic/other/checked.png" style="margin-top: 28px" width="18" height="18">
                        </span>
                    </c:if>
                    <c:if test="${flag2==false}">
                        <span onclick="window.location.href='select'" style="cursor: pointer">
                        <img src="pic/other/kong.png" style="margin-top: 23px" onmouseenter="this.src='pic/other/hover.png';"
                             onmouseleave="this.src='pic/other/kong.png'"
                             width="18" height="18">
                        </span>
                    </c:if>
                </c:if>
                <c:if test="${flag==true}">
                    <span onclick="window.location.href='select'" style="cursor: pointer">
                        <img src="pic/other/checked.png" style="margin-top: 23px" width="18" height="18">
                    </span>
                </c:if>
                <c:if test="${flag==false}">
                    <span onclick="window.location.href='select'" style="cursor: pointer">
                        <img src="pic/other/kong.png" style="margin-top: 23px" onmouseenter="this.src='pic/other/hover.png';"
                             onmouseleave="this.src='pic/other/kong.png'"
                             width="18" height="18">
                    </span>
                </c:if>
                <!--<span style="margin-right:-32px">全选</span>-->
            </div>
            <div class="head_pic">预览图</div>
            <div class="head_name">商品名称</div>
            <div class="head_price">单价</div>
            <div class="head_num">数量</div>
            <div class="head_small_price">小计</div>
            <div class="head_delete">操作</div>
        </div>
        <c:forEach items="${cartList}" var="cart">
            <div class="order_item">
                <div class="select">
                    <c:if test="${cart.flag==1}">
                        <img src="pic/other/checked.png" onclick="window.location.href='select?cartId=${cart.id}'"
                             width="18" height="18">
                    </c:if>
                    <c:if test="${cart.flag==2}">
                        <img src="pic/other/kong.png" onclick="window.location.href='select?cartId=${cart.id}'"
                             onmouseenter="this.src='pic/other/hover.png';" onmouseleave="this.src='pic/other/kong.png'"
                             width="18" height="18">
                    </c:if>
                </div>
                <div class="pic"><img src="${cart.good_pic}" width="80" height="80"/></div>
                <div class="detail">${cart.good_name}</div>
                <div class="price">${cart.good_price}元</div>
                <div class="num">
                    <a href="Setnum1?id=${cart.good_id}&buy_num=${cart.num}">
                        <input id="sub" type="button" value="-">
                    </a>
                    <input id="buy_num" type="text" value="${cart.num}" style="cursor: text" name="buy_num" width="90">
                    <a href="Setnum?id=${cart.good_id}&buy_num=${cart.num}">
                        <input id="add" type="button" value="+">
                    </a>
                </div>
                <div class="small_price">${cart.money}元</div>
                <div class="delete">
                    <div class="delete_content" onclick="window.location.href='deleteCart?id=${cart.id}'">X</div>
                </div>
            </div>
        </c:forEach>
        <div class="pay">
            <div class="pay_info">
                <span><a class="go_index" href="index">继续购物</a></span>
                <span class="static">共<span class="baby_count">${sum}</span>件商品，已选则<span
                        class="baby_count">${select}</span>件</span>
            </div>
            <div class="pay_btn">
                <input class="go_pay" type="button" onclick="window.location.href='addGoodOrder'" value="提交订单"/>
                <span class="money_info">合计：<span class="all_money">${all}</span>元</span>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>
