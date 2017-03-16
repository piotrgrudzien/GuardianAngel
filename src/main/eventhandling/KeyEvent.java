import org.jnativehook.keyboard.NativeKeyEvent;

import static org.jnativehook.keyboard.NativeKeyEvent.*;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class KeyEvent implements Event {

    private String type;
    private long when;
    private int rawCode;
    private char keyChar;


    public KeyEvent(NativeKeyEvent nativeKeyEvent) {
        this.when = nativeKeyEvent.getWhen();
        this.rawCode = nativeKeyEvent.getRawCode();
        this.keyChar = nativeKeyEvent.getKeyChar();
        switch (nativeKeyEvent.getID()){
            case NATIVE_KEY_TYPED:
                this.type = EventType.KEY_TYPED;
                break;
            case NATIVE_KEY_PRESSED:
                this.type = EventType.KEY_PRESSED;
                break;
            case NATIVE_KEY_RELEASED:
                this.type = EventType.KEY_RELEASED;
                break;
            default:
                throw new IllegalArgumentException("Unrecognised key event id " + nativeKeyEvent.getID());
        }
    }

    public String toCSV() {
        return when + "," + type + "," + rawCode + "," + keyChar;
    }

    public String type() {
        return type;
    }
}
