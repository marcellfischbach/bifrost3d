package com.bifrost3d.graphics.opengl.gl4;


import static org.lwjgl.opengl.GL44.*;

public abstract class PixelFormatMapGL4 {

    public static final int[] internal = new int [] {
            GL_R8,                  // R8,
            GL_RG8,                 // R8G8,
            GL_RGB8,                // R8G8B8,
            GL_RGBA8,               // R8G8B8A8,
            GL_DEPTH_COMPONENT32,   // DEPTH32,
            GL_DEPTH24_STENCIL8     // DEPTH24_STENCIL8
    };

    public static final int[] client = new int [] {
            GL_RED,                 // R8,
            GL_RG,                  // R8G8,
            GL_RGB,                 // R8G8B8,
            GL_RGBA,                // R8G8B8A8,
            GL_DEPTH,               // DEPTH32,
            GL_DEPTH_STENCIL        // DEPTH24_STENCIL8
    };


    public static final int[] format= new int [] {
            GL_UNSIGNED_BYTE,       // R8,
            GL_UNSIGNED_BYTE,       // R8G8,
            GL_UNSIGNED_BYTE,       // R8G8B8,
            GL_UNSIGNED_BYTE,       // R8G8B8A8,
            GL_UNSIGNED_INT,        // DEPTH32,
            GL_UNSIGNED_INT_24_8    // DEPTH24_STENCIL8
    };


    private PixelFormatMapGL4() {
    }
}
