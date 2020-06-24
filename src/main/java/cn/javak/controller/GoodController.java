package cn.javak.controller;

import cn.javak.pojo.Good;
import cn.javak.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodController {
    @Autowired
    private GoodService goodServiceImpl;
    @Autowired
    private HttpServletRequest request;

    /**
     * 查看商品详情
     * @param id
     * @param pic
     * @return
     */
    @RequestMapping("detail")
    public String detail(@RequestParam(required = true) int id, String pic){
        Good good = goodServiceImpl.selById(id);
        if (good==null){
            return "redirect:index";
        }
        request.setAttribute("good", good);
        request.setAttribute("pic", pic);
        return "detail";
    }
}
