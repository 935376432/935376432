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
        JFreeChart chart = ChartFactory.createBarChart3D("ˮ��", // ͼ�����
            "ˮ������", // Ŀ¼�����ʾ��ǩ
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


        // ʹ���Զ������Ⱦ��
        /*IntervalBarRenderer renderer = new IntervalBarRenderer();
        // �������ӿ��
        renderer.setMaximumBarWidth(0.2);
        // �������Ӹ߶�
        renderer.setMinimumBarLength(0.2);
        // �������ӱ߿���ɫ
        renderer.setBaseOutlinePaint(Color.BLACK);
        // �������ӱ߿�ɼ�
        renderer.setDrawBarOutline(true);
        // ����ÿ��������������ƽ������֮�����
        renderer.setItemMargin(0.5);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        // ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
        renderer.setIncludeBaseInRange(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);*/

        BarRenderer barrenderer = new BarRenderer();
        barrenderer.setMaximumBarWidth(0.1);
        barrenderer.setMinimumBarLength(0.1);
        barrenderer.setIncludeBaseInRange(true);
        barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barrenderer.setBaseItemLabelsVisible(true);
        //����ȴû��Ч��������������ȥ��ԭ��Ȼ��ûд��仰��
        plot.setRenderer(barrenderer);


        //plot.setRenderer(renderer);

        barrenderer.setSeriesPaint(0, Color.decode("#f90c0c"));
        barrenderer.setSeriesPaint(1, Color.decode("#0cf91e"));
        barrenderer.setSeriesPaint(2, Color.decode("#f5f90c"));



        /*BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setItemLabelFont(new Font("����", Font.PLAIN, 12));
        renderer.setItemLabelsVisible(true);
        plot.setRenderer(renderer);*/
        /*BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); // �趨�����������ɫ
        customBarRenderer.setSeriesPaint(0, Color.decode("#f90c0c"));

       customBarRenderer.setSeriesPaint(1, Color.decode("#0cf91e"));

       customBarRenderer.setSeriesPaint(2, Color.decode("#f5f90c"));*/




        chart.setBackgroundPaint(Color.white);
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
}