package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EShaderType;
import com.bifrost3d.core.graphics.IDevice;
import com.bifrost3d.core.graphics.IProgram;
import com.bifrost3d.core.graphics.IShader;
import com.bifrost3d.math.ColorRGBA;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL44.*;

public class DeviceGL4 implements IDevice {

    private int clearColor;

    private float clearDepth;

    private int clearStencil;


    public void initialize () {
        GL.createCapabilities();

        clearColor = new ColorRGBA(0.0f, 0.0f, 0.0f, 0.0f).rgb();
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
    public void setClearColor (ColorRGBA color) {
        int colorRGB = color.rgb();
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
}
