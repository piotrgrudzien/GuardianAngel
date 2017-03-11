/**
 * Created by piotrgrudzien on 3/11/17.
 */
public interface EventHandlerFactory {

    EventHandler createEventHandler();

    DatabaseWriter createDatabaseWriter();
}
