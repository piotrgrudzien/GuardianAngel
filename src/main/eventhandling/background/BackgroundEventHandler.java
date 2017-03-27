/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class BackgroundEventHandler implements EventHandler {

    private DatabaseWriter databaseWriter;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        databaseWriter = dataBaseWriter;
    }

    public void handleEvent(Event event) {
        databaseWriter.writeEvent(event);
    }

    public void setLookup(Lookup lookup) {};

    public void setModel(Model model) {};

}
