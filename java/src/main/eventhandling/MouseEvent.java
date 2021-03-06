import org.jnativehook.mouse.NativeMouseEvent;

import static org.jnativehook.mouse.NativeMouseEvent.*;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class MouseEvent implements Event {

    private String type;
    private long when;
    private int x;
    private int y;

    public MouseEvent(NativeMouseEvent nativeMouseEvent){
        this.when = nativeMouseEvent.getWhen();
        this.x = nativeMouseEvent.getX();
        this.y = nativeMouseEvent.getY();
        switch (nativeMouseEvent.getID()){
            case NATIVE_MOUSE_CLICKED:
                this.type = EventType.MOUSE_CLICKED;
                break;
            case NATIVE_MOUSE_DRAGGED:
                this.type = EventType.MOUSE_DRAGGED;
                break;
            case NATIVE_MOUSE_MOVED:
                this.type = EventType.MOUSE_MOVED;
                break;
            case NATIVE_MOUSE_PRESSED:
                this.type = EventType.MOUSE_PRESSED;
                break;
            case NATIVE_MOUSE_RELEASED:
                this.type = EventType.MOUSE_RELEASED;
                break;
            default:
                throw new IllegalArgumentException("Unrecognised mouse event id " + nativeMouseEvent.getID());
        }
    }

    public MouseEvent(String[] mouseEvent) {
        type = mouseEvent[1];
        when = Long.parseLong(mouseEvent[0]);
        x = Integer.parseInt(mouseEvent[2]);
        y = Integer.parseInt(mouseEvent[3]);
    }

    public long getWhen() {
        return when;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toCSV() {
        return when + "," + type + "," + x + "," + y;
    }

    public String type() {
        return type;
    }

    public String provideSomeMLparams() {
        return toCSV();
    }
}
