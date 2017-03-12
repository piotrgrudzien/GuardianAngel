import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class KeyEvent implements Event {

    private NativeKeyEvent nativeKeyEvent;
    private String type;

    public KeyEvent(NativeKeyEvent nativeKeyEvent, String type) {
        this.type = type;
        this.nativeKeyEvent = nativeKeyEvent;
    }

    public String toCSV() {
        return nativeKeyEvent.getWhen() + "," + type + "," + nativeKeyEvent.getRawCode() + "," + nativeKeyEvent.getKeyChar();
    }

    public String type() {
        return type;
    }
}
