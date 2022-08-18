package com.bifrost3d.launcher.window;

import com.bifrost3d.core.window.EKey;
import com.bifrost3d.core.window.IKeyboard;
import static org.lwjgl.glfw.GLFW.*;

public class KeyboardGLFW  implements IKeyboard {

    public void update(long window, int key, int scancode, int action, int mods) {
        EKey eKey = KeyMapGLFW.map[key];
        if (eKey != null) {
            if (action == GLFW_PRESS) {
                current[eKey.ordinal()] = true;
            }
            else if (action == GLFW_RELEASE) {
                current[eKey.ordinal()] = false;
            }
        }
    }

    public void swap () {
        System.arraycopy(this.current, 0, this.prev, 0, this.current.length);
    }

    @Override
    public boolean isDown(EKey key) {
        return current[key.ordinal()];
    }

    @Override
    public boolean isUp(EKey key) {
        return !current[key.ordinal()];
    }

    @Override
    public boolean isPressed(EKey key) {
        return current[key.ordinal()] && !prev[key.ordinal()];
    }

    @Override
    public boolean isReleased(EKey key) {
        return !current[key.ordinal()] && prev[key.ordinal()];
    }

    private final boolean[] current = new boolean[EKey.values().length];
    private final boolean[] prev = new boolean[EKey.values().length];
}
