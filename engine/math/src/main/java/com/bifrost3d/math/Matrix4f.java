package com.bifrost3d.math;

@SuppressWarnings({"squid:S1104", "squid:S107"})
public final class Matrix4f {

    public float m00;
    public float m01;
    public float m02;
    public float m03;
    public float m10;
    public float m11;
    public float m12;
    public float m13;
    public float m20;
    public float m21;
    public float m22;
    public float m23;
    public float m30;
    public float m31;
    public float m32;
    public float m33;

    public Matrix4f() {
        m00 = 1.0f;
        m01 = 0.0f;
        m02 = 0.0f;
        m03 = 0.0f;
        m10 = 0.0f;
        m11 = 1.0f;
        m12 = 0.0f;
        m13 = 0.0f;
        m20 = 0.0f;
        m21 = 0.0f;
        m22 = 1.0f;
        m23 = 0.0f;
        m30 = 0.0f;
        m31 = 0.0f;
        m32 = 0.0f;
        m33 = 1.0f;
    }

    public Matrix4f(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public void setIdentity() {
        m00 = 1.0f;
        m01 = 0.0f;
        m02 = 0.0f;
        m03 = 0.0f;
        m10 = 0.0f;
        m11 = 1.0f;
        m12 = 0.0f;
        m13 = 0.0f;
        m20 = 0.0f;
        m21 = 0.0f;
        m22 = 1.0f;
        m23 = 0.0f;
        m30 = 0.0f;
        m31 = 0.0f;
        m32 = 0.0f;
        m33 = 1.0f;
    }

    public Vector4f getXAxis() {
        return getXAxis(new Vector4f());
    }

    public Vector4f getXAxis(Vector4f r) {
        r.x = m00;
        r.y = m01;
        r.z = m02;
        r.w = m03;
        return r;
    }

    public Vector4f getYAxis() {
        return getYAxis(new Vector4f());
    }

    public Vector4f getYAxis(Vector4f r) {
        r.x = m10;
        r.y = m11;
        r.z = m12;
        r.z = m13;
        return r;
    }

    public Vector4f getZAxis() {
        return getZAxis(new Vector4f());
    }

    public Vector4f getZAxis(Vector4f r) {
        r.x = m20;
        r.y = m21;
        r.z = m22;
        r.z = m23;
        return r;
    }


    public Vector4f getTranslation() {
        return getTranslation(new Vector4f());
    }

    public Vector4f getTranslation(Vector4f r) {
        r.x = m30;
        r.y = m31;
        r.z = m32;
        r.z = m33;
        return r;
    }

    public static Matrix4f mul(Matrix4f m0, Matrix4f m1) {
        return mul(m0, m1, new Matrix4f());
    }

    public static Matrix4f mul(Matrix4f m0, Matrix4f m1, Matrix4f r) {

        float m00 = m0.m00 * m1.m00 + m0.m10 * m1.m01 + m0.m20 * m1.m02 + m0.m30 * m1.m03;
        float m01 = m0.m01 * m1.m00 + m0.m11 * m1.m01 + m0.m21 * m1.m02 + m0.m31 * m1.m03;
        float m02 = m0.m02 * m1.m00 + m0.m12 * m1.m01 + m0.m22 * m1.m02 + m0.m32 * m1.m03;
        float m03 = m0.m03 * m1.m00 + m0.m13 * m1.m01 + m0.m23 * m1.m02 + m0.m33 * m1.m03;

        float m10 = m0.m00 * m1.m10 + m0.m10 * m1.m11 + m0.m20 * m1.m12 + m0.m30 * m1.m13;
        float m11 = m0.m01 * m1.m10 + m0.m11 * m1.m11 + m0.m21 * m1.m12 + m0.m31 * m1.m13;
        float m12 = m0.m02 * m1.m10 + m0.m12 * m1.m11 + m0.m22 * m1.m12 + m0.m32 * m1.m13;
        float m13 = m0.m03 * m1.m10 + m0.m13 * m1.m11 + m0.m23 * m1.m12 + m0.m33 * m1.m13;

        float m20 = m0.m00 * m1.m20 + m0.m10 * m1.m21 + m0.m20 * m1.m22 + m0.m30 * m1.m23;
        float m21 = m0.m01 * m1.m20 + m0.m11 * m1.m21 + m0.m21 * m1.m22 + m0.m31 * m1.m23;
        float m22 = m0.m02 * m1.m20 + m0.m12 * m1.m21 + m0.m22 * m1.m22 + m0.m32 * m1.m23;
        float m23 = m0.m03 * m1.m20 + m0.m13 * m1.m21 + m0.m23 * m1.m22 + m0.m33 * m1.m23;

        float m30 = m0.m00 * m1.m30 + m0.m10 * m1.m31 + m0.m20 * m1.m32 + m0.m30 * m1.m33;
        float m31 = m0.m01 * m1.m30 + m0.m11 * m1.m31 + m0.m21 * m1.m32 + m0.m31 * m1.m33;
        float m32 = m0.m02 * m1.m30 + m0.m12 * m1.m31 + m0.m22 * m1.m32 + m0.m32 * m1.m33;
        float m33 = m0.m03 * m1.m30 + m0.m13 * m1.m31 + m0.m23 * m1.m32 + m0.m33 * m1.m33;

        r.m00 = m00;
        r.m01 = m01;
        r.m02 = m02;
        r.m03 = m03;
        r.m10 = m10;
        r.m11 = m11;
        r.m12 = m12;
        r.m13 = m13;
        r.m20 = m20;
        r.m21 = m21;
        r.m22 = m22;
        r.m23 = m23;
        r.m30 = m30;
        r.m31 = m31;
        r.m32 = m32;
        r.m33 = m33;

        return r;
    }


    public static Vector4f mul(Matrix4f m, Vector4f v) {
        return mul(m, v, new Vector4f());
    }

    public static Vector4f mul(Matrix4f m, Vector4f v, Vector4f r) {

        float x = m.m00 * v.x + m.m10 * v.y + m.m20 * v.z + m.m30 * v.w;
        float y = m.m01 * v.x + m.m11 * v.y + m.m21 * v.z + m.m31 * v.w;
        float z = m.m02 * v.x + m.m12 * v.y + m.m22 * v.z + m.m32 * v.w;
        float w = m.m03 * v.x + m.m13 * v.y + m.m23 * v.z + m.m33 * v.w;

        r.x = x;
        r.y = y;
        r.z = z;
        r.w = w;

        return r;
    }


    public static Matrix4f rotationX(float rad) {
        return rotationX(rad, new Matrix4f());
    }

    public static Matrix4f rotationX(float rad, Matrix4f r) {
        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);

        r.m00 = 1.0f;
        r.m01 = 0.0f;
        r.m02 = 0.0f;
        r.m03 = 0.0f;

        r.m10 = 0.0f;
        r.m11 = c;
        r.m12 = s;
        r.m13 = 0.0f;

        r.m20 = 0.0f;
        r.m21 = -s;
        r.m22 = c;
        r.m23 = 0.0f;

        r.m30 = 0.0f;
        r.m31 = 0.0f;
        r.m32 = 0.0f;
        r.m33 = 1.0f;

        return r;
    }


    public static Matrix4f rotationY(float rad) {
        return rotationY(rad, new Matrix4f());
    }

    public static Matrix4f rotationY(float rad, Matrix4f r) {
        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);

        r.m00 = c;
        r.m01 = 0.0f;
        r.m02 = -s;
        r.m03 = 0.0f;

        r.m10 = 0.0f;
        r.m11 = 1.0f;
        r.m12 = 0.0f;
        r.m13 = 0.0f;

        r.m20 = s;
        r.m21 = 0.0f;
        r.m22 = c;
        r.m23 = 0.0f;

        r.m30 = 0.0f;
        r.m31 = 0.0f;
        r.m32 = 0.0f;
        r.m33 = 1.0f;
        return r;
    }


    public static Matrix4f rotationZ(float rad) {
        return rotationZ(rad, new Matrix4f());
    }

    public static Matrix4f rotationZ(float rad, Matrix4f r) {
        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);

        r.m00 = c;
        r.m01 = s;
        r.m02 = 0.0f;
        r.m03 = 0.0f;

        r.m10 = -s;
        r.m11 = c;
        r.m12 = 0.0f;
        r.m13 = 0.0f;


        r.m20 = 0.0f;
        r.m21 = 0.0f;
        r.m22 = 1.0f;
        r.m23 = 0.0f;

        r.m30 = 0.0f;
        r.m31 = 0.0f;
        r.m32 = 0.0f;
        r.m33 = 1.0f;
        return r;
    }


    public static Matrix4f rotation(Vector3f axis, float rad) {
        return rotation(axis.x, axis.y, axis.z, rad, new Matrix4f());
    }

    public static Matrix4f rotation(float x, float y, float z, float rad) {
        return rotation(x, y, z, rad, new Matrix4f());
    }

    public static Matrix4f rotation(Vector3f axis, float rad, Matrix4f r) {
        return rotation(axis.x, axis.y, axis.z, rad, r);
    }

    public static Matrix4f rotation(float x, float y, float z, float rad, Matrix4f r) {

        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);


        float ic = 1.0f - c;
        r.m00 = x * x * ic + c;
        r.m10 = x * y * ic - z * s;
        r.m20 = x * z * ic + y * s;
        r.m30 = 0.0f;

        r.m01 = y * x * ic + z * s;
        r.m11 = y * y * ic + c;
        r.m21 = y * z * ic - x * s;
        r.m31 = 0.0f;

        r.m02 = z * x * ic - y * s;
        r.m12 = z * y * ic + x * s;
        r.m22 = z * z * ic + c;
        r.m32 = 0.0f;

        r.m03 = 0.0f;
        r.m13 = 0.0f;
        r.m23 = 0.0f;
        r.m33 = 1.0f;

        return r;
    }


}
