package com.bifrost3d.math;


@SuppressWarnings("squid:S1104")
public final class Vector4f {

    public float x;
    public float y;
    public float z;
    public float w;


    public Vector4f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float length() {
        return Mathf.sqrt(x * x + y * y + z * z + w * w);
    }

    public float dot() {
        return x * x + y * y + z * z + w * w;
    }

    public static float length(Vector4f v) {
        return Mathf.sqrt(v.x * v.x + v.y * v.y + v.z * v.z + v.w * v.w);
    }

    public static float dot(Vector4f v) {
        return v.x * v.x + v.y * v.y + v.z * v.z + v.w * v.w;
    }

    public static float dot(Vector4f v0, Vector4f v1) {
        return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z + v0.w * v1.w;
    }


    public void normalize() {
        float l = Mathf.sqrt(x * x + y * y + z * z + w*w);
        x /= l;
        y /= l;
        z /= l;
        w /= l;
    }

    public Vector4f normalized() {
        return normalized(new Vector4f());
    }

    public Vector4f normalized(Vector4f r) {
        float l = Mathf.sqrt(x * x + y * y + z * z + w*w);
        r.x = x / l;
        r.y = y / l;
        r.z = z / l;
        r.w = w / l;

        return r;
    }

    public static Vector4f add(Vector4f v0, Vector4f v1) {
        return add(v0, v1, new Vector4f());
    }


    public static Vector4f add(Vector4f v0, Vector4f v1, Vector4f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        r.z = v0.z + v1.z;
        r.w = v0.w + v1.w;
        return r;
    }

    public static Vector4f sub(Vector4f v0, Vector4f v1) {
        return sub(v0, v1, new Vector4f());
    }


    public static Vector4f sub(Vector4f v0, Vector4f v1, Vector4f r) {
        r.x = v0.x - v1.x;
        r.y = v0.y - v1.y;
        r.z = v0.z - v1.z;
        r.w = v0.w - v1.w;
        return r;
    }


    public static Vector4f mul(Vector4f v0, Vector4f v1) {
        return mul(v0, v1, new Vector4f());
    }


    public static Vector4f mul(Vector4f v0, Vector4f v1, Vector4f r) {
        r.x = v0.x * v1.x;
        r.y = v0.y * v1.y;
        r.z = v0.z * v1.z;
        r.w = v0.w * v1.w;
        return r;
    }

    public static Vector4f mul(Vector4f v0, float v1) {
        return mul(v0, v1, new Vector4f());
    }


    public static Vector4f mul(Vector4f v0, float v1, Vector4f r) {
        r.x = v0.x * v1;
        r.y = v0.y * v1;
        r.z = v0.z * v1;
        r.w = v0.w * v1;
        return r;
    }


    public static Vector4f mul(float v0, Vector4f v1) {
        return mul(v0, v1, new Vector4f());
    }


    public static Vector4f mul(float v0, Vector4f v1, Vector4f r) {
        r.x = v0 * v1.x;
        r.y = v0 * v1.y;
        r.z = v0 * v1.z;
        r.w = v0 * v1.w;
        return r;
    }

    public static Vector4f div(Vector4f v0, Vector4f v1) {
        return div(v0, v1, new Vector4f());
    }


    public static Vector4f div(Vector4f v0, Vector4f v1, Vector4f r) {
        r.x = v0.x / v1.x;
        r.y = v0.y / v1.y;
        r.z = v0.z / v1.z;
        r.w = v0.w / v1.w;
        return r;
    }

    public static Vector4f div(Vector4f v0, float v1) {
        return div(v0, v1, new Vector4f());
    }


    public static Vector4f div(Vector4f v0, float v1, Vector4f r) {
        r.x = v0.x / v1;
        r.y = v0.y / v1;
        r.z = v0.z / v1;
        r.w = v0.w / v1;
        return r;
    }


    public static Vector4f div(float v0, Vector4f v1) {
        return div(v0, v1, new Vector4f());
    }


    public static Vector4f div(float v0, Vector4f v1, Vector4f r) {
        r.x = v0 / v1.x;
        r.y = v0 / v1.y;
        r.z = v0 / v1.z;
        r.w = v0 / v1.w;
        return r;
    }


    public static boolean equals(Vector4f v0, Vector4f v1) {
        if (v0 == v1) return true;
        if (v0 == null || v1 == null) return false;

        if (!Mathf.equals(v0.x, v1.x)) return false;
        if (!Mathf.equals(v0.y, v1.y)) return false;
        if (!Mathf.equals(v0.z, v1.z)) return false;
        return Mathf.equals(v0.w, v1.w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Vector4f.class != o.getClass()) return false;

        Vector4f vector3f = (Vector4f) o;

        if (!Mathf.equals(vector3f.x, x)) return false;
        if (!Mathf.equals(vector3f.y, y)) return false;
        if (!Mathf.equals(vector3f.z, z)) return false;
        return Mathf.equals(vector3f.w, w);
    }

    @Override
    public int hashCode() {
        int result = (x != 0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != 0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != 0.0f ? Float.floatToIntBits(z) : 0);
        result = 31 * result + (w != 0.0f ? Float.floatToIntBits(w) : 0);
        return result;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z + ", " + w;
    }
}
