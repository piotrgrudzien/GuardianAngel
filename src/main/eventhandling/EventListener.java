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
public class EventListener implements NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

    public EventListener() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.out.println(e.getCode());
        }
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
        GlobalScreen.addNativeMouseWheelListener(this);
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        System.out.println(nativeKeyEvent.paramString());
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        System.out.println(nativeKeyEvent.paramString());
    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        System.out.println(nativeKeyEvent.paramString());
    }

    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        System.out.println(nativeMouseEvent.paramString());
    }

    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        System.out.println(nativeMouseEvent.paramString());
    }

    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        System.out.println(nativeMouseEvent.paramString());
    }

    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        System.out.println(nativeMouseEvent.paramString());
    }

    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        System.out.println(nativeMouseEvent.paramString());
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        System.out.println(nativeMouseWheelEvent.paramString());
    }
}
