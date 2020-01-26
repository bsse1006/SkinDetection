package package123;

import java.io.Serializable;

public class Database implements Serializable {

    private double [] [] [] trainedData = new double [256] [256] [256];

    public double[][][] getTrainedData() {
        return trainedData;
    }

    public Database(double[][][] trainedData) {
        this.trainedData = trainedData;
    }
}
