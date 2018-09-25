/*
 *jiji java
 */
package com.test.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

public class TestMenuOne implements ActionListener{


    public JMenu menu1;
    JMenu menu2;
    JMenu menu3;
    JMenu menu4;
    public  List<JMenu> menuList = new ArrayList<>();

    public TestMenuOne(){
        menu1 = new JMenu("概览");
        menu1.addActionListener(this);
        menu2 = new JMenu("上传事件");
        menu3 = new JMenu("采集日志");
        menu4 = new JMenu("资源管理");
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("actionPerformed");
        System.out.println(e.getActionCommand());
    }

}
