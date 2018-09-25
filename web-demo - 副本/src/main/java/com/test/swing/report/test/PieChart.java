/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.swing.report.test;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
    ChartPanel frame1;
    public PieChart(){
          DefaultPieDataset data = getDataSet();
          JFreeChart chart = ChartFactory.createPieChart3D("ˮ������",data,true,false,false);
          //���ðٷֱ�
          PiePlot pieplot = (PiePlot) chart.getPlot();
          DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
          NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
          StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0} {2} {1} ̨", nf, df) ;//���StandardPieSectionLabelGenerator����
          pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�

          //û�����ݵ�ʱ����ʾ������
          pieplot.setNoDataMessage("��������ʾ");
          pieplot.setCircular(false);
          pieplot.setLabelGap(0.02D);

          /*// �����ܵı�����ɫ
          chart.setBackgroundPaint(ChartColor.yellow);
          // ���ñ�����ɫ
          chart.getTitle().setPaint(ChartColor.blue);
          // ���ͼ�����
          CategoryPlot p = chart.getCategoryPlot();
          // ����ͼ�ı�����ɫ
          p.setBackgroundPaint(ChartColor.black);
          // ���ñ������ɫ
          p.setRangeGridlinePaint(ChartColor.red);*/

          pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
          pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
         frame1=new ChartPanel (chart,true);
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
          PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
          piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������
          chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));
    }
    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("ƻ��",100);
        dataset.setValue("����",200);
        dataset.setValue("����",300);
        dataset.setValue("�㽶",400);
        dataset.setValue("��֦",500);
        return dataset;
}
    public ChartPanel getChartPanel(){
        return frame1;

    }
    public static void main(String[] args) {
        PieChart pie =  new PieChart();
        Frame f=new Frame("���Ǵ��ڵı���");  //����java.awt����
        f.setSize(800,600);
        f.setLocation(10,20);  //���Ͻ�����
        f.setVisible(true);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));
        panel.add(pie.getChartPanel());
        JDialog dia = new JDialog();
        dia.add(panel);
        f.add(dia);

    }
}

