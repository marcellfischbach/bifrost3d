package com.bifrost3d.launcher.window;

import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.window.IKeyboard;
import com.bifrost3d.core.window.IMouse;
import com.bifrost3d.core.window.IWindow;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;


public class WindowGLFW implements IWindow {

    private int width;
    private int height;
    private String title = "Crossengine";
    private long wnd;

    private final KeyboardGLFW keyboard = new KeyboardGLFW();
    private MouseGLFW mouse;

    private boolean vSync = false;

    public void initialize() {

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RED_BITS, 8);
        glfwWindowHint(GLFW_GREEN_BITS, 8);
        glfwWindowHint(GLFW_BLUE_BITS, 8);
        glfwWindowHint(GLFW_ALPHA_BITS, 8);
        glfwWindowHint(GLFW_SAMPLES, 16);


        this.wnd = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
        this.mouse = new MouseGLFW(this.wnd, this);

        glfwSetWindowPos(this.wnd, 1, 31);

        ObjectRegistry.register(IKeyboard.class, this.keyboard);
        ObjectRegistry.register(IMouse.class, this.mouse);

        glfwSetKeyCallback(this.wnd, this.keyboard::update);
        glfwSetMouseButtonCallback(this.wnd, this.mouse::update);
        glfwSetCursorPosCallback(this.wnd, this.mouse::update);

        glfwMakeContextCurrent(this.wnd);

        if (vSync) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }

    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setTitle(String title) {
        if (!Objects.equals(this.title, title)) {
            this.title = title;
            if (this.wnd != 0L) {
                glfwSetWindowTitle(this.wnd, this.title);
            }
        }
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public boolean isVSync() {
        return vSync;
    }

    @Override
    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    @Override
    public void handleEvents() {
        this.keyboard.swap();
        this.mouse.swap();
        glfwPollEvents();
    }

    @Override
    public void swap() {
        glfwSwapBuffers(this.wnd);
    }

    @Override
    public KeyboardGLFW getKeyboard() {
        return keyboard;
    }
}
