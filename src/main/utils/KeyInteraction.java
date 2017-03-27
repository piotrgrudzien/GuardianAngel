/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class KeyInteraction {

    private String eventType;
    private int rawCode;
    private String keyChar;

    public KeyInteraction(String eventType, int rawCode, String keyChar) {
        this.eventType = eventType;
        this.rawCode = rawCode;
        this.keyChar = keyChar;
    }

    public boolean equals(Object o) {
        if (o instanceof KeyInteraction) {
            return (this.eventType.equals(((KeyInteraction) o).eventType)) &&
                    (this.rawCode == ((KeyInteraction)o).rawCode) &&
                    (this.keyChar.equals(((KeyInteraction)o).keyChar));
        }
        return false;
    }

    public int hashCode() {
        return this.eventType.hashCode() + this.rawCode + this.keyChar.hashCode();
    }
}
