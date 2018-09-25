package com.test.swing.report.test;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.VerticalAlignment;

public class ChartUtil {
    /**
     * 创建数据集合
     * @return dataSet
     */
    public static CategoryDataset createDataSet() {
        // 实例化DefaultCategoryDataset对象
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        // 添加第一季度数据
        dataSet.addValue(6000, "第一季度", "J2SE类");
        dataSet.addValue(3000, "第一季度", "J2ME类");
        dataSet.addValue(12000, "第一季度", "J2EE类");
        // 添加第二季度数据
        dataSet.addValue(8000, "第二季度", "J2SE类");
        dataSet.addValue(4000, "第二季度", "J2ME类");
        dataSet.addValue(6000, "第二季度", "J2EE类");
        // 添加第三季度数据
        dataSet.addValue(5000, "第三季度", "J2SE类");
        dataSet.addValue(4000, "第三季度", "J2ME类");
        dataSet.addValue(8000, "第三季度", "J2EE类");
        // 添加第四季度数据
        dataSet.addValue(8000, "第四季度", "J2SE类");
        dataSet.addValue(2000, "第四季度", "J2ME类");
        dataSet.addValue(9000, "第四季度", "J2EE类");
        return dataSet;
    }
    /**
     * 创建JFreeChart对象
     * @return chart
     */
    public static JFreeChart createChart() {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //创建主题样式
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); //设置标题字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); //设置图例的字体
        standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); //设置轴向的字体
        ChartFactory.setChartTheme(standardChartTheme);//设置主题样式
        // 通过ChartFactory创建JFreeChart
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Java图书销量统计", //图表标题
                "Java图书", //横轴标题
                "销量（本）",//纵轴标题
                createDataSet(),//数据集合
                PlotOrientation.VERTICAL, //图表方向
                true,//是否显示图例标识
                true,//是否显示tooltips
                false);//是否支持超链接
        // 背景图片
        /*Image image = null;
        try {
            // 创建背景图片
            image = ImageIO.read(ChartUtil.class.getResource("test.jpg"));
        } catch (IOException e){
            e.printStackTrace();
        }*/
        chart.getTitle().setFont(new Font("隶书", Font.BOLD, 25)); // 设置标题字体
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); // 设置图例类别字体
        chart.setBorderVisible(true); // 设置显示边框
        TextTitle subTitle = new TextTitle("2012年Java类图书全国销量统计（J2SE, J2ME, J2EE）");//实例化TextTitle对象
        subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); //设置居中显示
        chart.addSubtitle(subTitle);//添加子标题
        CategoryPlot plot = chart.getCategoryPlot(); // 获取绘图区对象
        plot.setForegroundAlpha(0.8F);//设置绘图区前景色透明度
        plot.setBackgroundAlpha(0.5F);//设置绘图区背景色透明度
        //plot.setBackgroundImage(image);//设置绘图区背景图片
        CategoryAxis categoryAxis = plot.getDomainAxis();//获取坐标轴对象
        categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));//设置坐标轴标题字体
        categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));//设置坐标轴尺值字体
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// 设置坐标轴标题旋转角度
        ValueAxis valueAxis = plot.getRangeAxis();// 设置数据轴对象
        valueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        BarRenderer3D renderer = new BarRenderer3D();
        renderer.setItemMargin(0.32); // 设置柱间的间距
        plot.setRenderer(renderer);// 设置图片渲染对象
        return chart;
    }
    // 本地测试
    public static void main(String[] args) {
        ChartFrame cf = new ChartFrame("Test", createChart());
        cf.pack();
        cf.setVisible(true);
    }
}