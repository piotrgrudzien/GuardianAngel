import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public interface EventFactory<K, M> {

    KeyEvent createKeyEvent(K event, String type);

    MouseEvent createMouseEvent(M event, String type);

}
