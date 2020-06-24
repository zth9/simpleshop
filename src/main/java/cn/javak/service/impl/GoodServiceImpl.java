package cn.javak.service.impl;

import cn.javak.mapper.GoodMapper;
import cn.javak.pojo.Good;
import cn.javak.pojo.GoodType;
import cn.javak.service.GoodService;
import cn.javak.service.GoodTypeService;
import cn.javak.utils.JsonUtils;
import cn.javak.utils.RedisUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class GoodServiceImpl implements GoodService {
    private static int size = 1000; //预计一千容量
    //redis预热
    @Autowired
    private GoodMapper goodMapper;
    private static BloomFilter<Integer> bl;
    @Override
    public Good selById(int goodId) {
        //拦截非法id
        if (goodId < 0) {
            return null;
        }
//        //初始化布隆过滤器
//        BloomFilter<Integer> bl = getBloomFilter();
//        //布隆过滤器拦截不存在的id
//        if (!bl.mightContain(goodId)){
//            return null;
//        }
        try (Jedis jedis = RedisUtil.getJedis()) {
            //从redis中获取
            if (jedis != null) {
                String goodJson = jedis.hget("good_map", "good" + goodId);
                //使用互斥锁 防止缓存击穿
                if(goodJson == null){
                    if (jedis.setnx("goodMutex", "1") == 1){
                        Good good = goodMapper.selectById(goodId);
                        jedis.hset("good_map", "good" + goodId, JsonUtils.objectToJson(good));
                        jedis.del("goodMutex");
                        goodJson = JsonUtils.objectToJson(good);
                    }else {
                        TimeUnit.MILLISECONDS.sleep(50);
                        return selById(goodId);
                    }
                }
                return JsonUtils.jsonToPojo(goodJson, Good.class);
            } else {
                //如果redis连接失败 或者 redis中没有 就从数据库中获取
                Good good = goodMapper.selectById(goodId);
                jedis.hset("good_map", "good" + goodId, JsonUtils.objectToJson(good));
                return good;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过商品类型id查询商品
     *
     * @param goodTypeId
     * @return
     */
    @Override
    public List<Good> selByGoodTypeId(int goodTypeId) {
        List<Good> goodList;
        String goodTypeList = "good_type_list";
        String curType = "type" + goodTypeId;
        try (Jedis jedis = RedisUtil.getJedis()) {
            if (jedis != null && jedis.hexists(goodTypeList, curType)) {
                String curTypeJson = jedis.hget(goodTypeList, curType);
                List<Good> goods = JsonUtils.jsonToPojo(curTypeJson, List.class);
                return goods;
            } else {
                goodList = goodMapper.selectByGoodTypeId(goodTypeId);
                if (jedis!=null){
                    jedis.hset(goodTypeList, curType, JsonUtils.objectToJson(goodList));
                }
                return goodList;
            }
        } catch (NullPointerException e) {
            return goodMapper.selectByGoodTypeId(goodTypeId);
        }
    }
    private synchronized BloomFilter<Integer> getBloomFilter(){
        if (bl==null){
            bl = BloomFilter.create(Funnels.integerFunnel(), size, 0.01);
            List<Integer> ids = goodMapper.selectId();
            for (Integer id : ids) {
                bl.put(id);
            }
            return bl;
        }else {
            return bl;
        }
    }
}
