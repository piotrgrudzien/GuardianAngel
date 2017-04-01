import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by piotrgrudzien on 3/27/17.
 */
public class NaiveBayes implements Model {

    private long modelTime;
    private double[] prediction;
    private int inputSize;
    private int outputSize;
    private int correctPredictions;
    private int totalPredictions;
    private double logloss;
    private Random rand;
    private DecimalFormat percentFormat;

    public NaiveBayes() {
        modelTime = 0;
        // file1.txt
        inputSize = 224;
        outputSize = 92;
        prediction = new double[outputSize];
        correctPredictions = 0;
        totalPredictions = 0;
        logloss = 0;
        rand = new Random();
//        rand.setSeed(152);
        percentFormat = new DecimalFormat("##.##%");
    }

    public long getModelTime() {
        return modelTime;
    }

    public void feedEvent(long when, int index, String eventType) {
        long dt = when - modelTime;
        modelTime = when;
        // update prediction on all except KT
        // check your current prediction on KT
        // update stats on all except KT

        if(!eventType.equals(EventType.KEY_TYPED)) {
            updatePrediction(dt);
        } else {
            updateStats(dt, index);
            scorePrediction(index);
        }
    }

    private void updatePrediction(long dt) {
        // Bayesian update
        // add log probability for the event
        for (int i = 0; i < outputSize; i++) {
            prediction[i] = rand.nextFloat();
        }
    }

    private void updateStats(long dt, int index) {
        // you received a label
        // update stats for events since the previous label
    }

    private void scorePrediction(int labelIndex) {
        // this method is called on a key typed event
        // which provides a label which is used to score
        // the prediction made
        // TODO add top5 accuracy
        int indexMax = 0;
        double max = prediction[indexMax];
        for (int i = 1; i < outputSize; i++) {
            if(prediction[i] > max) {
                indexMax = i;
                max = prediction[i];
            }
        }
        if(indexMax == labelIndex) {
            correctPredictions++;
            logloss -= Math.log(max + 10E-16);
        }
        totalPredictions++;
        System.out.println("Logloss " + (logloss / totalPredictions) +
                " Accuracy " + percentFormat.format((double) correctPredictions / totalPredictions) +
        " (" + correctPredictions + "/" + totalPredictions + ") Random guess benchmark: " +
        percentFormat.format((double) 1 / outputSize));
    }
}
