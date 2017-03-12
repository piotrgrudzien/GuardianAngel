import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class MouseEvent implements Event {

    private NativeMouseEvent nativeMouseEvent;
    private String type;

    public MouseEvent(NativeMouseEvent nativeMouseEvent, String type){
        this.nativeMouseEvent = nativeMouseEvent;
        this.type = type;
    }

    public String toCSV() {
        return nativeMouseEvent.getWhen() + "," + type + "," + nativeMouseEvent.getX() + "," + nativeMouseEvent.getY();
    }


    public String type() {
        return type;
    }
}
