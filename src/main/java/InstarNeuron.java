import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class InstarNeuron{

    public static double[] weights = {0.0, 0.0};
    private double learningRate = 0.15;

    public double activate(double[] input, double[] weights){
        return module(normalize(input)) * module(normalize(weights)) * cos(normalize(input), normalize(weights));
    }

    public void train(double[] input, double expectedResult){
        weights[0] = weights[0] + learningRate * expectedResult * (input[0] - weights[0]);
        weights[1] = weights[1] + learningRate * expectedResult * (input[1] - weights[1]);
    }

    private double[] normalize(double[] vector){
        double x = vector[0];
        double y = vector[1];
        return new double[] {x / sqrt(pow(x, 2)+ pow(y, 2)), y / sqrt(pow(x, 2)+ pow(y, 2))};
    }

    private double module(double[] vector){
        double x = vector[0];
        double y = vector[1];
        return sqrt(pow(x, 2) + pow(y, 2));
    }

    private double cos(double[] vector1, double[] vector2){
        return (vector1[0] * vector2[0] + vector1[1] * vector2[1]) / (module(vector1) * module(vector2));
    }
}
