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
                <div><a href="goodOrder?state=1">我的订单</a></div>
                <div><a href="logout">退出</a></div>
                <div>${sessionScope.name}</div>
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
                <img src="${good.pic1}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic1}'">
            </div>
            <div class="good_picture">
                <img src="${good.pic2}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic2}'">
            </div>
            <div class="good_picture">
                <img src="${good.pic3}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic3}'">
            </div>
            <div class="good_picture">
                <img src="${good.pic4}" width="69" height="69" onclick="window.location.href='detail?id=${good.id}&pic=${good.pic4}'">
            </div>
        </div>
        <div id="good_menu_type">
            <div id="good_menu_type_picture1">
                <c:if test="${pic==null}">
                    <img src="${good.pic1}" width="467" height="467">
                </c:if>
                <c:if test="${pic!=null}">
                    <img src="${pic}" width="467" height="467">
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
                <input id="xiao3" type="button" onclick="window.location.href='addCart?id=${good.id}'" value="加入购物车"/>
                <input id="xiao4" type="button" value="喜欢">
            </div>
        </div>
    </div>
</div>
</body>
</html>