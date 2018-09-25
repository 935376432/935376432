package com.test.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
public class MenuFrame extends JFrame{
    public static final int default_width=300;
    public static final int default_height=200;
    public MenuFrame(){
    setTitle("弹出菜单测试");
    setSize(default_width,default_height);
    final JPopupMenu popup;
    popup=new JPopupMenu();
    JMenuItem item=new JMenuItem("请单击");
    item.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e)
        {JOptionPane.showMessageDialog(MenuFrame.this,"大家好","提示对话框",1);
        }
        });
    popup.add(item);
    getContentPane().addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent event)
        {
            if(event.isPopupTrigger()) {
                popup.show(event.getComponent(),event.getX(),event.getY());
            }
        }
        @Override
        public void mouseReleased(MouseEvent event)
        {
            if(event.isPopupTrigger()) {
                popup.show(event.getComponent(),event.getX(),event.getY());
            }
        }
    });
    }



    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
     MenuFrame frame=new MenuFrame();
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.show();
    }

}