package cn.javak.controller;

import cn.javak.mapper.CartMapper;
import cn.javak.mapper.GoodMapper;
import cn.javak.mapper.GoodOrderMapper;
import cn.javak.mapper.UserMapper;
import cn.javak.pojo.Good;
import cn.javak.pojo.UserInfo;
import cn.javak.service.CartService;
import cn.javak.service.GoodOrderService;
import cn.javak.service.GoodService;
import cn.javak.service.UserService;
import cn.javak.utils.JsonUtils;
import cn.javak.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

/**
 * 定时任务 刷新redis
 * @author: theTian
 * @date: 2020/3/27 23:38
 */
@Controller
public class RedisController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodOrderMapper goodOrderMapper;

    @Scheduled(cron = "0 0 23 * * ?")
    public void flushUser(){
        System.out.println(new Date() + "开始[刷新用户缓存]...");
        try(Jedis jedis = RedisUtil.getJedis()) {
            if (jedis!=null){
                List<UserInfo> userInfoList = userMapper.selectAll();
                for (UserInfo userInfo : userInfoList) {
                    jedis.hsetnx("user_map", userInfo.getName(), JsonUtils.objectToJson(userInfo));
                }
            }
        }catch (Exception e){
            System.out.println("用户缓存刷新失败...");
        }
        System.out.println(new Date() + "用户缓存[刷新成功]...");
    }

    @Scheduled(cron = "0 10 23 * * ? ")
    public void  flushGood(){
        System.out.println(new Date() + "开始[刷新商品缓存]...");
        /**
         * TODO
         * 如果有商品变动需求再做
         */
        System.out.println(new Date() + "商品缓存[刷新成功]...");
    }
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void flushCart(){
        System.out.println(new Date() + "开始[刷新购物缓存]...");
        try(Jedis jedis = RedisUtil.getJedis()) {
            if (jedis!=null){

            }
        }catch (Exception e){
            System.out.println("购物缓存刷新失败...");
        }
        System.out.println(new Date() + "购物缓存[刷新成功]...");
    }
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void flushGoodOrder(){
        System.out.println(new Date() + "开始[刷新订单缓存]...");
        try(Jedis jedis = RedisUtil.getJedis()) {
            if (jedis!=null){

            }
        }catch (Exception e){
            System.out.println("订单缓存刷新失败...");
        }
        System.out.println(new Date() + "订单缓存[刷新成功]...");
    }
}
