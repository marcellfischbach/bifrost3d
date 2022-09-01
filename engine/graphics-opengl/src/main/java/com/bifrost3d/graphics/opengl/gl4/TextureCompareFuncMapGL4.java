package com.bifrost3d.graphics.opengl.gl4;

import static org.lwjgl.opengl.GL11.*;

public abstract class TextureCompareFuncMapGL4 {

    protected static final int[] map = new int[]{
            GL_LEQUAL,
            GL_LESS,
            GL_GEQUAL,
            GL_GREATER,
            GL_EQUAL,
            GL_NOTEQUAL,
            GL_ALWAYS,
            GL_NEVER

    };

    private TextureCompareFuncMapGL4() {
    }
}
