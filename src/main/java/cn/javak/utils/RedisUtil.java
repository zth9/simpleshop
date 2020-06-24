package cn.javak.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类
 * @author Tian90
 */
public class RedisUtil {

    //服务器IP地址
    private static String ADDR = "127.0.0.1";
    //端口
    private static int PORT = 6379;
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时的时间　　
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    //数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;
    /**
     * 初始化Redis连接池 饿汉单例
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
//            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH,DEFAULT_DATABASE);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    /**
     * 获取Jedis实例
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                System.out.println("redis--服务正在运行: "+resource.ping()+"活跃连接:["+jedisPool.getNumActive()+"] 空闲连接["+jedisPool.getNumIdle()+"]");
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     *
     * 释放资源
     */
    public static void returnResource(final Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }
}