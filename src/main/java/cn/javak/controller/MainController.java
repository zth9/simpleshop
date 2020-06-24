package cn.javak.controller;

import cn.javak.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: theTian
 * @date: 2020/3/10 10:59
 */
@Controller
public class MainController {
    @ResponseBody
    @RequestMapping(value = "/badRequest", produces = {"text/plain;charset=utf-8", "text/html;charset=utf-8"})
    public String badRequest() {
        return "<h1>迷路了...</h1><a href='http://javak.cn/xmshop/index'>点击回到主页</a>";
    }

    @ResponseBody
    @RequestMapping(value = "/errorRequest", produces = {"text/plain;charset=utf-8", "text/html;charset=utf-8"})
    public String errorRequest() {
        return "<h1>服务器繁忙</h1><a href='http://javak.cn/xmshop/index'>点击回到主页</a>";
    }
}
