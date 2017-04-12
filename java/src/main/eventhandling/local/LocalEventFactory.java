/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class LocalEventFactory implements EventFactory<String[], String[]> {

    public KeyEvent createKeyEvent(String[] keyEvent) {
        return new KeyEvent(keyEvent);
    }

    public MouseEvent createMouseEvent(String[] mouseEvent) {
        return new MouseEvent(mouseEvent);
    }
}
