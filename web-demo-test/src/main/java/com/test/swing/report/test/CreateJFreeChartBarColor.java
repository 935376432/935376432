package com.test.swing.report.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class CreateJFreeChartBarColor {

    /**
     * ����JFreeChart Bar Chart����״ͼ��
     */
    public static void main(String[] args) {
        // ����1������CategoryDataset����׼�����ݣ�
        CategoryDataset dataset = createDataset();
        // ����2������Dataset ����JFreeChart�����Լ�����Ӧ������
        JFreeChart freeChart = createChart(dataset);
        // ����3����JFreeChart����������ļ���Servlet�������
        //saveAsFile(freeChart, "E:\\bar.png", 500, 400);

        Frame f=new Frame("���Ǵ��ڵı���");  //����java.awt����
        f.setSize(400,300);
        f.setLocation(10,20);  //���Ͻ�����
        f.setVisible(true);
        ChartPanel frame1 = new ChartPanel(freeChart, true);
        f.add(frame1);

        System.out.println("--------------");
    }

    // ����Ϊ�ļ�
    public static void saveAsFile(JFreeChart chart, String outputPath, int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // ����ΪPNG�ļ�
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    // ����CategoryDataset����JFreeChart����
    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        JFreeChart jfreechart = ChartFactory.createBarChart("ѧ��ͳ��ͼ", // ����
                "ѧ������", // categoryAxisLabel ��category�ᣬ���ᣬX��ı�ǩ��
                "����", // valueAxisLabel��value�ᣬ���ᣬY��ı�ǩ��
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, false, // legend
                false, // tooltips
                false); // URLs

        Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);

        jfreechart.setTextAntiAlias(false);
        jfreechart.setBackgroundPaint(Color.white);

        CategoryPlot plot = jfreechart.getCategoryPlot();// ���ͼ���������

        // ���ú����߿ɼ�
        plot.setRangeGridlinesVisible(true);
        // ����ɫ��
        plot.setRangeGridlinePaint(Color.gray);
        // �����ᾫ��
        NumberAxis vn = (NumberAxis) plot.getRangeAxis();
        // vn.setAutoRangeIncludesZero(true);
        DecimalFormat df = new DecimalFormat("#0.0");
        vn.setNumberFormatOverride(df); // ���������ݱ�ǩ����ʾ��ʽ

        // x������
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(labelFont);// �����
        domainAxis.setTickLabelFont(labelFont);// ����ֵ
        // Lable��Math.PI/3.0������б
        // domainAxis.setCategoryLabelPositions(CategoryLabelPositions
        // .createUpRotationLabelPositions(Math.PI / 3.0));
        domainAxis.setMaximumCategoryLabelWidthRatio(6.00f);// �����ϵ� Lable
        // �Ƿ�������ʾ

        // ���þ���ͼƬ��˾���
        domainAxis.setLowerMargin(0.1);
        // ���þ���ͼƬ�Ҷ˾���
        domainAxis.setUpperMargin(0.1);
        // ���� columnKey �Ƿ�����ʾ
        // domainAxis.setSkipCategoryLabelsToFit(true);
        plot.setDomainAxis(domainAxis);
        // ������ͼ����ɫ��ע�⣬ϵͳȡɫ��ʱ��Ҫʹ��16λ��ģʽ���鿴��ɫ���룬�����Ƚ�׼ȷ��
        plot.setBackgroundPaint(new Color(255, 255, 204));

        // y������
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(labelFont);
        rangeAxis.setTickLabelFont(labelFont);
        // ������ߵ�һ�� Item ��ͼƬ���˵ľ���
        rangeAxis.setUpperMargin(0.15);
        // ������͵�һ�� Item ��ͼƬ�׶˵ľ���
        rangeAxis.setLowerMargin(0.15);
        plot.setRangeAxis(rangeAxis);

        // ���������������(�ؼ�)
        TextTitle textTitle = jfreechart.getTitle();
        textTitle.setFont(new Font("����", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
        vn.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        vn.setLabelFont(new Font("����", Font.PLAIN, 12));
        // jfreechart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));

        // ʹ���Զ������Ⱦ��
        CustomRender renderer = new CustomRender();
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
        jfreechart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        // ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
        renderer.setIncludeBaseInRange(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer);
        // ��������͸����
        plot.setForegroundAlpha(1.0f);

        // ����ɫ ͸����
        plot.setBackgroundAlpha(0.5f);

        return jfreechart;
    }

    // ����CategoryDataset����
    public static CategoryDataset createDataset() {
        double[][] data = new double[][] { { 25, 24, 40, 12, 33, 33 } };
        String[] rowKeys = { "" };
        String[] columnKeys = { "����", "����", "����", "����", "����", "�԰�" };
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
        return dataset;
    }

}