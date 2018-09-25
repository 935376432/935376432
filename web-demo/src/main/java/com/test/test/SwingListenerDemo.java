package com.test.test;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class SwingListenerDemo {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public SwingListenerDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingListenerDemo  swingListenerDemo = new SwingListenerDemo();
      swingListenerDemo.showAdjustmentListenerDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Java SWING Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
        public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }
      });
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);
   }

   private void showAdjustmentListenerDemo(){
      headerLabel.setText("Listener in action: AdjustmentListener");

      JPanel panel = new JPanel();
      JScrollBar scrollbar = new JScrollBar();
      scrollbar.addAdjustmentListener(new CustomAdjustmentListener());

      panel.add(scrollbar);
      controlPanel.add(panel);
      mainFrame.setVisible(true);
   }
      class CustomAdjustmentListener implements AdjustmentListener {
         @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            statusLabel.setText("Adjustment value: "
               +Integer.toString(e.getValue()));
         }
      }
}