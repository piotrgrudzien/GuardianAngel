import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by piotrgrudzien on 3/27/17.
 */
public class NaiveBayes implements Model {

    private long modelTime;
    private double[] prediction;
    private int inputSize;
    private int outputSize;
    private int correctPredictionsTop1;
    private int correctPredictionsTop3;
    private int totalPredictions;
    private double logloss;
    private Random rand;
    private DecimalFormat percentFormat;
    private List<IndexedEvent> shortMemory;
    private NaiveStats naiveStats;

    public NaiveBayes() {
        modelTime = 0;
        // file1.txt
        inputSize = 224;
        outputSize = 92;
        prediction = new double[outputSize];
        correctPredictionsTop1 = 0;
        correctPredictionsTop3 = 0;
        totalPredictions = 0;
        logloss = 0;
        rand = new Random();
//        rand.setSeed(152);
        percentFormat = new DecimalFormat("##.##%");
        shortMemory = new ArrayList<>();
        naiveStats = new NaiveStats(outputSize);
        setPrior();
    }

    private void setPrior() {
        // completely uninformed prior
        // next try better priors based on previous characters
        for (int i = 0; i < outputSize; i++) {
            prediction[i] = Math.log(((double) 1) / outputSize);
        }
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
            IndexedEvent indexedEvent = new IndexedEvent(dt, index);
            shortMemory.add(indexedEvent);
            updatePrediction(indexedEvent);
        } else {
            naiveStats.update(shortMemory, index);
            scorePrediction(index);
        }
    }

    private void updatePrediction(IndexedEvent indexedEvent) {
        // random guess
//        for (int i = 0; i < outputSize; i++) {
//            prediction[i] = rand.nextFloat();
//        }
        // Naive Bayes Update
        double[] update = naiveStats.getLogUpdate(indexedEvent);

        for (int i = 0; i < outputSize; i++) {
            prediction[i] += update[i];
        }

    }

    private void scorePrediction(int outputIndex) {
        // this method is called on a key typed event
        // which provides a label which is used to score
        // the prediction made
        int indexMax1 = 0, indexMax2 = 0, indexMax3 = 0;
        double max1 = 0, max2 = 0, max3 = 0;
        for (int i = 1; i < outputSize; i++) {
            if(prediction[i] > max1) {
                indexMax3 = indexMax2; max3 = max2;
                indexMax2 = indexMax1; max2 = max1;
                indexMax1 = i; max1 = prediction[i];
            } else if(prediction[i] > max2) {
                indexMax3 = indexMax2; max3 = max2;
                indexMax2 = i; max2 = prediction[i];
            } else if(prediction[i] > max3) {
                indexMax3 = i; max3 = prediction[i];
            }
        }
        if(indexMax1 == outputIndex) {
            correctPredictionsTop1++;
            correctPredictionsTop3++;
            logloss -= Math.log(max1 + 10E-16);
        } else if((indexMax2 == outputIndex) || (indexMax3 == outputIndex)) {
            correctPredictionsTop3++;
        }
        totalPredictions++;
        if(totalPredictions % 1000 == 0) {
            System.out.println("Logloss " + (logloss / totalPredictions) +
                    " Accuracy " + percentFormat.format((double) correctPredictionsTop1 / totalPredictions) +
                    " (" + correctPredictionsTop1 + "/" + totalPredictions + ")" +
                    " Accuracy Top3 " + percentFormat.format((double) correctPredictionsTop3 / totalPredictions) +
                    " (" + correctPredictionsTop3 + "/" + totalPredictions + ")");
        }
    }
}
