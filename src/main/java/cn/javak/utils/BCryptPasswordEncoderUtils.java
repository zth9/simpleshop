package cn.javak.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: theTian
 * @date: 2020/3/19 14:00
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
            return bCryptPasswordEncoder.encode(password);
    }
}
