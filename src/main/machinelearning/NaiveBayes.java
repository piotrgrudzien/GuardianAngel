/**
 * Created by piotrgrudzien on 3/27/17.
 */
public class NaiveBayes implements Model {

    private long modelTime;

    public NaiveBayes() {
        modelTime = 0;
    }

    public long getModelTime() {
        return modelTime;
    }

    public void feedEvent(long when, int index, boolean isOutput) {
        long dt = when - modelTime;
        System.out.println(when + ": (" + dt + " ms since last): " + index + " (output: " + isOutput + ")");
        modelTime = when;
        // don't update the model when isOutput is true
        // make a prediction for every event and then check the prediction
        // if the event directly succeeding is a key typed event
    }
}
