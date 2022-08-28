package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.IVertexBuffer;

import static org.lwjgl.opengl.GL44.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL44.GL_STATIC_DRAW;

public class VertexBufferGL4 extends BufferGL4 implements IVertexBuffer {


    public VertexBufferGL4(GraphicsGL4 graphics) {
        super(graphics, GL_ARRAY_BUFFER, GL_STATIC_DRAW);
    }

    @Override
    protected void applyGraphics() {
        this.graphics.setVertexBuffer(this);
    }
}
