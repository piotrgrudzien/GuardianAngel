import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class Lookup {

    // translates code to the actual string (character, usually)
    private Map<KeyInteraction, String> codeToString;
    // map code or position to indexes is input-output vectors
    private Map<KeyInteraction, Integer> codeToIndex;
    private Map<Position, Integer> positionToIndex;
    // keep track of input-output vector length
    private int vecLength;

    public Lookup() {
        codeToString = new HashMap<>();
        codeToIndex = new HashMap<>();
        positionToIndex = new HashMap<>();
        vecLength = 0;
    }

    public void updateDictionariesOnKeyEvent(KeyEvent event) {
        KeyInteraction keyInteraction = new KeyInteraction(event.type(), event.getRawCode(), event.getKeyChar());
        if(codeToString.containsKey(keyInteraction)) {
            if(!codeToString.get(keyInteraction).equals(event.getKeyChar())) {
                System.out.println("For raw code " + event.getRawCode() +
                " typed dictionary used to hold " + codeToString.get(keyInteraction) +
                        " trying to overwrite with " + event.getKeyChar());
            }
        } else {
            System.out.println("Code " + event.getRawCode() + " String " + event.getKeyChar() + " at index " + vecLength);
            codeToString.put(keyInteraction, event.getKeyChar());
            codeToIndex.put(keyInteraction, vecLength++);
        }
    }

    public void updateDictionariesOnMouseEvent(MouseEvent event) {
        Position position = new Position(event.getX(), event.getY());
        if(!positionToIndex.containsKey(position)) {
            System.out.println("X " + event.getX() + " Y " + event.getY() + " at index " + vecLength);
            positionToIndex.put(position, vecLength++);
        }
    }

}
