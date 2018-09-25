package com.test.swing.report.test;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class JFreeChart柱状图  {

public static void main(String[] args) {
        // 创建柱状图
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // 装载数据
        dataset.setValue(6, "Profit", "Jane");
        dataset.setValue(3, "Profit2", "Jane");
        dataset.setValue(7, "Profit", "Tom");
        dataset.setValue(6, "Profit2", "Tom");
        dataset.setValue(8, "Profit", "Jill");
        dataset.setValue(9, "Profit2", "Jill");
        dataset.setValue(5, "Profit", "Johh");
        dataset.setValue(8, "Profit2", "Johh");
        dataset.setValue(12, "Profit", "Fred");
        dataset.setValue(11, "Profit2", "Fred");
        // 产生柱状图
        // JFreeChart chart = ChartFactory.createBarChart("标题", "x轴标志", "y轴标志",
        // 设置数据, 设置图形显示方向, 是否显示图形, 是否进行提示, 是否配置报表存放地址);

        // 3D柱状图
        JFreeChart chart = ChartFactory.createBarChart3D("销售统计图", "销售员", "盈利",
                dataset, PlotOrientation.HORIZONTAL, true, true, false);
        // 解决中文乱码
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();

        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        numberAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        numberAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));

        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

        try {
            // 创建图形显示面板
            ChartFrame cf = new ChartFrame("柱状图", chart);

            cf.pack();
            // 设置图片大小
            cf.setSize(800, 600);
            // 设置图形可见
            cf.setVisible(true);
            // 保存图片到指定位置
            // ChartUtilities.saveChartAsJPEG(new File("C:\\bar.png"), chart,
            // 500,
            // 300);
        } catch (Exception e) {
            System.err.println("Problem occurred creating chart.");
        }
    }
}