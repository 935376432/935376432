package com.test.swing.report.test.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LatoutTest {

    static ChartPanel frame1;

    public static void main(String[] args) {
        barChart();
        JFrame frame = new JFrame("xxx");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400, 200);

        frame.setLayout(new GridLayout());

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(500, 500));

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel left_1 = new JPanel();
        left_1.setPreferredSize(new Dimension(250, 250));
        left_1.setBackground(Color.RED);
        left_1.add(frame1);

        JPanel left_2 = new JPanel();
        left_2.setPreferredSize(new Dimension(250, 250));
        left_2.setBackground(Color.GREEN);

        //----------------------

        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(500, 500));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        left.add(left_1, BorderLayout.NORTH);
        left.add(left_2, BorderLayout.SOUTH);

        JPanel right_1 = new JPanel();
        right_1.setBackground(Color.WHITE);
        right_1.setPreferredSize(new Dimension(250, 250));

        JPanel right_2 = new JPanel();
        right_2.setBackground(Color.BLUE);
        right_2.setPreferredSize(new Dimension(250,250));

        right.add(right_1, BorderLayout.NORTH);
        right.add(right_2, BorderLayout.SOUTH);

        frame.add(left, BorderLayout.WEST);
        frame.add(right, BorderLayout.EAST);

        frame.setVisible(true);
        frame.pack();
    }

    public static void barChart() {
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("水果", // 图表标题
            "水果种类", // 目录轴的显示标签
            "数量", // 数值轴的显示标签
            dataset, // 数据集
            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
            true, // 是否显示图例(对于简单的柱状图必须是false)
            false, // 是否生成工具
            false // 是否生成URL链接
        );


        CategoryPlot plot = chart.getCategoryPlot();

     // 设置图的背景颜色
        chart.setBackgroundPaint(ChartColor.WHITE);
     // 设置图的背景颜色
        chart.setBackgroundPaint(ChartColor.WHITE);


        // 使用自定义的渲染器
        /*IntervalBarRenderer renderer = new IntervalBarRenderer();
        // 设置柱子宽度
        renderer.setMaximumBarWidth(0.2);
        // 设置柱子高度
        renderer.setMinimumBarLength(0.2);
        // 设置柱子边框颜色
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 设置柱子边框可见
        renderer.setDrawBarOutline(true);
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.5);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        // 显示每个柱的数值，并修改该数值的字体属性
        renderer.setIncludeBaseInRange(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);*/

        BarRenderer barrenderer = new BarRenderer();
        barrenderer.setMaximumBarWidth(0.1);
        barrenderer.setMinimumBarLength(0.1);
        barrenderer.setIncludeBaseInRange(true);
        barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barrenderer.setBaseItemLabelsVisible(true);
        //但是却没有效果，后来找来找去，原因竟然是没写这句话：
        plot.setRenderer(barrenderer);


        //plot.setRenderer(renderer);

        barrenderer.setSeriesPaint(0, Color.decode("#f90c0c"));
        barrenderer.setSeriesPaint(1, Color.decode("#0cf91e"));
        barrenderer.setSeriesPaint(2, Color.decode("#f5f90c"));



        /*BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setItemLabelFont(new Font("黑体", Font.PLAIN, 12));
        renderer.setItemLabelsVisible(true);
        plot.setRenderer(renderer);*/
        /*BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); // 设定柱子上面的颜色
        customBarRenderer.setSeriesPaint(0, Color.decode("#f90c0c"));

       customBarRenderer.setSeriesPaint(1, Color.decode("#0cf91e"));

       customBarRenderer.setSeriesPaint(2, Color.decode("#f5f90c"));*/




        chart.setBackgroundPaint(Color.white);
        // 从这里开始
        //CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

        // 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame
        frame1.setMouseZoomable(true);
    }

    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "北京", "苹果");
        dataset.addValue(100, "上海", "苹果");
        dataset.addValue(100, "广州", "苹果");
        dataset.addValue(200, "北京", "梨子");
        dataset.addValue(200, "上海", "梨子");
        dataset.addValue(200, "广州", "梨子");
        dataset.addValue(300, "北京", "葡萄");
        dataset.addValue(300, "上海", "葡萄");
        dataset.addValue(300, "广州", "葡萄");
        dataset.addValue(400, "北京", "香蕉");
        dataset.addValue(400, "上海", "香蕉");
        dataset.addValue(400, "广州", "香蕉");
        dataset.addValue(500, "北京", "荔枝");
        dataset.addValue(500, "上海", "荔枝");
        dataset.addValue(500, "广州", "荔枝");
        return dataset;
    }

    public ChartPanel getChartPanel() {
        return frame1;

    }
}