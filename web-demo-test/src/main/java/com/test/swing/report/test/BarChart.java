/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.swing.report.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

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
import org.jfree.chart.renderer.category.IntervalBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
    ChartPanel frame1;

    public BarChart() {
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("ˮ��", // ͼ�����
            "", // Ŀ¼�����ʾ��ǩ
            "����", // ��ֵ�����ʾ��ǩ
            dataset, // ���ݼ�
            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
            true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
            false, // �Ƿ����ɹ���
            false // �Ƿ�����URL����
        );


        CategoryPlot plot = chart.getCategoryPlot();

        // ����ͼ�ı�����ɫ
        chart.setBackgroundPaint(ChartColor.WHITE);
        // ����ͼ�ı�����ɫ
        chart.setBackgroundPaint(ChartColor.WHITE);

        plot.setBackgroundPaint(ChartColor.WHITE);
        // ʹ���Զ������Ⱦ��
        IntervalBarRenderer renderer = new IntervalBarRenderer();
        /*// �������ӿ��
        renderer.setMaximumBarWidth(0.2);
        // �������Ӹ߶�
        renderer.setMinimumBarLength(0.2);
        // �������ӱ߿���ɫ
        renderer.setBaseOutlinePaint(Color.BLACK);
        // �������ӱ߿�ɼ�
        renderer.setDrawBarOutline(true);
        // ����ÿ��������������ƽ������֮�����
        renderer.setItemMargin(0.5);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);*/
        // ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
        renderer.setIncludeBaseInRange(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);


        plot.setRenderer(renderer);

        renderer.setSeriesPaint(0, Color.decode("#f90c0c"));
        renderer.setSeriesPaint(1, Color.decode("#0cf91e"));
        renderer.setSeriesPaint(2, Color.decode("#f5f90c"));



        /*BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setItemLabelFont(new Font("����", Font.PLAIN, 12));
        renderer.setItemLabelsVisible(true);
        plot.setRenderer(renderer);*/
        /*BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); // �趨�����������ɫ
        customBarRenderer.setSeriesPaint(0, Color.decode("#f90c0c"));

       customBarRenderer.setSeriesPaint(1, Color.decode("#0cf91e"));

       customBarRenderer.setSeriesPaint(2, Color.decode("#f5f90c"));*/




        chart.setBackgroundPaint(Color.decode("#f5f7db"));
        // �����￪ʼ
        //CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
        CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
        domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
        domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
        ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
        rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

        // �������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������

        frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame
        frame1.setMouseZoomable(true);
    }

    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "����", "ƻ��");
        dataset.addValue(100, "�Ϻ�", "ƻ��");
        dataset.addValue(100, "����", "ƻ��");
        dataset.addValue(200, "����", "����");
        dataset.addValue(200, "�Ϻ�", "����");
        dataset.addValue(200, "����", "����");
        dataset.addValue(300, "����", "����");
        dataset.addValue(300, "�Ϻ�", "����");
        dataset.addValue(300, "����", "����");
        dataset.addValue(400, "����", "�㽶");
        dataset.addValue(400, "�Ϻ�", "�㽶");
        dataset.addValue(400, "����", "�㽶");
        dataset.addValue(500, "����", "��֦");
        dataset.addValue(500, "�Ϻ�", "��֦");
        dataset.addValue(500, "����", "��֦");
        return dataset;
    }

    public ChartPanel getChartPanel() {
        return frame1;

    }

    public static void main(String[] args) {
        BarChart ba = new BarChart();
        Frame f=new Frame("���Ǵ��ڵı���");  //����java.awt����
        f.setSize(1000,800);
        f.setLocation(100,200);  //���Ͻ�����
        f.setVisible(true);
        JPanel panel = new JPanel();
        panel.add(ba.frame1);

        f.add(panel);

        System.out.println("--------------");
    }
}