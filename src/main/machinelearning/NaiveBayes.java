import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by piotrgrudzien on 3/27/17.
 */
public class NaiveBayes implements Model {

    public Lookup lookup;
    private long modelTime;
    private double[] prediction;
    private int inputSize;
    private int outputSize;
    private int correctPredictionsTop1;
    private int correctPredictionsTop3;
    private int correctPredictionsTop5;
    private int totalPredictions;
    private double logloss;
    private Random rand;
    private DecimalFormat percentFormat;
    private NaiveStats naiveStats;

    public NaiveBayes() {
        modelTime = 0;
        // file1.txt
        inputSize = 100;
        outputSize = 99;
        prediction = new double[outputSize];
        correctPredictionsTop1 = 0;
        correctPredictionsTop3 = 0;
        correctPredictionsTop5 = 0;
        totalPredictions = 0;
        logloss = 0;
        rand = new Random();
//        rand.setSeed(152);
        percentFormat = new DecimalFormat("##.##%");
        naiveStats = new NaiveStats(outputSize);
        setPrior();
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public Lookup getLookup() {
        return this.lookup;
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
        // event comes in
        updatePrediction(dt); // update prediction just looking at dt
        scorePrediction(index); // score prediction
        // now you've observed what the key typed was
        // update all the relevant stats
        // need to always remember n events and update stats n times for each one
        naiveStats.update(new IndexedEvent(dt, index)); 
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
        int[] top5 = ArrayHelper.getTop5Indexes(prediction);
        if(top5[0] == outputIndex) {
            correctPredictionsTop1++;
            correctPredictionsTop3++;
            correctPredictionsTop5++;
        } else if((top5[1] == outputIndex) || (top5[2] == outputIndex)) {
            correctPredictionsTop3++;
            correctPredictionsTop5++;
        } else if((top5[3] == outputIndex) || (top5[4] == outputIndex)) {
            correctPredictionsTop5++;
        }
        totalPredictions++;
        if(totalPredictions > 65000) {
            describePrediction(outputIndex);
        }
    }

    private void describePrediction(int outputIndex) {
        System.out.print("\nJust in: " + lookup.getStringFromOutputIndex(outputIndex) + " ");
        ArrayHelper.reportTopPredictions(prediction, lookup);
    }

    public void printResults() {
        System.out.println(
                "Accuracy " + percentFormat.format((double) correctPredictionsTop1 / totalPredictions) +
                        " (" + correctPredictionsTop1 + "/" + totalPredictions + ")" +
                        " Accuracy Top3 " + percentFormat.format((double) correctPredictionsTop3 / totalPredictions) +
                        " (" + correctPredictionsTop3 + "/" + totalPredictions + ")" +
                        " Accuracy Top5 " + percentFormat.format((double) correctPredictionsTop5 / totalPredictions) +
                        " (" + correctPredictionsTop5 + "/" + totalPredictions + ")");
    }
}
