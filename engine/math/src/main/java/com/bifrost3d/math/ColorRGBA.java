package com.bifrost3d.math;

@SuppressWarnings("squid:S1104")
public final class ColorRGBA {

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


    public int rgba() {
        return ((int) (r * 255.0f) & 0xff) << 24
                | ((int) (g * 255.0f) & 0xff) << 16
                | ((int) (b * 255.0f) & 0xff) << 8
                | ((int) (a * 255.0f) & 0xff);
    }

    public int argb() {
        return ((int) (a * 255.0f) & 0xff) << 24
                | ((int) (r * 255.0f) & 0xff) << 16
                | ((int) (g * 255.0f) & 0xff) << 8
                | (int) (b * 255.0f) & 0xff;
    }


    public static boolean equals(ColorRGBA v0, ColorRGBA v1) {
        if (v0 == v1) return true;
        if (v0 == null || v1 == null) return false;

        if (!Mathf.equals(v0.r, v1.r)) return false;
        if (!Mathf.equals(v0.g, v1.g)) return false;
        if (!Mathf.equals(v0.b, v1.b)) return false;
        return Mathf.equals(v0.a, v1.a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ColorRGBA.class != o.getClass()) return false;

        ColorRGBA vector3f = (ColorRGBA) o;

        if (!Mathf.equals(vector3f.r, r)) return false;
        if (!Mathf.equals(vector3f.g, g)) return false;
        if (!Mathf.equals(vector3f.b, b)) return false;
        return Mathf.equals(vector3f.a, a);
    }

    @Override
    public int hashCode() {
        int result = (r != 0.0f ? Float.floatToIntBits(r) : 0);
        result = 31 * result + (g != 0.0f ? Float.floatToIntBits(g) : 0);
        result = 31 * result + (b != 0.0f ? Float.floatToIntBits(b) : 0);
        result = 31 * result + (a != 0.0f ? Float.floatToIntBits(a) : 0);
        return result;
    }

    @Override
    public String toString() {
        return r + ", " + g + ", " + b + ", " + a;
    }
}
