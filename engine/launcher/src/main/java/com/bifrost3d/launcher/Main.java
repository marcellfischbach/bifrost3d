package com.bifrost3d.launcher;

import com.bifrost3d.core.Engine;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.IDevice;
import com.bifrost3d.core.window.*;
import com.bifrost3d.math.ColorRGBA;


public class Main {


    public static void main(String[] args) {
        Engine engine = Engine.create();
        engine.initializeModules();

        IKeyboard keyboard = ObjectRegistry.get(IKeyboard.class).orElse(null);
        IMouse mouse = ObjectRegistry.get(IMouse.class).orElse(null);
        IDevice device = ObjectRegistry.get(IDevice.class).orElse(null);
        IWindow window = ObjectRegistry.get(IWindow.class).orElse(null);

        while (true) {
            try {
                Thread.sleep(10L);
            } catch (Exception e) {
                //
            }
            window.handleEvents();

            device.clear(true, new ColorRGBA(0.5f, 0.0f, 0.0f, 1.0f), true, 1.0f, true, 0);
            window.swap();

//            System.out.printf(" %.2f %.2f - %.2f %.2f\n", mouse.getX(), mouse.getY(), mouse.getDeltaX(), mouse.getDeltaY());


            if (mouse.isDown(EMouseButton.MB_Right)) {
                mouse.setCursorMode(ECursorMode.Locked);
            } else {
                mouse.setCursorMode(ECursorMode.Normal);
            }
            if (keyboard.isPressed(EKey.K_Escape)) {
                break;
            }
        }

    }
}
