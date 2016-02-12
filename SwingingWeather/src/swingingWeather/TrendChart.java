package swingingWeather;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class TrendChart {
    private static Scene createScene() {
        Group root = new Group();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Day");
        yAxis.setLabel("Temp");
        lineChart.setTitle("High and Lows for next 7 days");
        XYChart.Series highSeries = new XYChart.Series();
        highSeries.setName("High");
        XYChart.Series lowSeries = new XYChart.Series();
        lowSeries.setName("Law");
        highSeries.getData().add(new XYChart.Data(1,14));
        highSeries.getData().add(new XYChart.Data(2,14));
        highSeries.getData().add(new XYChart.Data(3, 15));
        highSeries.getData().add(new XYChart.Data(4, 24));
        highSeries.getData().add(new XYChart.Data(5, 34));
        highSeries.getData().add(new XYChart.Data(6, 36));
        highSeries.getData().add(new XYChart.Data(7, 22));
        highSeries.getData().add(new XYChart.Data(8, 45));
        highSeries.getData().add(new XYChart.Data(9, 43));
        highSeries.getData().add(new XYChart.Data(10, 17));
        highSeries.getData().add(new XYChart.Data(11, 29));
        highSeries.getData().add(new XYChart.Data(12, 25));

        lowSeries.getData().add(new XYChart.Data(1,-5));
        lowSeries.getData().add(new XYChart.Data(2,2));
        lowSeries.getData().add(new XYChart.Data(3, 3));
        lowSeries.getData().add(new XYChart.Data(4, 10));
        lowSeries.getData().add(new XYChart.Data(5, 6));
        lowSeries.getData().add(new XYChart.Data(6, 4));
        lowSeries.getData().add(new XYChart.Data(7, 3));
        lowSeries.getData().add(new XYChart.Data(8, -2));
        lowSeries.getData().add(new XYChart.Data(9, -19));
        lowSeries.getData().add(new XYChart.Data(10, -2));
        lowSeries.getData().add(new XYChart.Data(11, 5));
        lowSeries.getData().add(new XYChart.Data(12, 1));
        lineChart.getData().add(highSeries);
        lineChart.getData().add(lowSeries);
        return new Scene(lineChart,300,150);

    }

    public static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }


}
