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
public class MyEventListener implements ActionListener, ItemListener, NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener, WindowListener {

    public MyEventListener() {
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

    public static void main(String[] args) {
        MyEventListener myEventListener = new MyEventListener();
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void itemStateChanged(ItemEvent e) {

    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {

    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

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
