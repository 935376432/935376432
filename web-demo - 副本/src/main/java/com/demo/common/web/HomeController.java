package com.demo.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ��ҳ web���������
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


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}