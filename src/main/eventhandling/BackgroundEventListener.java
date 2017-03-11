import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import java.awt.event.*;

/**
 * Created by piotrgrudzien on 3/10/17.
 */
public class BackgroundEventListener implements EventListener, NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

    private EventHandler eventHandler;

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public BackgroundEventListener(EventHandlerFactory eventHandlerFactory) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.out.println(e.getCode());
        }
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
        GlobalScreen.addNativeMouseWheelListener(this);
        this.setEventHandler(eventHandlerFactory.createEventHandler());
        this.eventHandler.setDataBaseWriter(eventHandlerFactory.createDatabaseWriter());
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        this.eventHandler.handleKeyTyped(nativeKeyEvent);
    }

    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        this.eventHandler.handleMouseClicked(nativeMouseEvent);
    }

    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
    }

    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

    }

    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {

    }

}
