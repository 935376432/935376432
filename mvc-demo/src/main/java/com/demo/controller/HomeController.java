package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转到首页
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

}