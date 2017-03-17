import java.util.logging.Logger;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class LocalEventListener implements EventListener<String, String> {

    private static final Logger LOGGER = Logger.getLogger(BackgroundEventListener.class.getName());
    private EventHandler eventHandler;
    private EventFactory<String, String> eventFactory;

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setEventFactory(EventFactory<String, String> eventFactory) {
        this.eventFactory = eventFactory;
    }

    public LocalEventListener(ManagerFactory managerFactory, EventFactory<String, String> eventFactory) {
        setEventHandler(managerFactory.createEventHandler());
        setEventFactory(eventFactory);
        eventHandler.setDataBaseWriter(managerFactory.createDatabaseWriter());
    }

}
