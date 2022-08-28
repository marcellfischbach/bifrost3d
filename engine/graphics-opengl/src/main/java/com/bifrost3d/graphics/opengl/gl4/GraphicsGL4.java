package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.*;
import com.bifrost3d.math.ColorRGBA;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL44.*;

public class GraphicsGL4 implements IGraphics {

    private int clearColor;

    private float clearDepth;

    private int clearStencil;

    private VertexBufferGL4 vertexBuffer = null;

    private IndexBufferGL4 indexBuffer = null;

    public void initialize() {
        GL.createCapabilities();

        clearColor = new ColorRGBA(0.0f, 0.0f, 0.0f, 0.0f).rgba();
        clearDepth = 1.0f;
        clearStencil = 0;
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glClearStencil(0);
    }


    @Override
    public void clear(boolean clearColor, boolean clearDepth, boolean clearStencil) {
        int clearMask = 0;
        if (clearColor) {
            clearMask |= GL_COLOR_BUFFER_BIT;
        }
        if (clearDepth) {
            clearMask |= GL_DEPTH_BUFFER_BIT;
        }
        if (clearStencil) {
            clearMask |= GL_STENCIL_BUFFER_BIT;
        }
        if (clearMask != 0) {
            glClear(clearMask);
        }
    }

    @Override
    public void clear(boolean clearColor, ColorRGBA color, boolean clearDepth, float depth, boolean clearStencil, int stencil) {
        int clearMask = 0;
        if (clearColor) {
            setClearColor(color);
            clearMask |= GL_COLOR_BUFFER_BIT;
        }
        if (clearDepth) {
            setClearDepth(depth);
            clearMask |= GL_DEPTH_BUFFER_BIT;
        }
        if (clearStencil) {
            setClearStencil(stencil);
            clearMask |= GL_STENCIL_BUFFER_BIT;
        }
        if (clearMask != 0) {
            glClear(clearMask);
        }
    }


    @Override
    public void setClearColor(ColorRGBA color) {
        int colorRGB = color.rgba();
        if (colorRGB != this.clearColor) {
            this.clearColor = colorRGB;
            glClearColor(color.r, color.g, color.b, color.a);
        }
    }

    @Override
    public void setClearDepth(float depth) {
        if (this.clearDepth != depth) {
            this.clearDepth = depth;
            glClearDepth(depth);
        }
    }

    @Override
    public void setClearStencil(int stencil) {
        if (this.clearStencil != stencil) {
            this.clearStencil = stencil;
            glClearStencil(stencil);
        }
    }


    @Override
    public IShader createShader(EShaderType type) {
        return new ShaderGL4(type);
    }

    @Override
    public IProgram createProgram() {
        return new ProgramGL4();
    }

    @Override
    public IVertexBuffer createVertexBuffer() {
        return new VertexBufferGL4(this);
    }

    @Override
    public IIndexBuffer createIndexBuffer() {
        return new IndexBufferGL4(this);
    }

    @Override
    public Mesh createMesh() {
        return new MeshGL4(this);
    }

    @Override
    public void setVertexBuffer(IVertexBuffer vertexBuffer) {
        VertexBufferGL4 vb = (VertexBufferGL4) vertexBuffer;
        if (vb != this.vertexBuffer) {
            this.vertexBuffer = vb;
            this.vertexBuffer.bind();
        }
    }

    @Override
    public void setIndexBuffer(IIndexBuffer indexBuffer) {
        IndexBufferGL4 ib = (IndexBufferGL4) indexBuffer;
        if (ib != this.indexBuffer) {
            this.indexBuffer = ib;
            this.indexBuffer.bind();
        }
    }
}
