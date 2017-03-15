import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class BackgroundEventFactory implements EventFactory<NativeKeyEvent, NativeMouseEvent> {

    public KeyEvent createKeyEvent(NativeKeyEvent nativeKeyEvent, String type) {
        return new KeyEvent(nativeKeyEvent, type);
    }

    public MouseEvent createMouseEvent(NativeMouseEvent nativeMouseEvent, String type) {
        return new MouseEvent(nativeMouseEvent, type);
    }
}
