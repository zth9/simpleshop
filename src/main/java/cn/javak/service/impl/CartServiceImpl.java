package cn.javak.service.impl;

import cn.javak.mapper.CartMapper;
import cn.javak.pojo.Cart;
import cn.javak.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public int insCart(Cart cart) {
        return cartMapper.insert(cart);
    }

    @Override
    public int updCart(Cart cart) {
        return cartMapper.update(cart);
    }

    @Override
    public int delCart(int cartId) {
        return cartMapper.deleteById(cartId);
    }

    @Override
    public List<Cart> selAll(int userId) {
        return cartMapper.selectAllByUserId(userId);
    }

    @Override
    public List<Cart> selNoSelect(int userId) {
        return cartMapper.selNoSelect(userId);
    }

    @Override
    public Cart selByCart(Cart cart) {
        return cartMapper.selByUserIdAndGoodId(cart);
    }

    ////改变单条记录选中状态
    @Override
    public void updFlag(Cart cart){
        Cart selCart = cartMapper.selByUserIdAndGoodId(cart);
        if (selCart.flag == 1){
            selCart.setFlag(2);
            cartMapper.updateStatus(selCart);
        }else {
            selCart.setFlag(1);
            cartMapper.updateStatus(selCart);
        }
    }
    ////改变所有购物记录选中状态 未全选-->全选/全选-->全不选
    @Override
    public void updCartListStatus(int userId){
        List<Cart> cartList = selAll(userId);
        boolean isSelectAll = true;
        for (int i=0;i<cartList.size();i++){
            Cart cart = cartList.get(i);
            if (cart.flag == 2){
                isSelectAll = false;
                cart.setFlag(1);
                cartMapper.updateStatus(cart);
            }
        }
        if (isSelectAll){
            for (int i=0;i<cartList.size();i++){
                Cart cart = cartList.get(i);
                if (cart.flag == 1){
                    cart.setFlag(2);
                    cartMapper.updateStatus(cart);
                }
            }
        }
    }

    @Override
    public int selCount(int userId) {
        return cartMapper.selCount(userId);
    }
}
