package com.bifrost3d.math;


@SuppressWarnings("squid:S1104")
public final class Vector2f {

    public float x;
    public float y;


    public Vector2f() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return Mathf.sqrt(x * x + y * y);
    }

    public float dot() {
        return x * x + y * y;
    }

    public static float length(Vector2f v) {
        return Mathf.sqrt(v.x * v.x + v.y * v.y);
    }

    public static float dot(Vector2f v) {
        return v.x * v.x + v.y * v.y;
    }

    public static float dot(Vector2f v0, Vector2f v1) {
        return v0.x * v1.x + v0.y * v1.y;
    }


    public void normalize() {
        float l = Mathf.sqrt(x * x + y * y);
        x /= l;
        y /= l;
    }

    public Vector2f normalized() {
        return normalized(new Vector2f());
    }

    public Vector2f normalized(Vector2f r) {
        float l = Mathf.sqrt(x * x + y * y);
        r.x = x / l;
        r.y = y / l;

        return r;
    }

    public static Vector2f add(Vector2f v0, Vector2f v1) {
        return add(v0, v1, new Vector2f());
    }


    public static Vector2f add(Vector2f v0, Vector2f v1, Vector2f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        return r;
    }

    public static Vector2f sub(Vector2f v0, Vector2f v1) {
        return sub(v0, v1, new Vector2f());
    }


    public static Vector2f sub(Vector2f v0, Vector2f v1, Vector2f r) {
        r.x = v0.x - v1.x;
        r.y = v0.y - v1.y;
        return r;
    }


    public static Vector2f mul(Vector2f v0, Vector2f v1) {
        return mul(v0, v1, new Vector2f());
    }


    public static Vector2f mul(Vector2f v0, Vector2f v1, Vector2f r) {
        r.x = v0.x * v1.x;
        r.y = v0.y * v1.y;
        return r;
    }

    public static Vector2f mul(Vector2f v0, float v1) {
        return mul(v0, v1, new Vector2f());
    }


    public static Vector2f mul(Vector2f v0, float v1, Vector2f r) {
        r.x = v0.x * v1;
        r.y = v0.y * v1;
        return r;
    }


    public static Vector2f mul(float v0, Vector2f v1) {
        return mul(v0, v1, new Vector2f());
    }


    public static Vector2f mul(float v0, Vector2f v1, Vector2f r) {
        r.x = v0 * v1.x;
        r.y = v0 * v1.y;
        return r;
    }

    public static Vector2f div(Vector2f v0, Vector2f v1) {
        return div(v0, v1, new Vector2f());
    }


    public static Vector2f div(Vector2f v0, Vector2f v1, Vector2f r) {
        r.x = v0.x / v1.x;
        r.y = v0.y / v1.y;
        return r;
    }

    public static Vector2f div(Vector2f v0, float v1) {
        return div(v0, v1, new Vector2f());
    }


    public static Vector2f div(Vector2f v0, float v1, Vector2f r) {
        r.x = v0.x / v1;
        r.y = v0.y / v1;
        return r;
    }


    public static Vector2f div(float v0, Vector2f v1) {
        return div(v0, v1, new Vector2f());
    }


    public static Vector2f div(float v0, Vector2f v1, Vector2f r) {
        r.x = v0 / v1.x;
        r.y = v0 / v1.y;
        return r;
    }


    public static boolean equals(Vector2f v0, Vector2f v1) {
        if (v0 == v1) return true;
        if (v0 == null || v1 == null) return false;

        if (!Mathf.equals(v0.x, v1.x)) return false;
        return Mathf.equals(v0.y, v1.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Vector2f.class != o.getClass()) return false;

        Vector2f vector3f = (Vector2f) o;

        if (!Mathf.equals(vector3f.x, x)) return false;
        return Mathf.equals(vector3f.y, y);
    }

    @Override
    public int hashCode() {
        int result = (x != 0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != 0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
