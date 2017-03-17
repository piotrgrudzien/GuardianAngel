/**
 * Created by piotrgrudzien on 3/17/17.
 */
public class EventTypeParser {

    public static boolean isMouseEvent(String[] event) {
        return event[1].equals(EventType.MOUSE_CLICKED) ||
                event[1].equals(EventType.MOUSE_DRAGGED) ||
                event[1].equals(EventType.MOUSE_MOVED) ||
                event[1].equals(EventType.MOUSE_PRESSED) ||
                event[1].equals(EventType.MOUSE_RELEASED);
    }

    public static boolean isKeyEvent(String[] event) {
        return event[1].equals(EventType.KEY_TYPED) ||
                event[1].equals(EventType.KEY_RELEASED) ||
                event[1].equals(EventType.KEY_PRESSED);
    }
}
