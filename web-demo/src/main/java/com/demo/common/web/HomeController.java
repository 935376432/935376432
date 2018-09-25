package com.demo.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页 web管理控制器
 */

@Controller
public class HomeController {

    /**
     * 跳转首页页面
     * @return 首页名称
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}