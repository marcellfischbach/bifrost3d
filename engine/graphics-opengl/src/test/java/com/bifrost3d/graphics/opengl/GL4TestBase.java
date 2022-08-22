package com.bifrost3d.graphics.opengl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL44;

import static org.lwjgl.glfw.GLFW.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class GL4TestBase {

    private long wnd;

    protected GL4TestBase() {

    }

    @BeforeAll
    public void setupOpenGLContext() {
        glfwInit();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RED_BITS, 8);
        glfwWindowHint(GLFW_GREEN_BITS, 8);
        glfwWindowHint(GLFW_BLUE_BITS, 8);
        glfwWindowHint(GLFW_ALPHA_BITS, 8);
        this.wnd = glfwCreateWindow(1024, 768, "", 0, 0);
        glfwMakeContextCurrent(this.wnd);
        GL.createCapabilities();
    }

    @BeforeEach
    public void makeContextCurrent() {
        glfwMakeContextCurrent(this.wnd);
    }

    @AfterAll
    public void tearDownOpenGLContext() {
        glfwDestroyWindow(this.wnd);
        this.wnd = 0;
    }
}