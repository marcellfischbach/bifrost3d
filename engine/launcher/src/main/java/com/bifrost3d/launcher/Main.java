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

        IKeyboard keyboard = ObjectRegistry.get(IKeyboard.class).orElseThrow(NullPointerException::new);
        IMouse mouse = ObjectRegistry.get(IMouse.class).orElseThrow(NullPointerException::new);
        IDevice device = ObjectRegistry.get(IDevice.class).orElseThrow(NullPointerException::new);
        IWindow window = ObjectRegistry.get(IWindow.class).orElseThrow(NullPointerException::new);

        boolean running = true;
        while (running) {
            try {
                Thread.sleep(10L);
            } catch (Exception e) {
                //
            }
            window.handleEvents();

            device.clear(true, new ColorRGBA(0.5f, 0.0f, 0.0f, 1.0f), true, 1.0f, true, 0);
            window.swap();


            if (mouse.isDown(EMouseButton.MB_Right)) {
                mouse.setCursorMode(ECursorMode.Locked);
            } else {
                mouse.setCursorMode(ECursorMode.Normal);
            }
            if (keyboard.isPressed(EKey.K_Escape)) {
                running = false;
            }
        }

    }
}
