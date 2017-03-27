import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/11/17.
 */
public interface EventHandler {

    void handleEvent(Event event);

    void setDataBaseWriter(DatabaseWriter dataBaseWriter);

    void setLookup(Lookup lookup);

    void setModel(Model model);
}
