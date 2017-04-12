/**
 * Created by piotrgrudzien on 3/12/17.
 */
public interface Event {

    long getWhen();

    String toCSV();

    String type();

    String provideSomeMLparams();

}
