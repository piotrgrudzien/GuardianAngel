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
        // don't update the model when isOutput is true
        // make a prediction for every event and then check the prediction
        // if the event directly succeeding is a key typed event
        updatePrediction(dt);
//        TODO figure out what to do to the model for different event types
//        if(!isKeyTyped) {
//            updateModel(dt, index);
//        } else {
//            scorePrediction(index);
//        }
    }

    private void updatePrediction(long dt) {
        // this method updates prediction for every event
        // before you can see what the event is
        for (int i = 0; i < outputSize; i++) {
            prediction[i] = rand.nextFloat();
        }
    }

    private void updateModel(long dt, int index) {
        // this method updates the model incorporating
        // information about then new event
    }

    private void scorePrediction(int labelIndex) {
        // this method is called on a key typed event
        // which provides a label which is used to score
        // the prediction made
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
        }
        totalPredictions++;
        System.out.println("Accuracy " + percentFormat.format((double) correctPredictions / totalPredictions) +
        " (" + correctPredictions + "/" + totalPredictions + ") random guess benchmark: " +
        percentFormat.format((double) 1 / outputSize));
    }
}
