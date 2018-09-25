/*
 *jiji java
 */
package com.test.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class TestMenuDemo extends JFrame implements ActionListener,PropertyChangeListener{

    /**  系列化id   */
    private static final long serialVersionUID = 1L;

    String[][] menuNameMnemonics = { { "概览", "s" }, { "上传事件", "f" }, { "采集日志", "e" } };
    JMenuBar menuBar;
    JPanel panel;
    JMenu menu1;
    JMenu menu2;
    JMenu menu3;
    JMenu menu4;

    public TestMenuDemo() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu1 = new JMenu("概览");
        menu2 = new JMenu("上传事件");
        menu3 = new JMenu("采集日志");
        menu4 = new JMenu("资源管理");
        panel = new JPanel();

        menu1.addMenuListener( new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                System.out.println("概览menuSelected");
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }

        });

        menu2.addMenuListener( new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                System.out.println("上传列表menuSelected");
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }

        });



        menu3.addMenuListener( new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                System.out.println("采集日志menuSelected");
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }

        });
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

    }

    public static void main(String[] args) {
        // 创建窗体
        TestMenuDemo frame = new TestMenuDemo();
        //指定标题
        frame.setTitle("JMenuDemo");
        //指定icno
        frame.setIconImage(new ImageIcon("resources\\icon\\Eclipse.png").getImage());
        // 关闭窗体后退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 自动适配所有控件大小
        frame.setSize(800, 600);
        //frame.pack();
        // 设置窗体位置在屏幕中央
        frame.setLocationRelativeTo(null);
        // 显示窗体
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("actionPerformed");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        System.out.println("propertyChange");
    }

}
