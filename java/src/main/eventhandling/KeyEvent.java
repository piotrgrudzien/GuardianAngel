import org.jnativehook.keyboard.NativeKeyEvent;

import static org.jnativehook.keyboard.NativeKeyEvent.*;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class KeyEvent implements Event {

    private String type;
    private long when;
    private int rawCode;
    private String keyChar;


    public KeyEvent(NativeKeyEvent nativeKeyEvent) {
        this.when = nativeKeyEvent.getWhen();
        this.rawCode = nativeKeyEvent.getRawCode();
        this.keyChar = String.valueOf(nativeKeyEvent.getKeyChar());
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

    public KeyEvent(String[] keyEvent) {
        type = keyEvent[1];
        when = Long.parseLong(keyEvent[0]);
        rawCode = Integer.parseInt(keyEvent[2]);
        try {
            if(rawCode == 36) {
                keyChar = "ENTER";
            } else if (rawCode == 43) {
                keyChar = ",";
            } else if (rawCode == 49) {
                keyChar = "SPACE";
            } else if (rawCode == 123) {
                keyChar = "KEY_LEFT";
            } else if (rawCode == 124) {
                keyChar = "KEY_RIGHT";
            } else if (rawCode == 125) {
                keyChar = "KEY_DOWN";
            } else if (rawCode == 126) {
                keyChar = "KEY_UP";
            } else if (rawCode == 48) {
                keyChar = "TAB";
            } else if (rawCode == 51) {
                keyChar = "DELETE";
            } else if (rawCode == 53) {
                keyChar = "ESCAPE";
            } else {
                keyChar = keyEvent[3];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
            System.out.println("Problem with rawCode " + rawCode);
            System.exit(1);
        }
    }

    public long getWhen() {
        return when;
    }

    public int getRawCode() {
        return rawCode;
    }

    public String getKeyChar() {
        return keyChar;
    }

    public String toCSV() {
        return when + "," + type + "," + rawCode + "," + keyChar;
    }

    public String type() {
        return type;
    }

    public String provideSomeMLparams() {
        return keyChar;
    }
}
