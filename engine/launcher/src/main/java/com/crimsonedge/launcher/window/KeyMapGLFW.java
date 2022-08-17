package com.crimsonedge.launcher.window;

import com.crimsonedge.core.window.EKey;

import static org.lwjgl.glfw.GLFW.*;

public abstract class KeyMapGLFW {
    private KeyMapGLFW() {
    }

    public static final EKey[] map = new EKey[GLFW_KEY_LAST + 1];

    static {
        register(GLFW_KEY_A, EKey.K_A);
        register(GLFW_KEY_B, EKey.K_B);
        register(GLFW_KEY_C, EKey.K_C);
        register(GLFW_KEY_D, EKey.K_D);
        register(GLFW_KEY_E, EKey.K_E);
        register(GLFW_KEY_F, EKey.K_F);
        register(GLFW_KEY_G, EKey.K_G);
        register(GLFW_KEY_H, EKey.K_H);
        register(GLFW_KEY_I, EKey.K_I);
        register(GLFW_KEY_J, EKey.K_J);
        register(GLFW_KEY_K, EKey.K_K);
        register(GLFW_KEY_L, EKey.K_L);
        register(GLFW_KEY_M, EKey.K_M);
        register(GLFW_KEY_N, EKey.K_N);
        register(GLFW_KEY_O, EKey.K_O);
        register(GLFW_KEY_P, EKey.K_P);
        register(GLFW_KEY_Q, EKey.K_Q);
        register(GLFW_KEY_R, EKey.K_R);
        register(GLFW_KEY_S, EKey.K_S);
        register(GLFW_KEY_T, EKey.K_T);
        register(GLFW_KEY_U, EKey.K_U);
        register(GLFW_KEY_V, EKey.K_V);
        register(GLFW_KEY_W, EKey.K_W);
        register(GLFW_KEY_X, EKey.K_X);
        register(GLFW_KEY_Y, EKey.K_Y);
        register(GLFW_KEY_Z, EKey.K_Z);
        register(GLFW_KEY_1, EKey.K_1);
        register(GLFW_KEY_2, EKey.K_2);
        register(GLFW_KEY_3, EKey.K_3);
        register(GLFW_KEY_4, EKey.K_4);
        register(GLFW_KEY_5, EKey.K_5);
        register(GLFW_KEY_6, EKey.K_6);
        register(GLFW_KEY_7, EKey.K_7);
        register(GLFW_KEY_8, EKey.K_8);
        register(GLFW_KEY_9, EKey.K_9);
        register(GLFW_KEY_0, EKey.K_0);
        register(GLFW_KEY_LEFT_CONTROL, EKey.K_LeftControl);
        register(GLFW_KEY_RIGHT_CONTROL, EKey.K_RightControl);
        register(GLFW_KEY_LEFT_SHIFT, EKey.K_LeftShift);
        register(GLFW_KEY_RIGHT_SHIFT, EKey.K_RightShift);
        register(GLFW_KEY_ESCAPE, EKey.K_Escape);
        register(GLFW_KEY_TAB, EKey.K_Tab);
        register(GLFW_KEY_UP,  EKey.K_Up);
    }



    private static void register(int key, EKey eKey) {
        map[key] = eKey;
    }
}
