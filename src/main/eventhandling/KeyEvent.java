import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class KeyEvent implements Event {

    private String type;
    private long when;
    private int rawCode;
    private char keyChar;


    public KeyEvent(NativeKeyEvent nativeKeyEvent, String type) {
        this.type = type;
        this.when = nativeKeyEvent.getWhen();
        this.rawCode = nativeKeyEvent.getRawCode();
        this.keyChar = nativeKeyEvent.getKeyChar();
    }

    public String toCSV() {
        return when + "," + type + "," + rawCode + "," + keyChar;
    }

    public String type() {
        return type;
    }
}
