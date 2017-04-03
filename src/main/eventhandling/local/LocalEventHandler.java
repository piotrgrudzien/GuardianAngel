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
        // let's look at key typed events only
        if(event.type().equals(EventType.KEY_TYPED)) {
            index = model.getLookup().updateDictionary((KeyEvent) event);
            model.feedEvent(event.getWhen(), index, event.type());
        }
//        System.out.println("Input size " + model.getLookup().inputSize() + " Output size " + model.getLookup().outputSize());
    }

    public void printResults() {
        model.printResults();
    }

}
