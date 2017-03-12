import java.io.IOException;

/**
 * Created by piotrgrudzien on 3/12/17.
 */
public class App {
    public static void main(String[] args) throws IOException {
        EventListener myEventListener = new BackgroundEventListener(new StoreEventHandlerFactory());
    }
}
