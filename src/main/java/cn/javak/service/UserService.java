package cn.javak.service;

import cn.javak.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
    //通过用户名查询
    UserInfo selByName(String userName);

    //通过id查询
    UserInfo selById(int userId);

    //登录
    boolean login(UserInfo userInfo);

    //扣钱
    int updMoney(UserInfo userInfo);

    //注册用户
    int insUser(UserInfo userInfo);

    List<UserInfo> selAll();
}
