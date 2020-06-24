package cn.javak.service;

import cn.javak.pojo.Good;

import java.util.List;

public interface GoodService {
    //通过id查询商品
    Good selById(int goodId);
    //通过商品类型查询商品(多个)
    List<Good> selByGoodTypeId(int goodTypeId);
}
