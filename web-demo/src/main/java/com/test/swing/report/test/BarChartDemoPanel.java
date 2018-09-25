package com.test.swing.report.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartDemoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public BarChartDemoPanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 60));
        topPanel.setBorder(BorderFactory.createTitledBorder("��״ͼ��"));

        CategoryDataset dataset1 = getDataSet1();
        ChartPanel charPanel1 = getChartPanel(dataset1);
        charPanel1.setPreferredSize(new Dimension(200, 100));//����ͼ�Ĵ�С
        charPanel1.setMouseZoomable(true);
        topPanel.add(charPanel1);

        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BarChartDemoPanel());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    /**
     * ��ȡһ����ʾ�õļ����ݼ�����
     *
     * @return
     */
    private CategoryDataset getDataSet1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "����", "ƻ��");
        // dataset.addValue(200, "����", "����");
        // dataset.addValue(300, "����", "����");
        // dataset.addValue(400, "����", "�㽶");
        // dataset.addValue(500, "����", "��֦");
        return dataset;
    }

    private JFreeChart getBarChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart3D("ˮ������ͼ", // ͼ�����
                "ˮ��", // Ŀ¼�����ʾ��ǩ
                "����", // ��ֵ�����ʾ��ǩ
                dataset, // ���ݼ�
                PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                true, // �Ƿ����ɹ���
                false // �Ƿ�����URL����
                );

        chart.setBackgroundPaint(SystemColor.controlHighlight);

        // ����ͼ���������
        chart.getTitle().setFont(new Font("����", Font.PLAIN, 20));

        CategoryPlot plot = chart.getCategoryPlot();
        // ���ú����������
        plot.getDomainAxis().setLabelFont(new Font("����", Font.PLAIN, 14));
        plot.getDomainAxis().setCategoryLabelPositions(
                CategoryLabelPositions.UP_45);

        // ���ú����ǵ�����
        plot.getDomainAxis().setTickLabelFont(new Font("����", Font.PLAIN, 12));
        // ���ú�����������ɫ
        plot.getDomainAxis().setTickLabelPaint(Color.RED);

        // ���������������
        plot.getRangeAxis().setLabelFont(new Font("����", Font.PLAIN, 14));

        // ��������������
        NumberAxis3D numberAxis3D = (NumberAxis3D) plot.getRangeAxis();
        numberAxis3D
                .setStandardTickUnits(NumberAxis3D.createIntegerTickUnits());
        numberAxis3D.setTickLabelPaint(Color.RED);

        // ����ͼ������
        BarRenderer3D renderer3D = (BarRenderer3D) plot.getRenderer();
        renderer3D.setBaseLegendTextFont(new Font("����", Font.PLAIN, 14));
        renderer3D.setSeriesPaint(0, Color.ORANGE);

//      int k = dataset.getColumnCount();
//      if (k == 1) {
//          plot.getDomainAxis().setLowerMargin(0.26);
//          plot.getDomainAxis().setUpperMargin(0.66);
//      } else if (k < 6) {
//          double margin = (1.0 - k * 0.08) / 3;
//          plot.getDomainAxis().setLowerMargin(margin);
//          plot.getDomainAxis().setUpperMargin(margin);
//          // domainAxis.setCategoryMargin(0.1);
//          ((BarRenderer3D) plot.getRenderer()).setItemMargin(margin);
//      } else {
//          ((BarRenderer3D) plot.getRenderer()).setItemMargin(0.1);
//      }

        return chart;
    }

    private ChartPanel getChartPanel(CategoryDataset dataset) {

        JFreeChart chart = getBarChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPopupMenu(null);// ����ʾ�����˵�

        return chartPanel;
    }
}