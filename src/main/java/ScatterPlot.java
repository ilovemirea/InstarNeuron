import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;
    private static InstarNeuron instarNeuron = new InstarNeuron();
    static double[][] trainData =
            {
                    // {x1, x2, y}
                    // y: 1=red, 0=green, -1=blue
                    //red
                    {0.7, 0, 1}, {0.5, 0, 1}, {0.3, 0, 1},
                    {0.65, 0.15, 1}, {0.65, -0.15, 1},
                    {0.5, 0.2, 1}, {0.5, -0.2, 1},
                    {0.35, 0.15, 1}, {0.35, -0.15, 1},
                    //blue
                    {0, 0.7, -1}, {0, 0.5, -1}, {0, 0.3, -1},
                    {0.15, 0.65, -1}, {-0.15, 0.65, -1},
                    {0.2, 0.5, -1}, {-0.2, 0.5, -1},
                    {0.15, 0.35, -1}, {-0.15, 0.35, -1},
                    //green
                    {0.3, 0.3, 0}, {0.45, 0.45, 0}, {0.6, 0.6, 0}, {0.75, 0.75, 0}, {0.9, 0.9, 0},
                    {0.4, 0.3, 0}, {0.3, 0.4, 0},
                    {0.6, 0.35, 0}, {0.35, 0.6, 0},
                    {0.75, 0.45, 0}, {0.45, 0.75, 0},
                    {0.8, 0.65, 0}, {0.65, 0.8, 0},
                    {0.85, 0.7, 0}, {0.7, 0.85, 0},
            };
    public ScatterPlot(String title) {
        super(title);
        // Create dataset
        XYDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Задача классификации",
                "X1", "X2", dataset);
        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));
        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series1 = new XYSeries("red");
        XYSeries series2 = new XYSeries("blue");
        XYSeries series3 = new XYSeries("green");

        for (int i = 0; i < 10000; i++){
            double x1 = Math.random();
            double x2 = Math.random();
            if(instarNeuron.activate(new double[]{x1, x2}, instarNeuron.weights) > 0.34 ){
                series1.add(x1, x2);
            }
            else if(instarNeuron.activate(new double[]{x1, x2}, instarNeuron.weights) < -0.35){
                series2.add(x1, x2);
            }
            else
                series3.add(x1, x2);
        }
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        return dataset;
    }

    public static void main(String[] args) {
        System.out.println("Значения весов до обучения: w1 = " + instarNeuron.weights[0] + ", w2 = " + instarNeuron.weights[1]);
        // Обучение заданным набором данных
        for (int i = 0; i < trainData.length; i++){
            instarNeuron.train(new double[] {trainData[i][0], trainData[i][1]}, trainData[i][2]);
        }
        System.out.println("Значения весов после обучения: w1 = " + instarNeuron.weights[0] + ", w2 = " + instarNeuron.weights[1]);
        SwingUtilities.invokeLater(() -> {
            ScatterPlot example = new ScatterPlot("Практическая работа №1");
            example.setSize(1000, 1000);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
