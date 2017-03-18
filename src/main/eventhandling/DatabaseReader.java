/**
 * Created by piotrgrudzien on 3/17/17.
 */
public interface DatabaseReader {

    void readFile(String fileName);

    void setMLFactory(MLFactory mlFactory);
}
