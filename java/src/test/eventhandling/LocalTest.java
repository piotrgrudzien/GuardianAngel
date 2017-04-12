import java.io.IOException;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class LocalTest {
    public static void main(String[] args) throws IOException {
        DatabaseReader myEventListener = new LocalEventListener(new LocalManagerFactory(), new LocalEventFactory());
        myEventListener.readFile("train.txt");
    }
}
