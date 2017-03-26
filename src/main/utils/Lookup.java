import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class Lookup {

    private Map<Integer, String> typedDictionary;
    private Map<Position, Integer> positionDictionary;
    private Integer positionIndex;

    public Lookup() {
        typedDictionary = new HashMap<>();
        positionDictionary = new HashMap<>();
        positionIndex = -1;
    }

    public void updateDictionariesOnKeyEvent(KeyEvent event) {
        if(typedDictionary.containsKey(event.getRawCode())) {
            if(!typedDictionary.get(event.getRawCode()).equals(event.getKeyChar())) {
                System.out.println("For raw code " + event.getRawCode() +
                " typed dictionary used to hold " + typedDictionary.get(event.getRawCode()) +
                        " trying to overwrite with " + event.getKeyChar());
            }
        } else {
            typedDictionary.put(event.getRawCode(), event.getKeyChar());
        }
    }

    public void updateDictionariesOnMouseEvent(MouseEvent event) {
        Position position = new Position(event.getX(), event.getY());
        if(!positionDictionary.containsKey(position)) {
            positionDictionary.put(position, positionIndex--);
        }
    }

}
