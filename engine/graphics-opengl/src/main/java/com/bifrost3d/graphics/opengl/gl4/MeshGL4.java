package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.Mesh;

public class MeshGL4 extends Mesh {

    private final GraphicsGL4 graphics;

    private boolean dirty = true;

    private VertexBufferGL4 vb;

    private IndexBufferGL4 ib;

    public MeshGL4(GraphicsGL4 graphics) {
        this.graphics = graphics;
    }

    @Override
    protected void markGraphicsBuffersDirty() {
        this.dirty = true;
    }
}
