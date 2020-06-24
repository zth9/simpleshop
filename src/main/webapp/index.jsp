<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ch">

<head>
    <meta charset="UTF-8"/>
    <title>【仅供学习-小米商城】</title>
    <link type="text/css" href="css/index_style.css" rel="stylesheet" charset="utf-8"/>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<!--商品栏-->
<div id="good" class="all_width">
    <div id="good_menu">
        <div id="good_background"></div>
        <div id="good_menu_type">
            <c:forEach items="${goodTypeList}" var="goodType" end="7">
                <div class="good_menu_type_name" onclick="window.location.href=href='index?goodTypeId=${goodType.id}'">
                        ${goodType.name}
                    <span>></span>
                </div>
            </c:forEach>
            <div class="good_menu_type_name_detail">
                <!--八个产品-->
                <div class="good_item_list">
                    <c:forEach items="${goodList}" var="good"  end="7">
                        <div class="good_item">
                            <div class="good_item_new"></div>
                            <div class="good_item_img">
                                <a href="detail?id=${good.id}" target="_self"><img src="http://123.57.236.58/${good.pic1}" width="160" height="160"/></a>
                            </div>
                            <div class="good_item_name">
                                    ${good.name}
                            </div>
                            <div class="good_item_price">
                                    ${good.price}元
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${goodList.size()==0}">
                        <div id="good_item_list">
                            <img src="pic/other/kongchaxun.png" />
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<!--推广栏-->
<div id="tuiguang">
    <a href="" target="_blank"><img alt="小米8屏幕指纹" src="//i1.mifile.cn/a4/xmad_1560592743757_kGyBr.jpg"
                                    width="1226"/></a>
</div>
<!--底部-->
<div id="bottom" class="all_width">
    <div id="bottom_content">
        <div id="bottom_content_left">
            <img src="https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/3c6d55fbb2fb431619c5f9ad2da4462308f7d30b.jpg"
                 width="55" height="55" style="position: relative">
        </div>
        <div id="bottom_content_right">
            <p class="p1">
                <a href="" target="_blank">小米商城</a><i>|</i>
                <a href="" target="_blank">MIUI</a><i>|</i>
                <a href="" target="_blank">米家</a><i>|</i>
                <a href="" target="_blank">米聊</a><i>|</i>
                <a href="" target="_blank">小米商城</a><i>|</i>
                <a href="" target="_blank">多看</a><i>|</i>
                <a href="" target="_blank">小米商城</a><i>|</i>
                <a href="" target="_blank">游戏</a><i>|</i>
                <a href="" target="_blank">路由器</a><i>|</i>
                <a href="" target="_blank">米粉卡</a><i>|</i>
                <a href="" target="_blank">政企服务</a><i>|</i>
                <a href="" target="_blank">小米天猫店</a><i>|</i>
                <a href="" target="_blank">隐私政策</a><i>|</i>
                <a href="" target="_blank">商城用户协议</a><i>|</i>
                <a href="" target="_blank">账号协议</a><i>|</i>
                <a href="" target="_blank">问题反馈</a><i>|</i>
                <a href="" target="_blank">廉政举报</a><i>|</i>
                <a href="" target="_blank">诚信合规</a><i>|</i>
                <a href="" target="_blank">Select Region</a>
            </p>
            <p class="p2">© mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2017]1530-131号<br/>
                （京）网械平台备字（2018）第00005号 互联网药品信息服务资格证 (京) -非经营性-2014-0090 营业执照 医疗器械公告<br/> 增值电信业务许可证
                网络食品经营备案（京）【2018】WLSPJYBAHF-12 食品经营许可证<br/> 违法和不良信息举报电话：185-0130-1238 知识产权侵权投诉
                本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
        </div>
    </div>
</div>

</body>

</html>
