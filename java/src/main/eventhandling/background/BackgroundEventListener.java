import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class BackgroundEventListener implements EventListener<NativeKeyEvent, NativeMouseEvent>, NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

    private static final Logger LOGGER = Logger.getLogger(BackgroundEventListener.class.getName());
    private EventHandler eventHandler;
    private EventFactory<NativeKeyEvent, NativeMouseEvent> eventFactory;

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setEventFactory(EventFactory<NativeKeyEvent, NativeMouseEvent> eventFactory) {
        this.eventFactory = eventFactory;
    }

    public BackgroundEventListener(ManagerFactory managerFactory, EventFactory<NativeKeyEvent, NativeMouseEvent> eventFactory) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
        GlobalScreen.addNativeMouseWheelListener(this);
        setEventHandler(managerFactory.createEventHandler());
        setEventFactory(eventFactory);
        eventHandler.setDataBaseWriter(managerFactory.createDatabaseWriter());
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        eventHandler.handleEvent(eventFactory.createKeyEvent(nativeKeyEvent));
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        eventHandler.handleEvent(eventFactory.createKeyEvent(nativeKeyEvent));
    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        eventHandler.handleEvent(eventFactory.createKeyEvent(nativeKeyEvent));
    }

    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        eventHandler.handleEvent(eventFactory.createMouseEvent(nativeMouseEvent));
    }

    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        eventHandler.handleEvent(eventFactory.createMouseEvent(nativeMouseEvent));
    }

    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        eventHandler.handleEvent(eventFactory.createMouseEvent(nativeMouseEvent));
    }

    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        eventHandler.handleEvent(eventFactory.createMouseEvent(nativeMouseEvent));
    }

    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        eventHandler.handleEvent(eventFactory.createMouseEvent(nativeMouseEvent));
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {

    }

}
