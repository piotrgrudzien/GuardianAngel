/**
 * Created by piotrgrudzien on 3/11/17.
 */
public interface EventListener<K, M> {

    void setEventHandler(EventHandler eventHandler);

    void setEventFactory(EventFactory<K, M> eventFactory);

}
