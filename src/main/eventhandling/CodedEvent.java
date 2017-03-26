/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class CodedEvent {

    private long timestamp;

    private int code;

    public CodedEvent(long timestamp, int code) {
        this.timestamp = timestamp;
        this.code = code;
    }
}
