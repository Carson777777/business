package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于跳转转换页面
 * 请求的url 是/
 * 返回一个String类型是一个逻辑视图,通过视图解析器
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    //请求跳转handler 用于进行商品列表的跳转 请求和页面的跳转一致可以统一处理
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}
