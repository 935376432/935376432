package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ��ת����ҳ
 */

@Controller
public class HomeController {

    /**
     * ��ת��ҳҳ��
     * @return ��ҳ����
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}