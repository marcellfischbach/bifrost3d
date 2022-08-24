package com.bifrost3d.math;


@SuppressWarnings("squid:S1104")
public final class Vector3f {

    public float x;
    public float y;
    public float z;


    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return Mathf.sqrt(x * x + y * y + z * z);
    }

    public float dot() {
        return x * x + y * y + z * z;
    }

    public static float length(Vector3f v) {
        return Mathf.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    public static float dot(Vector3f v) {
        return v.x * v.x + v.y * v.y + v.z * v.z;
    }

    public static float dot(Vector3f v0, Vector3f v1) {
        return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
    }


    public void normalize() {
        float l = Mathf.sqrt(x * x + y * y + z * z);
        x /= l;
        y /= l;
        z /= l;
    }

    public Vector3f normalized() {
        return normalized(new Vector3f());
    }

    public Vector3f normalized(Vector3f r) {
        float l = Mathf.sqrt(x * x + y * y + z * z);
        r.x = x / l;
        r.y = y / l;
        r.z = z / l;

        return r;
    }

    public static Vector3f add(Vector3f v0, Vector3f v1) {
        return add(v0, v1, new Vector3f());
    }


    public static Vector3f add(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x + v1.x;
        r.y = v0.y + v1.y;
        r.z = v0.z + v1.z;
        return r;
    }

    public static Vector3f sub(Vector3f v0, Vector3f v1) {
        return sub(v0, v1, new Vector3f());
    }


    public static Vector3f sub(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x - v1.x;
        r.y = v0.y - v1.y;
        r.z = v0.z - v1.z;
        return r;
    }


    public static Vector3f mul(Vector3f v0, Vector3f v1) {
        return mul(v0, v1, new Vector3f());
    }


    public static Vector3f mul(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x * v1.x;
        r.y = v0.y * v1.y;
        r.z = v0.z * v1.z;
        return r;
    }

    public static Vector3f mul(Vector3f v0, float v1) {
        return mul(v0, v1, new Vector3f());
    }


    public static Vector3f mul(Vector3f v0, float v1, Vector3f r) {
        r.x = v0.x * v1;
        r.y = v0.y * v1;
        r.z = v0.z * v1;
        return r;
    }


    public static Vector3f mul(float v0, Vector3f v1) {
        return mul(v0, v1, new Vector3f());
    }


    public static Vector3f mul(float v0, Vector3f v1, Vector3f r) {
        r.x = v0 * v1.x;
        r.y = v0 * v1.y;
        r.z = v0 * v1.z;
        return r;
    }

    public static Vector3f div(Vector3f v0, Vector3f v1) {
        return div(v0, v1, new Vector3f());
    }


    public static Vector3f div(Vector3f v0, Vector3f v1, Vector3f r) {
        r.x = v0.x / v1.x;
        r.y = v0.y / v1.y;
        r.z = v0.z / v1.z;
        return r;
    }

    public static Vector3f div(Vector3f v0, float v1) {
        return div(v0, v1, new Vector3f());
    }


    public static Vector3f div(Vector3f v0, float v1, Vector3f r) {
        r.x = v0.x / v1;
        r.y = v0.y / v1;
        r.z = v0.z / v1;
        return r;
    }


    public static Vector3f div(float v0, Vector3f v1) {
        return div(v0, v1, new Vector3f());
    }


    public static Vector3f div(float v0, Vector3f v1, Vector3f r) {
        r.x = v0 / v1.x;
        r.y = v0 / v1.y;
        r.z = v0 / v1.z;
        return r;
    }


    public static Vector3f cross(Vector3f v0, Vector3f v1) {
        return cross(v0, v1, new Vector3f());
    }

    public static Vector3f cross(Vector3f v0, Vector3f v1, Vector3f r) {
        float x = v0.y * v1.z - v0.z * v1.y;
        float y = v0.z * v1.x - v0.x * v1.z;
        float z = v0.x * v1.y - v0.y * v1.x;
        r.x = x;
        r.y = y;
        r.z = z;
        return r;
    }

    public static boolean equals(Vector3f v0, Vector3f v1) {
        if (v0 == v1) return true;
        if (v0 == null || v1 == null) return false;

        if (!Mathf.equals(v0.x, v1.x)) return false;
        if (!Mathf.equals(v0.y, v1.y)) return false;
        return Mathf.equals(v0.z, v1.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Vector3f.class != o.getClass()) return false;

        Vector3f vector3f = (Vector3f) o;

        if (!Mathf.equals(vector3f.x, x)) return false;
        if (!Mathf.equals(vector3f.y, y)) return false;
        return Mathf.equals(vector3f.z, z);
    }

    @Override
    public int hashCode() {
        int result = (x != 0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != 0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != 0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }
}
