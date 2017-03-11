import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class StoreEventHandler implements EventHandler {

    private DatabaseWriter databaseWriter;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        this.databaseWriter = dataBaseWriter;
    }

    public void handleKeyTyped(NativeKeyEvent nativeKeyEvent) {
        this.databaseWriter.writeKeyTyped(nativeKeyEvent);
    }

    public void handleMouseClicked(NativeMouseEvent nativeMouseEvent) {
        this.databaseWriter.writeMouseClicked(nativeMouseEvent);
    }

}
