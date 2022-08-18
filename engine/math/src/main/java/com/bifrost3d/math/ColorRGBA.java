package com.bifrost3d.math;

public class ColorRGBA {

    public float r;

    public float g;

    public float b;

    public float a;

    public ColorRGBA() {
    }

    public ColorRGBA(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }


    public int rgb() {
        return ((int) (r * 255.0f) & 0xff) << 24
                | ((int) (g * 255.0f) & 0xff) << 16
                | ((int) (b * 255.0f) & 0xff) << 8
                | ((int) (a * 255.0f) & 0xff);
    }
}
