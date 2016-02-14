package swingingWeather;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 */
public class TrendChart {
    private XYChart.Series highSeries;
    private XYChart.Series lowSeries;
    private int currentHigh=0;
    private  int currentLow=0;

    /**
     *
     */
    public TrendChart() {
        highSeries = new XYChart.Series();
        lowSeries = new XYChart.Series();
        highSeries.setName("High");
        lowSeries.setName("Low");
    }

    /**
     *
     * @return
     */
    private Scene geteScene() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Day");
        yAxis.setLabel("Temp");
        lineChart.setTitle("High and Lows for next 7 days");
        lineChart.getData().add(highSeries);
        lineChart.getData().add(lowSeries);
        return new Scene(lineChart,300,150);

    }

    /**
     *
     * @param fxPanel
     */
    public void initFX(JFXPanel fxPanel) {
        Scene scene = geteScene();
        fxPanel.setScene(scene);
    }

    /**
     *
     * @param high
     */
    public void addHigh(double high) {
        highSeries.getData().add(new XYChart.Data(currentHigh, high));
        currentHigh++;
    }

    /**
     *
     * @param high
     */
    public void addLow(double high) {
        lowSeries.getData().add(new XYChart.Data(currentLow, high));
        currentLow++;
    }

    public void clearChart() {
        highSeries = new XYChart.Series();
        lowSeries = new XYChart.Series();
    }


}
