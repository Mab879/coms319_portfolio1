package swingingWeather;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * This class is the trend chart
 */
public class TrendChart {
	private XYChart.Series highSeries;
	private XYChart.Series lowSeries;
	private LineChart<Number, Number> lineChart;
	private int currentHigh = 0;
	private int currentLow = 0;

	/**
	 * Constructor
	 */
	public TrendChart() {
		highSeries = new XYChart.Series();
		lowSeries = new XYChart.Series();
		highSeries.setName("High");
		lowSeries.setName("Low");
	}

	/**
	 * Create scene for the chart
	 *
	 * @return the scene with the chart
	 */
	private Scene getScene() {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		this.lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		xAxis.setLabel("Day");
		yAxis.setLabel("Temp");
		lineChart.setTitle("High and Lows for next 7 days");
		lineChart.getData().add(highSeries);
		lineChart.getData().add(lowSeries);
		return new Scene(lineChart, 300, 150);

	}

	/**
	 * This method creates the chart on the give JFXPanel
	 *
	 * @param fxPanel
	 */
	public void initFX(JFXPanel fxPanel) {
		Scene scene = getScene();
		fxPanel.setScene(scene);
	}

	/**
	 * Adds a value to the high seris
	 *
	 * @param high
	 */
	public void addHigh(double high) {
		highSeries.getData().add(new XYChart.Data(currentHigh, high));
		currentHigh++;
	}

	/**
	 * Adds a value to the low seris
	 *
	 * @param low
	 */
	public void addLow(double low) {
		lowSeries.getData().add(new XYChart.Data(currentLow, low));
		currentLow++;
	}

	/**
	 * Clears all values from the chart
	 */
	public void clearChart() {
		highSeries.getData().clear();
		lowSeries.getData().clear();
		currentLow = 0;
		currentHigh = 0;
	}

}
