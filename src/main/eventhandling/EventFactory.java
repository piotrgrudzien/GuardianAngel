import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public interface EventFactory {

    KeyEvent createKeyEvent(NativeKeyEvent nativeKeyEvent, String type);

    MouseEvent createMouseEvent(NativeMouseEvent nativeMouseEvent, String type);

}
