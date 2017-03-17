/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class LocalEventHandler implements EventHandler {

    private DatabaseWriter databaseWriter;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        databaseWriter = dataBaseWriter;
    }

    public void handleEvent(Event event) {
//        TODO the real magic has to happen here, the event will only provide some params
        event.provideSomeMLparams();
    }

}
