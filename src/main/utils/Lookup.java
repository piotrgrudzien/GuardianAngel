import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class Lookup {

    private Map<KeyInteraction, String> codeToString;
    private Map<Integer, String> indexToString;
    private Map<KeyInteraction, Integer> codeToIndex;
    private int dimension;

    public Lookup() {
        codeToString = new HashMap<>();
        indexToString = new HashMap<>();
        codeToIndex = new HashMap<>();
        dimension = 0;
    }

    public int inputSize() {
        // index zero for time since last event
        return dimension + 1;
    }

    public int outputSize() {
        return dimension;
    }

    public int updateDictionary(KeyEvent event) {
        KeyInteraction keyInteraction = new KeyInteraction(event.type(), event.getRawCode(), event.getKeyChar());
        if(codeToString.containsKey(keyInteraction)) {
            if(!codeToString.get(keyInteraction).equals(event.getKeyChar())) {
                System.out.println("For raw code " + event.getRawCode() +
                " typed dictionary used to hold " + codeToString.get(keyInteraction) +
                        " trying to overwrite with " + event.getKeyChar());
            }
            return codeToIndex.get(keyInteraction);
        } else {
            codeToString.put(keyInteraction, event.getKeyChar());
            codeToIndex.put(keyInteraction, dimension);
            indexToString.put(dimension, event.getKeyChar());
            return dimension++;
        }
    }

    public String getStringFromInputIndex(int index) {
        return indexToString.get(index - 1);
    }

    public String getStringFromOutputIndex(int index) {
        return indexToString.get(index);
    }

    public Map<Integer, String> getIndexToStringMap() {
        return indexToString;
    }

    public Map<KeyInteraction, Integer> getCodeToIndex() {
        return codeToIndex;
    }

}
