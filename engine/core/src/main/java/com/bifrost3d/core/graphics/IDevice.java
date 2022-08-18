package com.bifrost3d.core.graphics;

import com.bifrost3d.math.ColorRGBA;

public interface IDevice {

    void setClearColor(ColorRGBA color);
    void setClearDepth (float depth);
    void setClearStencil(int stencil);

    void clear (boolean clearColor, boolean clearDepth, boolean clearStencil);
    void clear (boolean clearColor, ColorRGBA color, boolean clearDepth, float depth, boolean clearStencil, int stencil);

    IShader createShader (EShaderType type);
    IProgram createProgram ();
}
