import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/11/17.
 */
public interface EventHandler {

    void handleKeyPressed(NativeKeyEvent nativeKeyEvent);

    void handleKeyReleased(NativeKeyEvent nativeKeyEvent);

    void handleKeyTyped(NativeKeyEvent nativeKeyEvent);

    void handleMousePressed(NativeMouseEvent nativeMouseEvent);

    void handleMouseReleased(NativeMouseEvent nativeMouseEvent);

    void handleMouseClicked(NativeMouseEvent nativeMouseEvent);

    void handleMouseMoved(NativeMouseEvent nativeMouseEvent);

    void handleMouseDragged(NativeMouseEvent nativeMouseEvent);

    void setDataBaseWriter(DatabaseWriter dataBaseWriter);
}
