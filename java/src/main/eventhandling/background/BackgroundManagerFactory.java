/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class BackgroundManagerFactory implements ManagerFactory {

    public EventHandler createEventHandler() {
        return new BackgroundEventHandler();
    }

    public DatabaseWriter createDatabaseWriter() {
        return new BackgroundEventWriter();
    }
}
