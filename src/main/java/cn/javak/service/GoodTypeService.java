package cn.javak.service;

import cn.javak.pojo.GoodType;

import java.util.List;

public interface GoodTypeService {
    //默认的GoodType数量
    int DEFAULT_TYPE_NUM = 10;
    //查询所有商品类型
    List<GoodType> selAll();
    //通过id查询商品类型
    GoodType selById(int goodTypeId);
}
