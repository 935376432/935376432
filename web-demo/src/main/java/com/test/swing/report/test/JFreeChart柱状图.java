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

public class JFreeChart��״ͼ  {

public static void main(String[] args) {
        // ������״ͼ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // װ������
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
        // ������״ͼ
        // JFreeChart chart = ChartFactory.createBarChart("����", "x���־", "y���־",
        // ��������, ����ͼ����ʾ����, �Ƿ���ʾͼ��, �Ƿ������ʾ, �Ƿ����ñ����ŵ�ַ);

        // 3D��״ͼ
        JFreeChart chart = ChartFactory.createBarChart3D("����ͳ��ͼ", "����Ա", "ӯ��",
                dataset, PlotOrientation.HORIZONTAL, true, true, false);
        // �����������
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();

        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("����", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
        numberAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        numberAxis.setLabelFont(new Font("����", Font.PLAIN, 12));

        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));

        try {
            // ����ͼ����ʾ���
            ChartFrame cf = new ChartFrame("��״ͼ", chart);

            cf.pack();
            // ����ͼƬ��С
            cf.setSize(800, 600);
            // ����ͼ�οɼ�
            cf.setVisible(true);
            // ����ͼƬ��ָ��λ��
            // ChartUtilities.saveChartAsJPEG(new File("C:\\bar.png"), chart,
            // 500,
            // 300);
        } catch (Exception e) {
            System.err.println("Problem occurred creating chart.");
        }
    }
}