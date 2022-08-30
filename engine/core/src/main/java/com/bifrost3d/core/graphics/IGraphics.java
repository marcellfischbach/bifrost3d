package com.bifrost3d.core.graphics;

import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Matrix4f;

public interface IGraphics {

    void setClearColor(ColorRGBA color);
    void setClearDepth (float depth);
    void setClearStencil(int stencil);

    void clear (boolean clearColor, boolean clearDepth, boolean clearStencil);
    void clear (boolean clearColor, ColorRGBA color, boolean clearDepth, float depth, boolean clearStencil, int stencil);

    IShader createShader (EShaderType type);
    IProgram createProgram ();

    IVertexBuffer createVertexBuffer ();
    IIndexBuffer createIndexBuffer ();

    ITexture2D createTexture2D(ITexture2D.Descriptor descriptor);

    void setVertexBuffer (IVertexBuffer vertexBuffer);
    void setIndexBuffer (IIndexBuffer indexBuffer);

    Mesh createMesh ();
    void renderMesh (Mesh mesh);

    void setProgram (IProgram program);

    void setModelMatrix (Matrix4f matrix);
    void setViewMatrix (Matrix4f matrix);
    void setProjectionMatrix (Matrix4f matrix);
}
