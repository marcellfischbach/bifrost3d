package com.bifrost3d.math;

@SuppressWarnings("squid:S1104")
public final class ColorRGB {

    public float r;

    public float g;

    public float b;

    public ColorRGB() {
    }

    public ColorRGB(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int rgb() {
        return ((int) (r * 255.0f) & 0xff) << 16
                | ((int) (g * 255.0f) & 0xff) << 8
                | (int) (b * 255.0f) & 0xff;
    }

    public int rgba() {
        return ((int) (r * 255.0f) & 0xff) << 24
                | ((int) (g * 255.0f) & 0xff) << 16
                | ((int) (b * 255.0f) & 0xff) << 8
                |  0xff;
    }

    public int argb() {
        return 0xff000000
                | ((int) (r * 255.0f) & 0xff) << 16
                | ((int) (g * 255.0f) & 0xff) << 8
                | (int) (b * 255.0f) & 0xff;
    }

    public static boolean equals(ColorRGB v0, ColorRGB v1) {
        if (v0 == v1) return true;
        if (v0 == null || v1 == null) return false;

        if (!Mathf.equals(v0.r, v1.r)) return false;
        if (!Mathf.equals(v0.g, v1.g)) return false;
        return Mathf.equals(v0.b, v1.b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ColorRGB.class != o.getClass()) return false;

        ColorRGB vector3f = (ColorRGB) o;

        if (!Mathf.equals(vector3f.r, r)) return false;
        if (!Mathf.equals(vector3f.g, g)) return false;
        return Mathf.equals(vector3f.b, b);
    }

    @Override
    public int hashCode() {
        int result = (r != 0.0f ? Float.floatToIntBits(r) : 0);
        result = 31 * result + (g != 0.0f ? Float.floatToIntBits(g) : 0);
        result = 31 * result + (b != 0.0f ? Float.floatToIntBits(b) : 0);
        return result;
    }

    @Override
    public String toString() {
        return r + ", " + g + ", " + b;
    }
}
