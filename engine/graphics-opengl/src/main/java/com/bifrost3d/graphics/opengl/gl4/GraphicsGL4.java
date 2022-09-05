package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.*;
import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Matrix4f;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.opengl.GL44.*;

public class GraphicsGL4 implements IGraphics {

    private int clearColor;

    private float clearDepth;

    private int clearStencil;

    private VertexBufferGL4 vertexBuffer = null;

    private IndexBufferGL4 indexBuffer = null;

    private ProgramGL4 program = null;

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

    private int nextTextureUnit;
    private final TextureGL4[] textures = new TextureGL4[ETextureUnit.values().length];
    private final SamplerGL4[] samplers = new SamplerGL4[ETextureUnit.values().length];


    public void initialize() {
        GL.createCapabilities();

        clearColor = new ColorRGBA(0.0f, 0.0f, 0.0f, 0.0f).rgba();
        clearDepth = 1.0f;
        clearStencil = 0;
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glClearStencil(0);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glPixelStorei(GL_PACK_ALIGNMENT, 1);

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
    public ShaderGL4 createShader(EShaderType type) {
        return new ShaderGL4(type);
    }

    @Override
    public ProgramGL4 createProgram() {
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
    public ISampler createSampler() {
        return new SamplerGL4();
    }

    @Override
    public Texture2DGL4 createTexture2D(Image image) {
        Texture2DGL4 texture = createTexture2D(new ITexture2D.Descriptor(
                image.getPixelFormat(),
                image.getWidth(),
                image.getHeight(),
                image.getLayerCount() > 1
        ));
        texture.setImage(image);
        return texture;
    }

    @Override
    public Texture2DGL4 createTexture2D(ITexture2D.Descriptor descriptor) {
        return new Texture2DGL4(this, descriptor);
    }


    @Override
    public void resetTextureUnits() {
        this.nextTextureUnit = 0;
    }

    @Override
    public int bind(ITexture texture) {

        if (this.nextTextureUnit >= 15) {
            return -1;
        }
        int unit = this.nextTextureUnit;
        nextTextureUnit++;

        bindTexture((TextureGL4) texture, unit);
        return unit;
    }

    private void bindTexture (TextureGL4 texture, int unit) {

        TextureGL4 currentTexture = this.textures[unit];
        if (currentTexture != texture) {
            glActiveTexture(unit);

            if (needUnbind(currentTexture, texture)) {
                currentTexture.unbind();
            }

            if (texture != null) {
                bindSampler(texture.getSampler(), unit);
                texture.bind();
            }
            this.textures[unit] = texture;
        }
    }

    private static boolean needUnbind (TextureGL4 oldTexture, TextureGL4 newTexture) {
        return oldTexture != null
                && (newTexture == null || oldTexture.getTarget() != newTexture.getTarget());
    }

    private void bindSampler(SamplerGL4 sampler, int unit) {
        if (this.samplers[unit] != sampler) {
            this.samplers[unit] = sampler;
            if (sampler != null) {
                sampler.bind(unit);
            }
            else {
                glBindSampler(unit, 0);
            }
        }
    }

    public void bindTemp(ITexture2D texture) {
        Texture2DGL4 textureGL4 = (Texture2DGL4) texture;

        glActiveTexture(16);
        bindSampler(textureGL4.getSampler(), 16);
        textureGL4.bind();
    }

    @Override
    public Mesh createMesh() {
        return new MeshGL4(this);
    }

    @Override
    public void renderMesh(Mesh mesh) {
        bindMatrices();

        MeshGL4 meshGL4 = (MeshGL4) mesh;
        meshGL4.render();
    }

    private void bindMatrices() {
        if (this.program != null) {
            bindMatrix(EShaderAttribute.MODEL_MATRIX);
            bindMatrix(EShaderAttribute.VIEW_MATRIX);
            bindMatrix(EShaderAttribute.PROJECTION_MATRIX);

            bindMatrix(EShaderAttribute.VIEW_MODEL_MATRIX);

            bindMatrix(EShaderAttribute.PROJECTION_VIEW_MATRIX);
            bindMatrix(EShaderAttribute.PROJECTION_VIEW_MODEL_MATRIX);
        }
    }

    private void bindMatrix(EShaderAttribute type) {
        IShaderAttribute attribute = this.program.getAttribute(type);
        if (attribute != null) {
            switch (type) {

                case MODEL_MATRIX:
                    attribute.bind(getModelMatrix());
                    break;
                case VIEW_MATRIX:
                    attribute.bind(getViewMatrix());
                    break;
                case PROJECTION_MATRIX:
                    attribute.bind(getProjectionMatrix());
                    break;
                case VIEW_MODEL_MATRIX:
                    attribute.bind(getViewModelMatrix());
                    break;
                case PROJECTION_VIEW_MATRIX:
                    attribute.bind(getProjectionViewMatrix());
                    break;
                case PROJECTION_VIEW_MODEL_MATRIX:
                    attribute.bind(getProjectionViewModelMatrix());
                    break;
                case MODEL_MATRIX_INV:
                    attribute.bind(getModelMatrixInv());
                    break;
                case VIEW_MATRIX_INV:
                    attribute.bind(getViewMatrixInv());
                    break;
                case PROJECTION_MATRIX_INV:
                    attribute.bind(getProjectionMatrixInv());
                    break;
                case VIEW_MODEL_MATRIX_INV:
                    attribute.bind(getViewModelMatrixInv());
                    break;
                case PROJECTION_VIEW_MATRIX_INV:
                    attribute.bind(getProjectionViewMatrixInv());
                    break;
                case PROJECTION_VIEW_MODEL_MATRIX_INV:
                    attribute.bind(getProjectionViewModelMatrixInv());
                    break;
            }
        }
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
        if (Objects.equals(this.program, program)) {
            return;
        }

        if (program != null) {
            ProgramGL4 programGL4 = (ProgramGL4) program;
            glUseProgram(programGL4.glName());
            this.program = programGL4;
        } else {
            glUseProgram(0);
            this.program = null;
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
