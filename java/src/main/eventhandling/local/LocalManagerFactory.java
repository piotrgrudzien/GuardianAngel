/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class LocalManagerFactory implements ManagerFactory {

    public EventHandler createEventHandler() {
        return new LocalEventHandler();
    }

    public DatabaseWriter createDatabaseWriter() {
        return new LocalEventWriter();
    }

}
