/**
 * Created by piotrgrudzien on 4/1/17.
 */
public class IndexedEvent {

    private long dt;
    private int inputIndex;

    public IndexedEvent(long dt, int inputIndex) {
        this.dt = dt;
        this.inputIndex = inputIndex;
    }

    public long clipDt(long dt) {
        if(dt < 200) {
            return dt / 50;
        } else {
            return 200;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof IndexedEvent) {
            return (clipDt(this.dt) == clipDt(((IndexedEvent)o).dt) &&
                    (this.inputIndex == ((IndexedEvent)o).inputIndex));
        }
        return false;
    }

    public int hashCode() {
        return (int)dt + inputIndex;
    }

    public long getDt() {
        return dt;
    }

    public int getInputIndex() {
        return inputIndex;
    }

}
