package cn.javak.mapper;

import cn.javak.pojo.UserInfo;

import java.util.List;

public interface UserMapper {

    int insert(UserInfo userInfo);

    int deleteById(int id);

    int update(UserInfo userInfo);

    List<UserInfo> selectAll();

    UserInfo selectById(int id);

    UserInfo selectByName(String userName);

    UserInfo selByUser(UserInfo userInfo);
}
