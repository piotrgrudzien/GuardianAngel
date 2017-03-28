import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class Lookup {

    // translates code to the actual string (character, usually)
    private Map<KeyInteraction, String> codeToString;
    // map code or position to indexes of input vectors
    private Map<KeyInteraction, Integer> inCodeToIndex;
    private Map<Position, Integer> positionToIndex;
    // map code to indexes of output vectors
    private Map<KeyInteraction, Integer> outCodeToIndex;
    // keep track of input-output vector length
    private int inVecLength, outVecLength;

    public Lookup() {
        codeToString = new HashMap<>();
        inCodeToIndex = new HashMap<>();
        positionToIndex = new HashMap<>();
        outCodeToIndex = new HashMap<>();
        // leave index 0 for time since last event
        inVecLength = 1;
        // time since last event net relevant for output vector
        outVecLength = 0;
    }

    public int updateInputDictionariesOnKeyEvent(KeyEvent event) {
        KeyInteraction keyInteraction = new KeyInteraction(event.type(), event.getRawCode(), event.getKeyChar());
        if(codeToString.containsKey(keyInteraction)) {
            if(!codeToString.get(keyInteraction).equals(event.getKeyChar())) {
                System.out.println("For raw code " + event.getRawCode() +
                " typed dictionary used to hold " + codeToString.get(keyInteraction) +
                        " trying to overwrite with " + event.getKeyChar());
            }
            return inCodeToIndex.get(keyInteraction);
        } else {
            codeToString.put(keyInteraction, event.getKeyChar());
            inCodeToIndex.put(keyInteraction, inVecLength);
            return inVecLength++;
        }
    }

    public int updateInputDictionariesOnMouseEvent(MouseEvent event) {
        Position position = new Position(event.getX(), event.getY());
        if(!positionToIndex.containsKey(position)) {
            positionToIndex.put(position, inVecLength);
            return inVecLength++;
        } else {
            return positionToIndex.get(position);
        }
    }

    public int updateOutputDictionariesOnKeyEvent(KeyEvent event) {
        KeyInteraction keyInteraction = new KeyInteraction(event.type(), event.getRawCode(), event.getKeyChar());
        if(codeToString.containsKey(keyInteraction)) {
            if(!codeToString.get(keyInteraction).equals(event.getKeyChar())) {
                System.out.println("For raw code " + event.getRawCode() +
                        " typed dictionary used to hold " + codeToString.get(keyInteraction) +
                        " trying to overwrite with " + event.getKeyChar());
            }
            return outCodeToIndex.get(keyInteraction);
        } else {
            codeToString.put(keyInteraction, event.getKeyChar());
            outCodeToIndex.put(keyInteraction, outVecLength);
            return outVecLength++;
        }
    }

}
