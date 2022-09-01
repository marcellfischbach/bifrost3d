package com.bifrost3d.graphics.opengl.gl4;

import static org.lwjgl.opengl.GL44.GL_COMPARE_REF_TO_TEXTURE;
import static org.lwjgl.opengl.GL44.GL_NONE;

public abstract class TextureCompareModeMapGL4 {

    public static final int[] map = new int[]{
            GL_NONE,
            GL_COMPARE_REF_TO_TEXTURE

    };

    private TextureCompareModeMapGL4() {
    }
}
