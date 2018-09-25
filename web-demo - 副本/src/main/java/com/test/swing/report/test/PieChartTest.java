package com.test.swing.report.test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

/**
 * ��״ͼ����
 *
 */
public class PieChartTest {


    /**
     * Creates a chart.
     *
     * @return A chart.
     */
    public static JFreeChart createPieChart() {

        //Creates a sample dataset ����һ���򵥵����ݼ�
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Samsung", new Double(27.8));
        dataset.setValue("Others", new Double(55.3));
        dataset.setValue("Nokia", new Double(16.8));
        dataset.setValue("Apple", new Double(17.1));

        //create a chart object  ���ݱ�״ͼ�ı�������ݼ��Ȳ�������ͼ�����
        JFreeChart chart = ChartFactory.createPieChart(
                "Smart Phones Manufactured / Q3 2011", // chart title
                dataset, // data
                false, // no legend
                true, // tooltips
                false // no URL generation
                );

        // set a custom background for the chart Ϊ��״ͼ���ñ���ɫ
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), new Color(
                20, 20, 20), new Point(400, 200), Color.white));

        // customise the title position and font  Ϊ��״ͼ�ı������ã�λ�á���ɫ������
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        //�����ͼ�ı������
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);

        // use gradients and white borders for the section colours  Ϊ������ɫ���뽥��ɫ�Ͱ�ɫ�߿�
        plot.setSectionPaint("Others",
                createGradientPaint(new Color(200, 200, 255), Color.BLUE)); //��ÿ��������������б������
        plot.setSectionPaint("Samsung",
                createGradientPaint(new Color(255, 200, 200), Color.RED));
        plot.setSectionPaint("Apple",
                createGradientPaint(new Color(200, 255, 200), Color.GREEN));
        plot.setSectionPaint("Nokia",
                createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

        // customise the section label appearance   Ϊ��ǩ�������
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        // add a subtitle giving the data source  Ϊÿ������Դ����ӱ���
        TextTitle source = new TextTitle(
                "Source: http://www.bbc.co.uk/news/business-15489523",
                new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        return chart;
    }

    /**
     * A utility method for creating gradient paints.
     * ���������ݶ���ɫ�Ļ滭
     * @param c1  color 1.
     * @param c2  color 2.
     *
     * @return A radial gradient paint.
     */
    private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};
        return new RadialGradientPaint(center, radius, dist,
                new Color[] {c1, c2});
    }

    public static void main(String[] args) {
        PieChart pie =  new PieChart();
        Frame f=new Frame("���Ǵ��ڵı���");  //����java.awt����
        f.setSize(800,600);
        f.setLocation(10,20);  //���Ͻ�����
        f.setVisible(true);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));
        JFreeChart a =createPieChart();
        panel.add(new ChartPanel (a,true));
        f.add(panel);

    }

}