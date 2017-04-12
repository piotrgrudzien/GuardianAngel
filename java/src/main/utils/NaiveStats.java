import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piotrgrudzien on 4/1/17.
 */
public class NaiveStats {

    private Map<IndexedEvent, double[]> indexedEventsCounts;
    private Map<Integer, double[]> dtCounts;
    private int outputSize;
    private double[] logPrior;

    public NaiveStats(int outputSize) {
        this.outputSize = outputSize;
        logPrior = new double[outputSize];
        for (int i = 0; i < outputSize; i++) {
            logPrior[i] = Math.log(((double) 1) / outputSize);
        }
        indexedEventsCounts = new HashMap<>();
        dtCounts = new HashMap<>();
    }

    public void update(IndexedEvent event) {
        updateDtCounts(event.getDt(), event.getInputIndex());
        updateIndexedEventCounts(event);
    }

    private void updateDtCounts(long dt, int index) {
        int clippedDt = IndexedEvent.clipDtToInteger(dt);
        if(!dtCounts.containsKey(clippedDt)) {
            dtCounts.put(clippedDt, ArrayHelper.arrayofConst(outputSize, 0));
        }
        dtCounts.get(clippedDt)[index]++;
    }

    private void updateIndexedEventCounts(IndexedEvent event) {
        if(!indexedEventsCounts.containsKey(event)) {
            indexedEventsCounts.put(event, ArrayHelper.arrayofConst(outputSize, 0));
        }
        indexedEventsCounts.get(event)[event.getInputIndex()]++;
    }

    public double[] getLogUpdate(IndexedEvent indexedEvent) {
        double[] logUpdate = new double[outputSize];
        if(indexedEventsCounts.containsKey(indexedEvent)) {
            double[] update = indexedEventsCounts.get(indexedEvent);
            double updateSum = ArrayHelper.sum(update);
            for (int i = 0; i < outputSize; i++) {
                logUpdate[i] = Math.log((update[i] + 2E-8) / updateSum);
            }
            return logUpdate;
        }
        return logPrior;
    }

    public double[] getLogUpdate(long dt) {
        double[] logUpdate = new double[outputSize];
        if(dtCounts.containsKey(IndexedEvent.clipDtToInteger(dt))) {
            double[] update = dtCounts.get(IndexedEvent.clipDtToInteger(dt));
            double updateSum = ArrayHelper.sum(update);
            for (int i = 0; i < outputSize; i++) {
                logUpdate[i] = Math.log((update[i] + 2E-8) / updateSum);
            }
            return logUpdate;
        }
        return logPrior;
    }
}
