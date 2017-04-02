/**
 * Created by piotrgrudzien on 3/11/17.
 */
public class LocalEventHandler implements EventHandler {

    private DatabaseWriter dataBaseWriter;
    private Model model;

    public void setDataBaseWriter(DatabaseWriter dataBaseWriter) {
        this.dataBaseWriter = dataBaseWriter;
    }

    public void setModel(Model model) {
        this.model = model;
        model.setLookup(new Lookup());
    }

    public void handleEvent(Event event) {
//        TODO the real magic has to happen here, the event will only provide some params
        int index;
        // update model on KEY_PRESSED, KEY_RELEASED and MOUSE_CLICKED
        // predict on KEY_TYPED - always happens 0 ms after KEY_PRESSED
        if(event.type().equals(EventType.KEY_PRESSED) || event.type().equals(EventType.KEY_RELEASED)) {
            index = model.getLookup().updateInputDictionariesOnKeyEvent((KeyEvent)event);
        } else if(event.type().equals(EventType.MOUSE_CLICKED)) {
            index = model.getLookup().updateInputDictionariesOnMouseEvent((MouseEvent)event);
        } else if(event.type().equals(EventType.KEY_TYPED)) {
            index = model.getLookup().updateOutputDictionariesOnKeyEvent((KeyEvent)event);
        } else {
            return;
        }
        model.feedEvent(event.getWhen(), index, event.type());
//        System.out.println("Input size " + lookup.inputSize() + " Output size " + lookup.outputSize());
    }

    public void printResults() {
        model.printResults();
    }

}
