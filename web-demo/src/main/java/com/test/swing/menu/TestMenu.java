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
        MainFrame mf = new MainFrame("���Բ˵�");
    }
}

class MainFrame implements ActionListener {
    Container p;
    JMenuBar mb;
    final JMenu[] m = { new JMenu("�ļ�"), new JMenu("�༭"), new JMenu("����") };
    final JMenuItem[][] mi = { { new JMenuItem("�½�"), new JMenuItem("��"), new JMenuItem("����") },
        { new JMenuItem("ѡɫ"), new JMenuItem("ճ��") }, { new JMenuItem("����") } };

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
        if (e.getActionCommand() == "�½�") {
        }
        if (e.getActionCommand() == "��") {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showOpenDialog(null);
            if (returnVal == chooseFile.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                JOptionPane.showConfirmDialog(null, "��ѡ����ļ����ǣ�" + chooseFile.getName(f), "ȷ��",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand() == "����") {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showSaveDialog(null);
            if (returnVal == chooseFile.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                JOptionPane.showConfirmDialog(null, chooseFile.getName(f));
            }
        }
        if (e.getActionCommand() == "ѡɫ") {
            Color tmpColor = JColorChooser.showDialog(null, "��ɫ��", Color.red);
        }
        if (e.getActionCommand() == "ճ��") {
        }
        if (e.getActionCommand() == "����") {
            JOptionPane.showMessageDialog(null, "http://student.csdn.net/space.php?do=home");
        }
    }
}