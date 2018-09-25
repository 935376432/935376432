package com.test.swing.report.test.panel;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Calculator extends JFrame
{
    public Calculator()
    {
     // 创建一个柱状图
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // 装载数据
        dataset.setValue(6, "Profit", "Jane");
        dataset.setValue(3, "Profit2", "Jane");
        dataset.setValue(7, "Profit", "Tom");
        dataset.setValue(6, "Profit2", "Tom");
        dataset.setValue(8, "Profit", "Jill");
        dataset.setValue(9, "Profit2", "Jill");
        dataset.setValue(5, "Profit", "John");
        dataset.setValue(8, "Profit2", "John");
        dataset.setValue(12, "Profit", "Fred");
        dataset.setValue(11, "Profit2", "Fred");

        // 产生柱状图
        // JFreeChart chart =
        // ChartFactory.createXYLineChart("标题"，"x轴标志","y轴标志","设置数据","设置图形显示方向",是否显示图形,是否进行提示,是否配置报表存放地址);
        // 图形显示方向：
        // (1)HORIZONTAL:横向显示图形
        // (2)VERTICAL:纵向显示图形
        // 3D柱状图
        JFreeChart chart = ChartFactory.createBarChart3D("销售统计图", "Salesman", "Profit", dataset,
            PlotOrientation.VERTICAL, true, true, false);

        // 设置总的背景颜色
        chart.setBackgroundPaint(ChartColor.yellow);
        // 设置标题颜色
        chart.getTitle().setPaint(ChartColor.blue);
        // 获得图表对象
        CategoryPlot p = chart.getCategoryPlot();
        // 设置图的背景颜色
        p.setBackgroundPaint(ChartColor.black);
        // 设置表格线颜色
        p.setRangeGridlinePaint(ChartColor.red);
        try {
            // // 创建图形显示面板
            // ChartFrame cf = new ChartFrame("柱状图",chart);
            // cf.pack();
            // // 设置图片大小
            // cf.setSize(500,300);
            // // 设置图形可见
            // cf.setVisible(true);

            // 保存图片到指定文件夹
            //ChartUtilities.saveChartAsJPEG(new File("C:\\BarChart.jpg"), chart, 500, 300);
            Frame f=new Frame("这是窗口的标题");  //它是java.awt包的
            f.setSize(800,600);
            f.setLocation(10,20);  //左上角座标
            f.setVisible(true);
            JPanel panel = new JPanel();
            ChartPanel frame1 = new ChartPanel(chart, true);
            panel.add(frame1);
            f.add(panel);
        } catch (Exception e) {
            System.err.println("Problem occurred creating chart.");
        }



        //-------------
        //super("无标题 - 记事本");
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu formatMenu = new JMenu("格式");
        JMenu checkMenu = new JMenu("查看");
        JMenu helpMenu = new JMenu("帮助");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(checkMenu);
        menuBar.add(helpMenu);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        ChartPanel frame1 = new ChartPanel(chart, true);
        this.getContentPane().add(frame1);

        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //用来设置窗口随屏幕大小改变
        sizeWindowOnScreen(this, 0.6, 0.6);
        this.setVisible(true);
    }

    /**
    *
    * @param calculator
    * @param widthRate 宽度比例
    * @param heightRate 高度比例
    */
    private void sizeWindowOnScreen(Calculator calculator, double widthRate,
            double heightRate)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        calculator.setSize(new Dimension((int) (screenSize.width * widthRate),
                (int) (screenSize.height * heightRate)));
    }

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();
    }
}