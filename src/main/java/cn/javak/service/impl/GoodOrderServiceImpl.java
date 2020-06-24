package cn.javak.service.impl;

import cn.javak.mapper.CartMapper;
import cn.javak.mapper.GoodMapper;
import cn.javak.mapper.GoodOrderMapper;
import cn.javak.mapper.UserMapper;
import cn.javak.pojo.Cart;
import cn.javak.pojo.Good;
import cn.javak.pojo.GoodOrder;
import cn.javak.pojo.UserInfo;
import cn.javak.service.GoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GoodOrderServiceImpl implements GoodOrderService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodOrderMapper goodOrderMapper;
    //提交订单
    @Override
    public void updSubmitOrder(int userId) {
        //查询出用户
        UserInfo userInfo = userMapper.selectById(userId);
        //查出所有提交的购物记录
        List<Cart> cartList = cartMapper.selSelect(userId);
        //从购物车中删除对应的记录 并 装载 订单
        GoodOrder goodOrder = new GoodOrder();
        UUID uuid = UUID.randomUUID();
        for (Cart cart:cartList){
            cartMapper.deleteById(cart.getId());
            goodOrder.setUser_id(cart.user_id);
            goodOrder.setGood_id(cart.good_id);
            goodOrder.setBuy_num(cart.num);
            goodOrder.setBuy_time(cart.buy_time);
            goodOrder.setState(1);
            goodOrder.setMoney(cart.money);
            goodOrder.setOrderId(uuid.toString());
            goodOrderMapper.insertOrder(goodOrder);
        }
    }

    //支付订单
    @Override
    public int updPayOrder(String orderId) {
        return goodOrderMapper.updOrder(orderId);
    }

    //删除订单
    @Override
    public int delOrder(String orderId) {
        return goodOrderMapper.delOrder(orderId);
    }

    //访问订单
    @Override
    public List<List<GoodOrder>> selAllOrder(int userId, int state) {
        //查询用户所有的订单号(根据状态)
        List<String> orderNoList = goodOrderMapper.selAllOrderNo(userId, state);
        //装配订单
        List<List<GoodOrder>> orderList = new ArrayList<>();
        //存在订单时查询
        if (orderNoList!=null){
            for (String orderNo:orderNoList){
                List<GoodOrder> goodOrderList = goodOrderMapper.selAllOrder(orderNo);
                for (int i=0;i<goodOrderList.size();i++){
                    GoodOrder goodOrder = goodOrderList.get(i);
                    goodOrder.setUser_name(userMapper.selectById(userId).name);
                    Good good = goodMapper.selectById(goodOrder.good_id);
                    goodOrder.setGood_name(good.name);
                    goodOrder.setGood_pic(good.pic1);
                    goodOrder.setPrice(good.price);
                }
                orderList.add(goodOrderList);
            }
        }
        return orderList;
    }

    @Override
    public float selMoney(String orderId) {
        return goodOrderMapper.selMoney(orderId);
    }
}
