import java.io.IOException;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class EventListenerTest {
    public static void main(String[] args) throws IOException {
        EventListener myEventListener = new BackgroundEventListener(new BackgroundManagerFactory(), new BackgroundEventFactory());
    }
}
