/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class LocalEventHandler implements EventHandler {

    private DatabaseWriter dataBaseWriter;
    private Lookup lookup;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        this.dataBaseWriter = dataBaseWriter;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public void handleEvent(Event event) {
//        TODO the real magic has to happen here, the event will only provide some params
        if(event.type().equals(EventType.KEY_TYPED) || event.type().equals(EventType.KEY_PRESSED)) {
            lookup.updateDictionariesOnKeyEvent((KeyEvent)event);
        } else if(event.type().equals(EventType.MOUSE_CLICKED)) {
            lookup.updateDictionariesOnMouseEvent((MouseEvent)event);
        }
    }

}
