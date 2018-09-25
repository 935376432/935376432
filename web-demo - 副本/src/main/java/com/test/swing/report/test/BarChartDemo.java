package com.test.swing.report.test;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        CategoryDataset dataset = getDataSet2();
        JFreeChart chart = ChartFactory.createBarChart3D("水果产量图", // 图表标题
            null, // 目录轴的显示标签
            null, // 数值轴的显示标签
            dataset, // 数据集
            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
            true, // 是否显示图例(对于简单的柱状图必须是false)
            true, // 是否生成工具
            true // 是否生成URL链接
        );
        CategoryPlot plot = chart.getCategoryPlot();

        CategoryAxis domainAxis = plot.getDomainAxis();
        // domainAxis.setVerticalCategoryLabels(false);
        plot.setDomainAxis(domainAxis);

        ValueAxis rangeAxis = plot.getRangeAxis();
        // 设置最高的一个 Item 与图片顶端的距离
        rangeAxis.setUpperMargin(0.15);
        // 设置最低的一个 Item 与图片底端的距离
        rangeAxis.setLowerMargin(0.15);
        plot.setRangeAxis(rangeAxis);

        BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 设置 Wall 的颜色
        renderer.setWallPaint(Color.gray);
        // 设置每种水果代表的柱的颜色
        renderer.setSeriesPaint(0, new Color(0, 0, 255));
        renderer.setSeriesPaint(1, new Color(0, 100, 255));
        renderer.setSeriesPaint(2, Color.GREEN);
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.1);
        // 显示每个柱的数值，并修改该数值的字体属性
        // renderer.setLabelGenerator(new StandardCategoryLabelGenerator());
        renderer.setItemLabelsVisible(true);
        plot.setRenderer(renderer);

        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("D:\\fruit.jpg");
            ChartUtilities.writeChartAsJPEG(fos_jpg, 100, chart, 400, 300, null);
        } finally {
            try {
                fos_jpg.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取一个演示用的组合数据集对象
     *
     * @return
     */
    private static CategoryDataset getDataSet2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "北京", "苹果");
        dataset.addValue(100, "上海", "苹果");
        dataset.addValue(85, "广州", "苹果");
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

}
/*
 * 解决方法： 我把renderer.setItemLabelGenerator(new
 * StandardCategoryItemLabelGenerator()); 改成 renderer.setLabelGenerator(new
 * StandardCategoryLabelGenerator()); 后，在其中一根柱子上显示了数字 望哪位能指点一下？ 在生成
 * 函数后(ChartFactory.createBarChart3D)加入下面的代码即可。
 */

/*
 * CategoryPlot plot = chart.getCategoryPlot(); BarRenderer3D renderer = new
 * BarRenderer3D(); //显示每个柱的数值，并修改该数值的字体属性 renderer.setBaseLabelGenerator(new
 * StandardCategoryItemLabelGenerator()); renderer.setItemLabelFont(new
 * Font("黑体", Font.PLAIN, 12)); renderer.setItemLabelsVisible(true);
 * plot.setRenderer(renderer);
 */