package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.*;
import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Matrix4f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL44.*;

public class GraphicsGL4 implements IGraphics {

    private int clearColor;

    private float clearDepth;

    private int clearStencil;

    private VertexBufferGL4 vertexBuffer = null;

    private IndexBufferGL4 indexBuffer = null;


    private final Matrix4f modelMatrix = new Matrix4f();
    private final Matrix4f viewMatrix = new Matrix4f();
    private final Matrix4f projectionMatrix = new Matrix4f();


    private final Matrix4f viewModelMatrix = new Matrix4f();
    private final Matrix4f projectionViewMatrix = new Matrix4f();
    private final Matrix4f projectionViewModelMatrix = new Matrix4f();

    private final Matrix4f modelMatrixInv = new Matrix4f();
    private final Matrix4f viewMatrixInv = new Matrix4f();
    private final Matrix4f projectionMatrixInv = new Matrix4f();
    private final Matrix4f viewModelMatrixInv = new Matrix4f();
    private final Matrix4f projectionViewMatrixInv = new Matrix4f();
    private final Matrix4f projectionViewModelMatrixInv = new Matrix4f();

    private boolean viewModeMatrixInvalid = true;
    private boolean projectionViewMatrixInvalid = true;
    private boolean projectionViewModelMatrixInvalid = true;
    private boolean modelMatrixInvInvalid = true;
    private boolean viewMatrixInvInvalid = true;
    private boolean projectionMatrixInvInvalid = true;
    private boolean viewModelMatrixInvInvalid = true;
    private boolean projectionViewMatrixInvInvalid = true;
    private boolean projectionViewModelMatrixInvInvalid = true;

    public void initialize() {
        GL.createCapabilities();

        clearColor = new ColorRGBA(0.0f, 0.0f, 0.0f, 0.0f).rgba();
        clearDepth = 1.0f;
        clearStencil = 0;
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glClearStencil(0);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
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
    public VertexBufferGL4 createVertexBuffer() {
        return new VertexBufferGL4(this);
    }

    @Override
    public IndexBufferGL4 createIndexBuffer() {
        return new IndexBufferGL4(this);
    }

    @Override
    public Mesh createMesh() {
        return new MeshGL4(this);
    }

    @Override
    public void renderMesh(Mesh mesh) {
        MeshGL4 meshGL4 = (MeshGL4) mesh;
        meshGL4.render();
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

    @Override
    public void setProgram(IProgram program) {
        if (program != null) {
            ProgramGL4 programGL4 = (ProgramGL4) program;
            glUseProgram(programGL4.glName());
        } else {
            glUseProgram(0);
        }
    }


    @Override
    public void setModelMatrix(Matrix4f matrix) {
        this.modelMatrix.set(matrix);

        this.viewModeMatrixInvalid = true;
        this.projectionViewModelMatrixInvalid = true;

        this.modelMatrixInvInvalid = true;
        this.viewModelMatrixInvInvalid = true;
        this.projectionViewModelMatrixInvInvalid = true;
    }

    @Override
    public void setViewMatrix(Matrix4f matrix) {
        this.viewMatrix.set(matrix);

        this.viewModeMatrixInvalid = true;
        this.projectionViewMatrixInvalid = true;
        this.projectionViewModelMatrixInvalid = true;

        this.viewMatrixInvInvalid = true;
        this.viewModelMatrixInvInvalid = true;
        this.projectionViewMatrixInvInvalid = true;
        this.projectionViewModelMatrixInvInvalid = true;

    }

    @Override
    public void setProjectionMatrix(Matrix4f matrix) {
        this.projectionMatrix.set(matrix);

        this.projectionViewMatrixInvalid = true;
        this.projectionViewModelMatrixInvalid = true;

        this.projectionMatrixInvInvalid = true;
        this.projectionViewMatrixInvInvalid = true;
        this.projectionViewModelMatrixInvInvalid = true;

    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewModelMatrix() {
        if (viewModeMatrixInvalid) {
            Matrix4f.mul(viewMatrix, modelMatrix, viewModelMatrix);
            viewModeMatrixInvalid = false;
        }
        return viewModelMatrix;
    }

    public Matrix4f getProjectionViewMatrix() {
        if (projectionViewMatrixInvalid) {
            Matrix4f.mul(projectionMatrix, viewMatrix, projectionViewMatrix);
            projectionViewMatrixInvalid = false;
        }
        return projectionViewMatrix;
    }

    public Matrix4f getProjectionViewModelMatrix() {
        if (projectionViewModelMatrixInvalid) {
            Matrix4f.mul(getProjectionViewMatrix(), modelMatrix, projectionViewModelMatrix);
            projectionViewModelMatrixInvalid = false;
        }
        return projectionViewModelMatrix;
    }

    public Matrix4f getModelMatrixInv() {
        if (modelMatrixInvInvalid) {
            this.modelMatrix.inverted(modelMatrixInv);
            modelMatrixInvInvalid = false;
        }
        return modelMatrixInv;
    }

    public Matrix4f getViewMatrixInv() {
        if (viewMatrixInvInvalid) {
            this.viewMatrix.inverted(viewMatrixInv);
            viewMatrixInvInvalid = false;
        }
        return viewMatrixInv;
    }

    public Matrix4f getProjectionMatrixInv() {
        if (projectionMatrixInvInvalid) {
            projectionMatrix.inverted(projectionMatrixInv);
            projectionMatrixInvInvalid = false;
        }
        return projectionMatrixInv;
    }

    public Matrix4f getViewModelMatrixInv() {
        if (viewModelMatrixInvInvalid) {
            getViewModelMatrix().inverted(viewModelMatrixInv);
            viewModelMatrixInvInvalid = false;
        }
        return viewModelMatrixInv;
    }

    public Matrix4f getProjectionViewMatrixInv() {
        if (projectionViewMatrixInvInvalid) {
            getProjectionViewMatrix().inverted(projectionViewMatrixInv);
            projectionViewMatrixInvInvalid = false;
        }
        return projectionViewMatrixInv;
    }

    public Matrix4f getProjectionViewModelMatrixInv() {
        if (projectionViewModelMatrixInvInvalid) {
            getProjectionViewModelMatrix().inverted(projectionViewModelMatrixInv);
            projectionViewModelMatrixInvInvalid = false;
        }
        return projectionViewModelMatrixInv;
    }
}
