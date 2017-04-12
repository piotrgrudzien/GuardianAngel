import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by piotrgrudzien on 3/27/17.
 */
public class NaiveBayesNoOrder implements Model {

    public Lookup lookup;
    private long modelTime;
    private int naiveBayesDepth;
    private LinkedList<double[]> predictionFactors;
    private double[] currentPrediction;
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

    public NaiveBayesNoOrder() {
        modelTime = 0;
        // file1.txt
        inputSize = 100;
        outputSize = 99;
        naiveBayesDepth = 32*2;
        predictionFactors = new LinkedList<>();
        for(int i = 0; i < naiveBayesDepth; i++) {
            predictionFactors.add(new double[outputSize]);
        }
        currentPrediction = ArrayHelper.arrayofConst(outputSize, (double) 1 / outputSize);
        correctPredictionsTop1 = 0;
        correctPredictionsTop3 = 0;
        correctPredictionsTop5 = 0;
        totalPredictions = 0;
        logloss = 0;
        rand = new Random();
        rand.setSeed(152);
        percentFormat = new DecimalFormat("##.#%");
        naiveStats = new NaiveStats(outputSize);
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public Lookup getLookup() {
        return this.lookup;
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
        IndexedEvent indexedEvent = new IndexedEvent(dt, index);
        naiveStats.update(indexedEvent);
        updatePrediction(indexedEvent);
    }

    private void updatePrediction(IndexedEvent event) {
        // Naive Bayes Update
        updatePredictionFactors(naiveStats.getLogUpdate(event));
    }

    private void updatePrediction(long dt) {
        // Naive Bayes Update
        updatePredictionFactors(naiveStats.getLogUpdate(dt));
    }

    private void updatePredictionFactors(double[] update) {
        predictionFactors.addFirst(update);
        if (predictionFactors.size() > naiveBayesDepth) {
            predictionFactors.removeLast();
        }
        double[] res = ArrayHelper.arrayofConst(outputSize, 0);
        for(double[] factor : predictionFactors) {
            if(res == null) {
                res = factor;
            } else {
                res = ArrayHelper.addElementwise(res, factor);
            }
        }
//        System.out.println("Predictions: [" + ArrayHelper.min(res) + ", " + ArrayHelper.max(res) + " ] : [" +
//        Math.exp(ArrayHelper.min(res)) + ", " + Math.exp(ArrayHelper.max(res)) + "]");
        if(res != null) currentPrediction = res;
    }

    private void scorePrediction(int outputIndex) {
        // random guess
//        double[] randomPrediction = new double[outputSize];
//        for (int i = 0; i < outputSize; i++) {
//            randomPrediction[i] = rand.nextFloat();
//        }
//        currentPrediction = randomPrediction;

        int[] top5 = ArrayHelper.getTop5Indexes(currentPrediction);
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
        ArrayHelper.reportTopPredictions(currentPrediction, lookup);
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
