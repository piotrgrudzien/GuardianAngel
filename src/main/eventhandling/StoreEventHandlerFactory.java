/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class StoreEventHandlerFactory implements EventHandlerFactory {

    public EventHandler createEventHandler() {
        return new StoreEventHandler();
    }

    public DatabaseWriter createDatabaseWriter() {
        return new SimpleEventWriter();
    }
}
