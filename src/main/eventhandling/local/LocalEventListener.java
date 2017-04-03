import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class LocalEventListener implements EventListener<String[], String[]>, DatabaseReader {

    private static final Logger LOGGER = Logger.getLogger(BackgroundEventListener.class.getName());
    private EventHandler eventHandler;
    private EventFactory<String[], String[]> eventFactory;

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setEventFactory(EventFactory<String[], String[]> eventFactory) {
        this.eventFactory = eventFactory;
    }

    LocalEventListener(ManagerFactory managerFactory, EventFactory<String[], String[]> eventFactory) {
        setEventHandler(managerFactory.createEventHandler());
        setEventFactory(eventFactory);
        eventHandler.setDataBaseWriter(managerFactory.createDatabaseWriter());
        eventHandler.setModel(new NaiveBayes());
    }

    public void readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(new File(MyPaths.DATABASE + fileName));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] csvLine = line.split(",");
                lineCount++;
                if(EventTypeParser.isKeyEvent(csvLine)) {
                    eventHandler.handleEvent(eventFactory.createKeyEvent(csvLine));
                } else if(EventTypeParser.isMouseEvent(csvLine)) {
                    eventHandler.handleEvent(eventFactory.createMouseEvent(csvLine));
                } else {
                    System.out.println("Event of unknown type: " + line);
                }
                if(lineCount % 10000 == 0) {
                    System.out.println("Read " + lineCount + " lines");
                }
            }
            System.out.println("Read " + lineCount + " lines");
            eventHandler.printResults();
            System.out.println("Simplest untested Naive Bayes looking at current sequence only:");
            System.out.println("Accuracy 9.31% (7025/75447) Accuracy Top3 17.33% (13074/75447) Accuracy Top5 23.68% (17864/75447)");
            fileReader.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}
