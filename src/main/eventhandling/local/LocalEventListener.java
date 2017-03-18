import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class LocalEventListener implements EventListener<String[], String[]>, DatabaseReader {

    private static final Logger LOGGER = Logger.getLogger(BackgroundEventListener.class.getName());
    private EventHandler eventHandler;
    private EventFactory<String[], String[]> eventFactory;
    private MLFactory mlFactory;

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setEventFactory(EventFactory<String[], String[]> eventFactory) {
        this.eventFactory = eventFactory;
    }

    public void setMLFactory(MLFactory mlFactory) {
        this.mlFactory = mlFactory;
    }

    public LocalEventListener(ManagerFactory managerFactory, EventFactory<String[], String[]> eventFactory) {
        setEventHandler(managerFactory.createEventHandler());
        setEventFactory(eventFactory);
        eventHandler.setDataBaseWriter(managerFactory.createDatabaseWriter());
    }

    public void readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(new File(MyPaths.DATABASE + fileName));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] csvLine = line.split(",");
                if(EventTypeParser.isKeyEvent(csvLine)) {
                    eventHandler.handleEvent(eventFactory.createKeyEvent(csvLine));
                } else if(EventTypeParser.isMouseEvent(csvLine)) {
                    eventHandler.handleEvent(eventFactory.createMouseEvent(csvLine));
                } else {
                    System.out.println("Event of unknown type: " + line);
                }
            }
            fileReader.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}
