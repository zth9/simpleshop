package cn.javak.service;

import cn.javak.pojo.Cart;

import java.util.List;

public interface CartService {
    //添加一条购物记录
    int insCart(Cart cart);
    //修改一条购物记录
    int updCart(Cart cart);
    //按id 删除一条购物记录
    int delCart(int cartId);
    //按用户id 查找所有购物记录
    List<Cart> selAll(int userId);
    //按用户id 查找为选中的购物记录
    List<Cart> selNoSelect(int userId);
    //按用户id 和 商品id 查询购物车记录
    Cart selByCart(Cart cart);
    //更新单条购物记录选中状态
    void updFlag(Cart cart);
    //全选/全不选
    void updCartListStatus(int userId);
    //查询购物车总数
    int selCount(int userId);
}
