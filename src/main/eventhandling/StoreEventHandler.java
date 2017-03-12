import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class StoreEventHandler implements EventHandler {

    private DatabaseWriter databaseWriter;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        databaseWriter = dataBaseWriter;
    }

    public void handleKeyPressed(NativeKeyEvent nativeKeyEvent) {
        databaseWriter.writeKeyPressed(nativeKeyEvent);
    }

    public void handleKeyReleased(NativeKeyEvent nativeKeyEvent) {
        databaseWriter.writeKeyReleased(nativeKeyEvent);
    }

    public void handleKeyTyped(NativeKeyEvent nativeKeyEvent) {
        databaseWriter.writeKeyTyped(nativeKeyEvent);
    }

    public void handleMousePressed(NativeMouseEvent nativeMouseEvent) {
        databaseWriter.writeMousePressed(nativeMouseEvent);
    }

    public void handleMouseReleased(NativeMouseEvent nativeMouseEvent) {
        databaseWriter.writeMouseReleased(nativeMouseEvent);
    }

    public void handleMouseClicked(NativeMouseEvent nativeMouseEvent) {
        this.databaseWriter.writeMouseClicked(nativeMouseEvent);
    }

    public void handleMouseMoved(NativeMouseEvent nativeMouseEvent) {
        databaseWriter.writeMouseMoved(nativeMouseEvent);
    }

    public void handleMouseDragged(NativeMouseEvent nativeMouseEvent) {
        databaseWriter.writeMouseDragged(nativeMouseEvent);
    }

}
