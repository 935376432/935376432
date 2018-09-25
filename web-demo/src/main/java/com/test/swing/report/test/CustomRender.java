/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.swing.report.test;

import java.awt.Color;
import java.awt.Paint;

public class CustomRender extends  org.jfree.chart.renderer.category.IntervalBarRenderer {

    private Paint[] colors;
 // 初始化柱子颜色
 private String[] colorValues = { "#FF0000", "#0070C0","#00AF4E","#7030A0" };

 public CustomRender() {
     colors = new Paint[colorValues.length];
     for (int i = 0; i < colorValues.length; i++) {
         colors[i] = Color.decode(colorValues[i]);
     }
 }
 // 每根柱子以初始化的颜色不断轮循
 @Override
public Paint getItemPaint(int i, int j) {
     return colors[j % colors.length];
 }

}
