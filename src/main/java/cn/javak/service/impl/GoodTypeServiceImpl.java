package cn.javak.service.impl;

import cn.javak.mapper.GoodTypeMapper;
import cn.javak.pojo.GoodType;
import cn.javak.service.GoodTypeService;
import cn.javak.utils.JsonUtils;
import cn.javak.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GoodTypeServiceImpl implements GoodTypeService {
    @Autowired
    private GoodTypeMapper goodTypeMapper;
    @Override
    public List<GoodType> selAll() {
        List<GoodType> goodTypeList;
        try(Jedis jedis = RedisUtil.getJedis()){
            //从redis中获取
            if (jedis!=null && jedis.exists("good_Type_map")) {
                goodTypeList = new ArrayList<>(DEFAULT_TYPE_NUM);
                Map<String, String> goodTypeMap = jedis.hgetAll("good_Type_map");
                Set<String> strings = goodTypeMap.keySet();
                for (String goodType : strings) {
                    String goodTypeJson = goodTypeMap.get(goodType);
                    goodTypeList.add(JsonUtils.jsonToPojo(goodTypeJson, GoodType.class));
                }
                return goodTypeList;
            } else {
                //如果redis崩了 或者 goodTypeMap没了 就走数据库
                return goodTypeMapper.selectAll();
            }
        }
    }

    @Override
    public GoodType selById(int goodTypeId) {
        if (goodTypeId < 0){
            return null;
        }
        try(Jedis jedis = RedisUtil.getJedis()){
            //从redis中获取
            if (jedis!=null && jedis.hexists("good_Type_map", "goodType"+goodTypeId)) {
                String goodJson = jedis.hget("good_Type_map", "goodType"+goodTypeId);
                return JsonUtils.jsonToPojo(goodJson, GoodType.class);
            } else {
                //Redis没有或者崩了 就从数据库中获取
                GoodType goodType = goodTypeMapper.selectById(goodTypeId);
                jedis.hset("good_Type_map", "goodType"+goodTypeId, JsonUtils.objectToJson(goodType));
                return goodType;
            }
        }
    }
}
