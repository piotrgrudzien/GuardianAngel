/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class LocalEventHandler implements EventHandler {

    private DatabaseWriter dataBaseWriter;
    private Lookup lookup;
    private Model model;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        this.dataBaseWriter = dataBaseWriter;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void handleEvent(Event event) {
//        TODO the real magic has to happen here, the event will only provide some params
        int index;
        // update model on KEY_PRESSED, KEY_RELEASED and MOUSE_CLICKED
        // predict on KEY_TYPED - always happens 0 ms after KEY_PRESSED
        if(event.type().equals(EventType.KEY_PRESSED) || event.type().equals(EventType.KEY_RELEASED)) {
            index = lookup.updateInputDictionariesOnKeyEvent((KeyEvent)event);
        } else if(event.type().equals(EventType.MOUSE_CLICKED)) {
            index = lookup.updateInputDictionariesOnMouseEvent((MouseEvent)event);
        } else if(event.type().equals(EventType.KEY_TYPED)) {
            index = lookup.updateOutputDictionariesOnKeyEvent((KeyEvent)event);
        } else {
            return;
        }
        model.feedEvent(event.getWhen(), index, event.type());
    }

}
