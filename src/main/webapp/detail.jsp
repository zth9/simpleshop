<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf8">
    <title>商品详情</title>
    <link rel="stylesheet" type="text/css" href="css/detail.css" charset="utf-8"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<!--商品信息-->
<div id="xianshi" class="all_width">
    <div id="xianshi_content">
        <span class="text">
            <a href="index">首页</a>
        </span>
        <span class="text">
            >
        </span>
        <span class="text">
            商品详情
        </span>
    </div>
</div>
<div id="good" class="all_width">
    <div id="good_menu">
        <div id="good_menu_picture">
            <div class="good_picture">
                <img src="http://123.57.236.58/${good.pic1}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic1}'">
            </div>
            <div class="good_picture">
                <img src="http://123.57.236.58/${good.pic2}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic2}'">
            </div>
            <div class="good_picture">
                <img src="http://123.57.236.58/${good.pic3}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic3}'">
            </div>
            <div class="good_picture">
                <img src="http://123.57.236.58/${good.pic4}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic4}'">
            </div>
        </div>
        <div id="good_menu_type">
            <div id="good_menu_type_picture1">
                <c:if test="${pic==null}">
                    <img src="http://123.57.236.58/${good.pic1}" width="467" height="467">
                </c:if>
                <c:if test="${pic!=null}">
                    <img src="http://123.57.236.58/${pic}" width="467" height="467">
                </c:if>
            </div>
        </div>
        <div id="good_menu_list">
            <h1>${good.name} </h1>
            <p>${good.detail}</p>
            <p>小米自营</p>
            <div id="xiao_zong">
                <div id="xiao">
                    <div id="xiao_picture1"></div>
                    <div id="xiao_picture2"></div>
                </div>
                <div id="xiao_picture">
                    <span></span>
                </div>
            </div>
            <div id="xiao1"></div>
            <div class="price">
                <div id="xiao2">
                    ${good.price}
                    <span class="yuan">元</span>
                </div>
            </div>
            <div id="xiao5">
                <input id="xiao3" type="button" onclick="window.location.href='addCart?goodId=${good.id}'" value="加入购物车"/>
                <input id="xiao4" type="button" value="喜欢">
            </div>
        </div>
    </div>
</div>
</body>
</html>