package com.bifrost3d.core.graphics;

import java.nio.*;

public interface IBuffer {

    void generateBuffer (long size);

    void copy(long offset, short[] data);


    void copy(long offset, int[] data);


    void copy(long offset, float[] data);


    void copy(long offset, double[] data);


    void copy(long offset, ByteBuffer data);


    void copy(long offset, ShortBuffer data);


    void copy(long offset, IntBuffer data);


    void copy(long offset, FloatBuffer data);


    void copy(long offset, DoubleBuffer data);


    ByteBuffer map();

    void unmap();

}
