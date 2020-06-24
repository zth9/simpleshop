package cn.javak.service.impl;

import cn.javak.mapper.RoleMapper;
import cn.javak.mapper.UserMapper;
import cn.javak.pojo.Role;
import cn.javak.pojo.UserInfo;
import cn.javak.service.UserService;
import cn.javak.utils.BCryptPasswordEncoderUtils;
import cn.javak.utils.JsonUtils;
import cn.javak.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public UserInfo selByName(String userName) {
        //从缓存中获取
        try(Jedis jedis = RedisUtil.getJedis()) {
            if (jedis!=null){
                if (jedis.hexists("user_map", userName)){
                    String userJson = jedis.hget("user_map", userName);
                    UserInfo userInfo = JsonUtils.jsonToPojo(userJson, UserInfo.class);
                    return userInfo;
                }else {
                    UserInfo userInfo = userMapper.selectByName(userName);
                    jedis.hset("user_map", userName, JsonUtils.objectToJson(userInfo));
                    return userInfo;
                }
            }
            return userMapper.selectByName(userName);
        }
    }

    @Override
    public UserInfo selById(int userId) {
        return userMapper.selectById(userId);
    }

    //登录
    @Override
    public boolean login(UserInfo userInfo) {
        UserInfo selUserInfo = userMapper.selByUser(userInfo);
        if (selUserInfo !=null){
            return true;
        }else {
            return false;
        }
    }

    //更新余额
    @Override
    public int updMoney(UserInfo userInfo) {
        return userMapper.update(userInfo);
    }

    //注册
    @Override
    public int insUser(UserInfo userInfo) {
        //加密
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        int res = userMapper.insert(userInfo);
        roleMapper.addRoleByUserId(userMapper.selectByName(userInfo.name).getId());
        return res;
    }

    /**
     * spring-security核心方法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo selUserInfo = null;
        User user = null;
        try(Jedis jedis = RedisUtil.getJedis()) {
            //如果redis中存在该用户数据 就从redis中取出
            if (jedis!=null){
                if (jedis.hexists("user_map", username)){
                    String user_map = jedis.hget("user_map", username);
                    selUserInfo = JsonUtils.jsonToPojo(user_map, UserInfo.class);
                }else {
                    //如果连不上redis或者redis中没有 就从数据库中查询 并放到redis中
                    selUserInfo = userMapper.selectByName(username);
                    jedis.hset("user_map", "userId"+selUserInfo.getId(), JsonUtils.objectToJson(selUserInfo));
                }
            }else {
                selUserInfo = userMapper.selectByName(username);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (selUserInfo !=null){
            user = new User(selUserInfo.getName(), selUserInfo.getPassword(),true, true, true, true,getAuthority(selUserInfo.getRoleList()));
        }
        return user;
    }
    /**
     * 根据用户的角色存储授权信息
     * @param roleList 用户的角色
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roleList){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> selAll() {
        return userMapper.selectAll();
    }
}
