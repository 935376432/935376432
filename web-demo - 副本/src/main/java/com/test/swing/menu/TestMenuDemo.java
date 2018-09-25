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

    /**  ϵ�л�id   */
    private static final long serialVersionUID = 1L;

    String[][] menuNameMnemonics = { { "����", "s" }, { "�ϴ��¼�", "f" }, { "�ɼ���־", "e" } };
    JMenuBar menuBar;
    JPanel panel;
    JMenu menu1;
    JMenu menu2;
    JMenu menu3;
    JMenu menu4;

    public TestMenuDemo() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu1 = new JMenu("����");
        menu2 = new JMenu("�ϴ��¼�");
        menu3 = new JMenu("�ɼ���־");
        menu4 = new JMenu("��Դ����");
        panel = new JPanel();

        menu1.addMenuListener( new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                System.out.println("����menuSelected");
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
                System.out.println("�ϴ��б�menuSelected");
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
                System.out.println("�ɼ���־menuSelected");
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
        // ��������
        TestMenuDemo frame = new TestMenuDemo();
        //ָ������
        frame.setTitle("JMenuDemo");
        //ָ��icno
        frame.setIconImage(new ImageIcon("resources\\icon\\Eclipse.png").getImage());
        // �رմ�����˳�����
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �Զ��������пؼ���С
        frame.setSize(800, 600);
        //frame.pack();
        // ���ô���λ������Ļ����
        frame.setLocationRelativeTo(null);
        // ��ʾ����
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
