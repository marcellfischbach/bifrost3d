package com.crimsonedge.launcher;

import com.crimsonedge.core.ObjectRegistry;
import com.crimsonedge.core.window.*;
import com.crimsonedge.launcher.window.WindowGLFW;
import org.lwjgl.glfw.GLFW;


public class Main {


    public static void main(String[] args) {
        GLFW.glfwInit();

        WindowGLFW window = new WindowGLFW();
        window.setWidth(1024);
        window.setHeight(768);
        window.initialize();
        ObjectRegistry.register(IWindow.class, window);


        IKeyboard keyboard = ObjectRegistry.get(IKeyboard.class).orElse(null);
        IMouse mouse = ObjectRegistry.get(IMouse.class).orElse(null);

        while (true) {
            try {
                Thread.sleep(10L);
            }
            catch (Exception e) {
                //
            }
            window.handleEvents();
            window.swap();

            System.out.printf(" %.2f %.2f - %.2f %.2f\n", mouse.getX(), mouse.getY(), mouse.getDeltaX(), mouse.getDeltaY());


            if (mouse.isDown(EMouseButton.MB_Right)) {
                mouse.setCursorMode(ECursorMode.Locked);
            }
            else {
                mouse.setCursorMode(ECursorMode.Normal);
            }
            if (keyboard.isPressed(EKey.K_Escape)) {
                break;
            }
        }

    }
}
