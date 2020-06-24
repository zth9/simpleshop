package cn.javak.mapper;

import cn.javak.pojo.Role;

import java.util.List;

/**
 * @author: theTian
 * @date: 2020/3/20 17:44
 */
public interface RoleMapper {
    List<Role> selByUserId(int userId);

    int addRoleByUserId(int userId);
}
