package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EShaderAttributeFormat;
import com.bifrost3d.core.graphics.IShaderAttribute;
import com.bifrost3d.math.Matrix3f;
import com.bifrost3d.math.Matrix4f;

import static org.lwjgl.opengl.GL44.*;

public class ShaderAttributeGL4 implements IShaderAttribute {

    private final EShaderAttributeFormat format;

    private final int idx;

    private int arrayIdx;

    private int absIdx;

    private static final float[] mat3Buffer = new float[9];
    private static final float[] mat4Buffer = new float[16];

    public ShaderAttributeGL4(EShaderAttributeFormat format, int idx) {
        this.format = format;
        this.idx = idx;
        this.arrayIdx = 0;
        this.absIdx = idx;
    }

    @Override
    public void setArrayIdx(int arrayIdx) {
        this.arrayIdx = arrayIdx;
        this.absIdx = this.idx + this.arrayIdx;
    }

    @Override
    public EShaderAttributeFormat getFormat() {
        return this.format;
    }

    @Override
    public void bind(float x) {
        glUniform1f(this.absIdx, x);
    }

    @Override
    public void bind(float x, float y) {
        glUniform2f(this.absIdx, x, y);
    }

    @Override
    public void bind(float x, float y, float z) {
        glUniform3f(this.absIdx, x, y, z);
    }

    @Override
    public void bind(float x, float y, float z, float w) {
        glUniform4f(this.absIdx, x, y, z, w);
    }

    @Override
    public void bind(int x) {
        glUniform1i(this.absIdx, x);
    }

    @Override
    public void bind(int x, int y) {
        glUniform2i(this.absIdx, x, y);
    }

    @Override
    public void bind(int x, int y, int z) {
        glUniform3i(this.absIdx, x, y, z);
    }

    @Override
    public void bind(int x, int y, int z, int w) {
        glUniform4i(this.absIdx, x, y, z, w);
    }

    @Override
    public void bind(Matrix3f m) {
        m.writeToBuffer(mat3Buffer, 0);
        glUniformMatrix3fv(this.absIdx, false, mat3Buffer);
    }

    @Override
    public void bind(Matrix4f m) {
        m.writeToBuffer(mat4Buffer, 0);
        glUniformMatrix4fv(this.absIdx, false, mat4Buffer);

    }
}
