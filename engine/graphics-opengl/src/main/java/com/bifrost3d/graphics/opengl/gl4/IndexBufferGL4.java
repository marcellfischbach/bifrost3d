package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.IIndexBuffer;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL44.GL_STATIC_DRAW;

public class IndexBufferGL4 extends BufferGL4 implements IIndexBuffer {

    public IndexBufferGL4(GraphicsGL4 graphics) {
        super(graphics, GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW);
    }

    @Override
    protected void applyGraphics() {
        this.graphics.setIndexBuffer(this);
    }
}
