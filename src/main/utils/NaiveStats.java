import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piotrgrudzien on 4/1/17.
 */
public class NaiveStats {

    private Map<IndexedEvent, double[]> counts;
    private int outputSize;
    private double[] logPrior;

    public NaiveStats(int outputSize) {
        this.outputSize = outputSize;
        logPrior = new double[outputSize];
        for (int i = 0; i < outputSize; i++) {
            logPrior[i] = Math.log(((double) 1) / outputSize);
        }
        counts = new HashMap<>();
    }

    public void update(List<IndexedEvent> shortMemory, int outputIndex) {
        for (IndexedEvent indexedEvent : shortMemory) {
            if(!counts.containsKey(indexedEvent)) {
                counts.put(indexedEvent, new double[outputSize]);
                for (int i = 0; i < outputSize; i++) {
                    counts.get(indexedEvent)[i] = 0;
                }
                counts.get(indexedEvent)[outputIndex]++;
            }
        }
    }

    public double[] getLogUpdate(IndexedEvent indexedEvent) {
        double[] logUpdate = new double[outputSize];
        if(counts.containsKey(indexedEvent)) {
            double[] update = counts.get(indexedEvent);
            double updateSum = ArrayHelper.sum(update);
            for (int i = 0; i < outputSize; i++) {
                logUpdate[i] = Math.log((update[i] + 10E-16) / updateSum);
            }
            return logUpdate;
        }
        return logPrior;
    }
}
