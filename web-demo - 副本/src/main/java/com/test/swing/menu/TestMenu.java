package com.test.swing.menu;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TestMenu {
    public static void main(String[] args) {
        MainFrame mf = new MainFrame("测试菜单");
    }
}

class MainFrame implements ActionListener {
    Container p;
    JMenuBar mb;
    final JMenu[] m = { new JMenu("文件"), new JMenu("编辑"), new JMenu("帮助") };
    final JMenuItem[][] mi = { { new JMenuItem("新建"), new JMenuItem("打开"), new JMenuItem("保存") },
        { new JMenuItem("选色"), new JMenuItem("粘贴") }, { new JMenuItem("关于") } };

    public MainFrame(String title) {
        JFrame f = new JFrame(title);
        p = f.getContentPane();
        mb = new JMenuBar();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < mi[i].length; j++) {
                m[i].add(mi[i][j]);
                m[i].addActionListener(this);
            }
            mb.add(m[i]);
        }
        f.setJMenuBar(mb);
        f.setBounds(200, 200, 500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "新建") {
        }
        if (e.getActionCommand() == "打开") {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showOpenDialog(null);
            if (returnVal == chooseFile.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                JOptionPane.showConfirmDialog(null, "你选择的文件名是：" + chooseFile.getName(f), "确认",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand() == "保存") {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showSaveDialog(null);
            if (returnVal == chooseFile.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                JOptionPane.showConfirmDialog(null, chooseFile.getName(f));
            }
        }
        if (e.getActionCommand() == "选色") {
            Color tmpColor = JColorChooser.showDialog(null, "调色板", Color.red);
        }
        if (e.getActionCommand() == "粘贴") {
        }
        if (e.getActionCommand() == "关于") {
            JOptionPane.showMessageDialog(null, "http://student.csdn.net/space.php?do=home");
        }
    }
}