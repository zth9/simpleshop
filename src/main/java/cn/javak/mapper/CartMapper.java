package cn.javak.mapper;

import cn.javak.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper{

    int insert(Cart cart);

    int deleteById(int id);

    int update(Cart cart);

    int updateStatus(Cart cart);

    List<Cart> selectAllByUserId(int userId);

    Cart selectById(int userId);

    /**
     * 查询选中的购物记录
     * @param userId
     * @return
     */
    List<Cart> selSelect(int userId);

    /**
     * 查询未选中的购物记录
     * @param userId
     * @return
     */
    List<Cart> selNoSelect(int userId);

    /**
     * 根据用户id和商品id查询购物车记录
     * @param cart
     * @return
     */
    Cart selByUserIdAndGoodId(Cart cart);

    /**
     * 根据用户id查询购物车数
     * @param userId
     * @return
     */
    int selCount(int userId);

}
