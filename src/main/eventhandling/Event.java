/**
 * Created by piotrgrudzien on 3/12/17.
 */
public interface Event {

    String toCSV();

    String type();

    void provideSomeMLparams();
}
