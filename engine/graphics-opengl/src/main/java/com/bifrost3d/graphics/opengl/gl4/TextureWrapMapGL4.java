package com.bifrost3d.graphics.opengl.gl4;

import static org.lwjgl.opengl.GL44.*;

public abstract class TextureWrapMapGL4 {

    public static final int[] map = new int[] {
            GL_CLAMP_TO_EDGE,
            GL_MIRRORED_REPEAT,
            GL_REPEAT,
            GL_MIRROR_CLAMP_TO_EDGE

    };

    private TextureWrapMapGL4() {
    }
}
