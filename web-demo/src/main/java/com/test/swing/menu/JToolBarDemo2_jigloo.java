package com.test.swing.menu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
//工具栏的使用案例
public class JToolBarDemo2_jigloo extends javax.swing.JFrame {

 private JToolBar myJToolBar;
 private JButton jB_file;
 private JButton jB_edit;
 private JButton jB_tools;
 private JButton jB_help;

 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
   @Override
public void run() {
    JToolBarDemo2_jigloo inst = new JToolBarDemo2_jigloo();
    inst.setLocationRelativeTo(null);
    inst.setVisible(true);
   }
  });
 }

 public JToolBarDemo2_jigloo() {
  super();
  initGUI();
 }

 private void initGUI() {
  try {
   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   getContentPane().setLayout(null);
   getContentPane().setBackground(new java.awt.Color(255, 128, 255));
   {
    myJToolBar = new JToolBar();
    getContentPane().add(myJToolBar);
    myJToolBar.setBounds(29, 12, 320, 38);
    myJToolBar.setBackground(new java.awt.Color(255, 255, 255));
    {
     jB_file = new JButton();
     myJToolBar.add(jB_file);
     jB_file.setText("\u6587\u4ef6");
     jB_file.setPreferredSize(new java.awt.Dimension(80, 34));
     jB_file.setIcon(new ImageIcon("images//AddNode.jpg"));
     jB_file.setFont(new java.awt.Font("楷体", 0, 14));
     jB_file.setToolTipText("点此每次新增一个结点");
     jB_file.setBackground(new java.awt.Color(255, 255, 128));
    }
    {
     jB_edit = new JButton();
     myJToolBar.add(jB_edit);
     jB_edit.setText("\u7f16\u8f91");
     jB_edit.setToolTipText("点此每次新增一个结点");
     jB_edit.setIcon(new ImageIcon("images//AddSide.jpg"));
     jB_edit.setFont(new java.awt.Font("楷体", 0, 14));
     jB_edit.setBackground(new java.awt.Color(255, 255, 128));
     jB_edit.setToolTipText("首次点击结束结点输入,然后增加一条边");
     jB_edit.setPreferredSize(new java.awt.Dimension(78, 34));
    }
    {
     jB_tools = new JButton();
     myJToolBar.add(jB_tools);
     jB_tools.setText("\u89c6\u56fe");
     jB_tools.setIcon(new ImageIcon("images//DrawFigure.jpg"));
     jB_tools.setFont(new java.awt.Font("楷体", 0, 14));
     jB_tools.setBackground(new java.awt.Color(255, 255, 128));
     jB_tools.setToolTipText("首次点击结束边的输入,然后绘制图形");
     jB_tools.setPreferredSize(new java.awt.Dimension(94, 34));
    }
    {
     jB_help = new JButton();
     myJToolBar.add(jB_help);
     jB_help.setText("\u5e2e\u52a9");
     jB_help.setIcon(new ImageIcon("images//ShortestPath.jpg"));
     jB_help.setFont(new java.awt.Font("楷体", 0, 14));
     jB_help.setBackground(new java.awt.Color(255, 255, 128));
     jB_help.setToolTipText("首次点击变换按钮,选择起始点求其最短路径");
     jB_help.setPreferredSize(new java.awt.Dimension(95, 34));
    }
   }
   pack();
   setSize(400, 300);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}