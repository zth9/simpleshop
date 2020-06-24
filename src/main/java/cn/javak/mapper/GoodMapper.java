package cn.javak.mapper;

import cn.javak.pojo.Good;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodMapper {

    List<Good> selectAll();

    Good selectById(int goodId);

    List<Good> selectByGoodTypeId(int goodTypeId);

    List<Integer> selectId();
}
