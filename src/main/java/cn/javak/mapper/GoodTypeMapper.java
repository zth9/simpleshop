package cn.javak.mapper;

import cn.javak.pojo.GoodType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodTypeMapper {

    List<GoodType> selectAll();

    GoodType selectById(int id);
}
