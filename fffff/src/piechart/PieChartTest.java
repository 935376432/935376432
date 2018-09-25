/*
 *jiji java
 */
package piechart;

import java.awt.Dimension;

import javax.swing.JApplet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class PieChartTest extends JApplet {
    /** */
    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        JFXPanel chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(600, 400));


        Scene scene = new Scene(new Group());

        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(new PieChart.Data("服务器", 13),
                new PieChart.Data("防火墙", 25),
                new PieChart.Data("交换机", 10),
                new PieChart.Data("正向隔离", 22),
                new PieChart.Data("反向隔离", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        ((Group) scene.getRoot()).getChildren().add(chart);

        chart.setLabelLineLength(10);
        //chart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
            caption.setText("aa");
            System.out.println(data.getPieValue());
        }
        chart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf("aaa") + "%");
            }
        });

        //chartFxPanel.setScene(new Scene(chart));

    }

}
