package com.crimsonedge.launcher.window;

import com.crimsonedge.core.window.EMouseButton;

import static org.lwjgl.glfw.GLFW.*;

public abstract class MouseButtonMapGLFW {
    private MouseButtonMapGLFW() {
    }

    public static final EMouseButton[] map = new EMouseButton[GLFW_MOUSE_BUTTON_LAST + 1];

    static {
        register(GLFW_MOUSE_BUTTON_LEFT, EMouseButton.MB_Left);
        register(GLFW_MOUSE_BUTTON_MIDDLE, EMouseButton.MB_Middle);
        register(GLFW_MOUSE_BUTTON_RIGHT, EMouseButton.MB_Right);
    }



    private static void register(int button, EMouseButton eButton) {
        map[button] = eButton;
    }
}
