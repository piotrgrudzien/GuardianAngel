import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/11/17.
 */
public interface EventHandler {

    void handleKeyTyped(NativeKeyEvent nativeKeyEvent);

    void handleMouseClicked(NativeMouseEvent nativeMouseEvent);

    void setDataBaseWriter(DatabaseWriter dataBaseWriter);
}
