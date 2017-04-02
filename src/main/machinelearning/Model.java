/**
 * Created by piotrgrudzien on 3/27/17.
 */
interface Model {

    long getModelTime();

    void feedEvent(long when, int index, String eventType);

    void setLookup(Lookup lookup);

    Lookup getLookup();

    void printResults();

}
