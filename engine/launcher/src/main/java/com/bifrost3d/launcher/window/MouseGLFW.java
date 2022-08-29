package com.bifrost3d.launcher.window;

import com.bifrost3d.math.Mathf;
import com.bifrost3d.core.window.ECursorMode;
import com.bifrost3d.core.window.EMouseButton;
import com.bifrost3d.core.window.IMouse;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;

public class MouseGLFW implements IMouse {


    private double x;
    private double y;
    private double realX;
    private double realY;
    private double deltaX;
    private double deltaY;
    private double prevX;
    private double prevY;
    private double prevRealX;
    private double prevRealY;
    private final long wnd;

    private final boolean[] current = new boolean[EMouseButton.values().length];
    private final boolean[] prev = new boolean[EMouseButton.values().length];

    private ECursorMode mode = ECursorMode.NORMAL;

    private final WindowGLFW window;

    public MouseGLFW(long wnd, WindowGLFW window) {
        this.wnd = wnd;
        this.window = window;
    }

    public void swap() {
        swapPositions();
        swapButtons();
    }

    private void swapPositions() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevRealX = this.realX;
        this.prevRealY = this.realY;
        this.deltaX = 0.0;
        this.deltaY = 0.0;
    }

    private void swapButtons() {
        System.arraycopy(this.current, 0, this.prev, 0, this.current.length);
    }

    @SuppressWarnings("unused")
    public void update(long window, int button, int action, int mods) {
        EMouseButton eButton = MouseButtonMapGLFW.map[button];
        if (eButton != null) {
            if (action == GLFW_PRESS) {
                current[eButton.ordinal()] = true;
            } else if (action == GLFW_RELEASE) {
                current[eButton.ordinal()] = false;
            }
        }
    }

    @SuppressWarnings("unused")
    public void update(long window, double xpos, double ypos) {
        this.realX = xpos;
        this.realY = ypos;

        if (this.mode == ECursorMode.NORMAL) {
            this.x = xpos;
            this.y = ypos;
            this.deltaX = this.realX - this.prevRealX;
            this.deltaY = this.realY - this.prevRealY;
        } else if (this.mode == ECursorMode.LOCKED) {
            this.deltaX = this.realX - this.prevRealX;
            this.deltaY = this.realY - this.prevRealY;
            this.x = this.prevX + this.deltaX;
            this.y = this.prevY + this.deltaY;
            this.x = Mathf.clamp(this.x, 0.0, this.window.getWidth());
            this.y = Mathf.clamp(this.y, 0.0, this.window.getHeight());
        }
    }

    @Override
    public void setCursorMode(ECursorMode mode) {
        if (mode == null || Objects.equals(this.mode, mode)) {
            return;
        }

        this.mode = mode;
        if (this.mode == ECursorMode.NORMAL) {
            glfwSetInputMode(this.wnd, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        } else if (this.mode == ECursorMode.LOCKED) {
            glfwSetInputMode(this.wnd, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        }

        glfwSetCursorPos(this.wnd, window.getWidth() / 2.0, window.getHeight() / 2.0);
        swapPositions();

    }

    @Override
    public ECursorMode getCursorMode() {
        return null;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getDeltaX() {
        return this.deltaX;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getDeltaY() {
        return this.deltaY;
    }

    @Override
    public boolean isDown(EMouseButton button) {
        return this.current[button.ordinal()];
    }

    @Override
    public boolean isUp(EMouseButton button) {
        return !this.current[button.ordinal()];
    }

    @Override
    public boolean isPressed(EMouseButton button) {
        return this.current[button.ordinal()] && !this.prev[button.ordinal()];
    }

    @Override
    public boolean isReleased(EMouseButton button) {
        return !this.current[button.ordinal()] && this.prev[button.ordinal()];
    }

}
