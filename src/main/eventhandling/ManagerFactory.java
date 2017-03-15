/**
 * Created by piotrgrudzien on 3/11/17.
 */
public interface ManagerFactory {

    EventHandler createEventHandler();

    DatabaseWriter createDatabaseWriter();
}
