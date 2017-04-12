import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public interface EventFactory<K, M> {

    KeyEvent createKeyEvent(K event);

    MouseEvent createMouseEvent(M event);

}
