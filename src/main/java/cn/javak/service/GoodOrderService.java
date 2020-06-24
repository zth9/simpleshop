package cn.javak.service;

import cn.javak.pojo.GoodOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodOrderService {
    //按用户id提交订单
    void updSubmitOrder(int userId);
    //按订单号支付订单
    int updPayOrder(String orderId);
    //删除订单
    int delOrder(String orderId);
    //按用户id和状态码查询订单
    List<List<GoodOrder>> selAllOrder(int userId, int state);
    //按订单id查询总金额
    float selMoney(String orderId);
}
