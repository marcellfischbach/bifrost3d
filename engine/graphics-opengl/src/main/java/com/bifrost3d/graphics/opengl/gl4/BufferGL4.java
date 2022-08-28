package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.IBuffer;

import java.nio.*;

import static org.lwjgl.opengl.GL44.*;

abstract class BufferGL4 implements IBuffer {

    protected final GraphicsGL4 graphics;

    private final int target;

    private final int mapAccess;

    private final int name;


    public BufferGL4(GraphicsGL4 graphics, int target, int mapAccess) {
        this.graphics = graphics;
        this.target = target;
        this.mapAccess = mapAccess;

        this.name = glGenBuffers();
    }

    public void bind() {
        glBindBuffer(this.target, this.name);
    }

    protected abstract void applyGraphics();


    @Override
    public void copy(long offset, short[] data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, int[] data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, float[] data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, double[] data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, ByteBuffer data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, ShortBuffer data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, IntBuffer data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, FloatBuffer data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, DoubleBuffer data) {
        applyGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public ByteBuffer map() {
        applyGraphics();
        return glMapBuffer(this.target, this.mapAccess);
    }

    @Override
    public void unmap() {
        applyGraphics();
        glUnmapBuffer(this.target);
    }
}
