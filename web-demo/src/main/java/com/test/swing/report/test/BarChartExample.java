package com.test.swing.report.test;
import java.awt.Frame;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartExample {
    public static void main(String[] args) {
        // ����һ����״ͼ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // װ������
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

        // ������״ͼ
        // JFreeChart chart =
        // ChartFactory.createXYLineChart("����"��"x���־","y���־","��������","����ͼ����ʾ����",�Ƿ���ʾͼ��,�Ƿ������ʾ,�Ƿ����ñ����ŵ�ַ);
        // ͼ����ʾ����
        // (1)HORIZONTAL:������ʾͼ��
        // (2)VERTICAL:������ʾͼ��
        // 3D��״ͼ
        JFreeChart chart = ChartFactory.createBarChart3D("����ͳ��ͼ", "Salesman", "Profit", dataset,
            PlotOrientation.VERTICAL, true, true, false);

        // �����ܵı�����ɫ
        chart.setBackgroundPaint(ChartColor.yellow);
        // ���ñ�����ɫ
        chart.getTitle().setPaint(ChartColor.blue);
        // ���ͼ�����
        CategoryPlot p = chart.getCategoryPlot();
        // ����ͼ�ı�����ɫ
        p.setBackgroundPaint(ChartColor.black);
        // ���ñ������ɫ
        p.setRangeGridlinePaint(ChartColor.red);
        try {
            // // ����ͼ����ʾ���
            // ChartFrame cf = new ChartFrame("��״ͼ",chart);
            // cf.pack();
            // // ����ͼƬ��С
            // cf.setSize(500,300);
            // // ����ͼ�οɼ�
            // cf.setVisible(true);

            // ����ͼƬ��ָ���ļ���
            //ChartUtilities.saveChartAsJPEG(new File("C:\\BarChart.jpg"), chart, 500, 300);
            Frame f=new Frame("���Ǵ��ڵı���");  //����java.awt����
            f.setSize(800,600);
            f.setLocation(10,20);  //���Ͻ�����
            f.setVisible(true);
            JPanel panel = new JPanel();
            ChartPanel frame1 = new ChartPanel(chart, true);
            panel.add(frame1);
            f.add(panel);
        } catch (Exception e) {
            System.err.println("Problem occurred creating chart.");
        }
    }
}