import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class BackgroundEventFactory implements EventFactory<NativeKeyEvent, NativeMouseEvent> {

    public KeyEvent createKeyEvent(NativeKeyEvent nativeKeyEvent) {
        return new KeyEvent(nativeKeyEvent);
    }

    public MouseEvent createMouseEvent(NativeMouseEvent nativeMouseEvent) {
        return new MouseEvent(nativeMouseEvent);
    }
}
