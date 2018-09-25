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
        JFreeChart chart = ChartFactory.createBarChart3D("ˮ������ͼ", // ͼ�����
            null, // Ŀ¼�����ʾ��ǩ
            null, // ��ֵ�����ʾ��ǩ
            dataset, // ���ݼ�
            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
            true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
            true, // �Ƿ����ɹ���
            true // �Ƿ�����URL����
        );
        CategoryPlot plot = chart.getCategoryPlot();

        CategoryAxis domainAxis = plot.getDomainAxis();
        // domainAxis.setVerticalCategoryLabels(false);
        plot.setDomainAxis(domainAxis);

        ValueAxis rangeAxis = plot.getRangeAxis();
        // ������ߵ�һ�� Item ��ͼƬ���˵ľ���
        rangeAxis.setUpperMargin(0.15);
        // ������͵�һ�� Item ��ͼƬ�׶˵ľ���
        rangeAxis.setLowerMargin(0.15);
        plot.setRangeAxis(rangeAxis);

        BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseOutlinePaint(Color.BLACK);
        // ���� Wall ����ɫ
        renderer.setWallPaint(Color.gray);
        // ����ÿ��ˮ�������������ɫ
        renderer.setSeriesPaint(0, new Color(0, 0, 255));
        renderer.setSeriesPaint(1, new Color(0, 100, 255));
        renderer.setSeriesPaint(2, Color.GREEN);
        // ����ÿ��������������ƽ������֮�����
        renderer.setItemMargin(0.1);
        // ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
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
     * ��ȡһ����ʾ�õ�������ݼ�����
     *
     * @return
     */
    private static CategoryDataset getDataSet2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "����", "ƻ��");
        dataset.addValue(100, "�Ϻ�", "ƻ��");
        dataset.addValue(85, "����", "ƻ��");
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

}
/*
 * ��������� �Ұ�renderer.setItemLabelGenerator(new
 * StandardCategoryItemLabelGenerator()); �ĳ� renderer.setLabelGenerator(new
 * StandardCategoryLabelGenerator()); ��������һ����������ʾ������ ����λ��ָ��һ�£� ������
 * ������(ChartFactory.createBarChart3D)��������Ĵ��뼴�ɡ�
 */

/*
 * CategoryPlot plot = chart.getCategoryPlot(); BarRenderer3D renderer = new
 * BarRenderer3D(); //��ʾÿ��������ֵ�����޸ĸ���ֵ���������� renderer.setBaseLabelGenerator(new
 * StandardCategoryItemLabelGenerator()); renderer.setItemLabelFont(new
 * Font("����", Font.PLAIN, 12)); renderer.setItemLabelsVisible(true);
 * plot.setRenderer(renderer);
 */