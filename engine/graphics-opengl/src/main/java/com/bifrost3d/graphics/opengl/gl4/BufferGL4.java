package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.IBuffer;

import java.nio.*;

import static org.lwjgl.opengl.GL44.*;

abstract class BufferGL4 implements IBuffer {

    protected final GraphicsGL4 graphics;

    private final int target;

    private final int mapAccess;

    private final int name;


    protected BufferGL4(GraphicsGL4 graphics, int target, int mapAccess) {
        this.graphics = graphics;
        this.target = target;
        this.mapAccess = mapAccess;

        this.name = glGenBuffers();
    }

    public void destroy () {
        glDeleteBuffers(this.name);
    }

    public void bind() {
        glBindBuffer(this.target, this.name);
    }

    protected abstract void setBufferInGraphics();


    @Override
    public void generateBuffer(long size) {
        setBufferInGraphics();
        glBufferData(this.target, size, this.mapAccess);
    }

    @Override
    public void copy(long offset, short[] data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, int[] data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, float[] data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, double[] data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, ByteBuffer data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, ShortBuffer data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, IntBuffer data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, FloatBuffer data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public void copy(long offset, DoubleBuffer data) {
        setBufferInGraphics();
        glBufferSubData(this.target, offset, data);
    }

    @Override
    public ByteBuffer map() {
        setBufferInGraphics();
        return glMapBuffer(this.target, this.mapAccess);
    }

    @Override
    public void unmap() {
        setBufferInGraphics();
        glUnmapBuffer(this.target);
    }
}
