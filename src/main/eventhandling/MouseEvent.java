import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class MouseEvent implements Event {

    private String type;
    private long when;
    private int x;
    private int y;

    public MouseEvent(NativeMouseEvent nativeMouseEvent, String type){
        this.type = type;
        this.when = nativeMouseEvent.getWhen();
        this.x = nativeMouseEvent.getX();
        this.y = nativeMouseEvent.getY();
    }

    public String toCSV() {
        return when + "," + type + "," + x + "," + y;
    }


    public String type() {
        return type;
    }
}
