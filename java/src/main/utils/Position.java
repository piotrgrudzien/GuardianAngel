/**
 * Created by piotrgrudzien on 3/26/17.
 */
public class Position {

    private int x;

    private int y;

    public Position(int x, int y) {
        this.x = x / 100;
        this.y = y / 100;
    }

    public boolean equals(Object o) {
        if (o instanceof Position) {
            return (this.x == ((Position)o).x) && (this.y == ((Position)o).y);
        }
        return false;
    }

    public int hashCode() {
        return 100 * this.x + this.y;
    }
}
