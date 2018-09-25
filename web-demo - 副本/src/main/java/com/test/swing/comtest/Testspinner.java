/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.swing.comtest;

import java.awt.Color;
import java.awt.Frame;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class Testspinner {


    public static void main(String[] args) {
        Frame f=new Frame("这是窗口的标题");  //它是java.awt包的
        f.setSize(800,600);
        f.setLocation(10,20);  //左上角座标

        /*JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeDitor = new JSpinner.DateEditor(timeSpinner,"HH:mm:ss");
        timeSpinner.setEditor(timeDitor);
        timeSpinner.setValue(new Date());
        JPanel panel = new JPanel();
        panel.add(timeDitor);
        f.add(timeSpinner);
        f.add(panel);*/

        JSpinner timeSpinner;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateModel.setCalendarField(Calendar.SECOND);
        timeSpinner = new JSpinner(dateModel);
        timeSpinner.setValue( new Date() );

        JPanel newPanel = new JPanel();
        newPanel.add(new JLabel( "Time:" ) );
        newPanel.add(timeSpinner);
        newPanel.setBackground(Color.WHITE);
        f.add(newPanel);
        f.setVisible(true);
    }
}
