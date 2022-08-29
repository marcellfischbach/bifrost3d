package com.bifrost3d.launcher.window;

import com.bifrost3d.core.window.EMouseButton;

import static org.lwjgl.glfw.GLFW.*;

public abstract class MouseButtonMapGLFW {
    private MouseButtonMapGLFW() {
    }

    protected static final EMouseButton[] map = new EMouseButton[GLFW_MOUSE_BUTTON_LAST + 1];

    static {
        register(GLFW_MOUSE_BUTTON_LEFT, EMouseButton.MB_LEFT);
        register(GLFW_MOUSE_BUTTON_MIDDLE, EMouseButton.MB_MIDDLE);
        register(GLFW_MOUSE_BUTTON_RIGHT, EMouseButton.MB_RIGHT);
    }



    private static void register(int button, EMouseButton eButton) {
        map[button] = eButton;
    }
}
