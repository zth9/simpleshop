package cn.javak.mapper;

import cn.javak.pojo.GoodOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodOrderMapper {
    //新增单条订单记录
    int insertOrder(GoodOrder goodOrder);
    //按用户id 查询所有 订单号
    List<String> selAllOrderNo(int userId, int state);
    //按订单号查询该订单号的所有记录
    List<GoodOrder> selAllOrder(String orderId);
    //按订单号删除订单记录
    int delOrder(String orderId);
    //按订单号 支付
    int updOrder(String orderId);
    //按订单号 查询金额
    float selMoney(String orderId);
}
