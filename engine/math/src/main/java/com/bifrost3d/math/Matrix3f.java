package com.bifrost3d.math;

@SuppressWarnings({"squid:S1104", "squid:S107"})
public final class Matrix3f {

    public float m00;
    public float m01;
    public float m02;
    public float m10;
    public float m11;
    public float m12;
    public float m20;
    public float m21;
    public float m22;

    public Matrix3f() {
        m00 = 1.0f;
        m01 = 0.0f;
        m02 = 0.0f;
        m10 = 0.0f;
        m11 = 1.0f;
        m12 = 0.0f;
        m20 = 0.0f;
        m21 = 0.0f;
        m22 = 1.0f;
    }

    public Matrix3f(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }

    public void setIdentity() {
        m00 = 1.0f;
        m01 = 0.0f;
        m02 = 0.0f;
        m10 = 0.0f;
        m11 = 1.0f;
        m12 = 0.0f;
        m20 = 0.0f;
        m21 = 0.0f;
        m22 = 1.0f;
    }

    public void writeToBuffer (float[] buffer, int offset) {
        buffer[offset] = this.m00;
        buffer[offset+1] = this.m01;
        buffer[offset+2] = this.m02;

        buffer[offset+3] = this.m10;
        buffer[offset+4] = this.m11;
        buffer[offset+5] = this.m12;

        buffer[offset+6] = this.m20;
        buffer[offset+7] = this.m21;
        buffer[offset+8] = this.m22;
    }

    public Vector3f getXAxis() {
        return getXAxis(new Vector3f());
    }

    public Vector3f getXAxis(Vector3f r) {
        r.x = m00;
        r.y = m01;
        r.z = m02;
        return r;
    }

    public Vector3f getYAxis() {
        return getYAxis(new Vector3f());
    }

    public Vector3f getYAxis(Vector3f r) {
        r.x = m10;
        r.y = m11;
        r.z = m12;
        return r;
    }

    public Vector3f getZAxis() {
        return getZAxis(new Vector3f());
    }

    public Vector3f getZAxis(Vector3f r) {
        r.x = m20;
        r.y = m21;
        r.z = m22;
        return r;
    }


    public static Matrix3f mul(Matrix3f m0, Matrix3f m1) {
        return mul(m0, m1, new Matrix3f());
    }

    public static Matrix3f mul(Matrix3f m0, Matrix3f m1, Matrix3f r) {

        float m00 = m0.m00 * m1.m00 + m0.m10 * m1.m01 + m0.m20 * m1.m02;
        float m01 = m0.m01 * m1.m00 + m0.m11 * m1.m01 + m0.m21 * m1.m02;
        float m02 = m0.m02 * m1.m00 + m0.m12 * m1.m01 + m0.m22 * m1.m02;

        float m10 = m0.m00 * m1.m10 + m0.m10 * m1.m11 + m0.m20 * m1.m12;
        float m11 = m0.m01 * m1.m10 + m0.m11 * m1.m11 + m0.m21 * m1.m12;
        float m12 = m0.m02 * m1.m10 + m0.m12 * m1.m11 + m0.m22 * m1.m12;

        float m20 = m0.m00 * m1.m20 + m0.m10 * m1.m21 + m0.m20 * m1.m22;
        float m21 = m0.m01 * m1.m20 + m0.m11 * m1.m21 + m0.m21 * m1.m22;
        float m22 = m0.m02 * m1.m20 + m0.m12 * m1.m21 + m0.m22 * m1.m22;

        r.m00 = m00;
        r.m01 = m01;
        r.m02 = m02;
        r.m10 = m10;
        r.m11 = m11;
        r.m12 = m12;
        r.m20 = m20;
        r.m21 = m21;
        r.m22 = m22;

        return r;
    }


    public static Vector3f mul(Matrix3f m, Vector3f v) {
        return mul(m, v, new Vector3f());
    }

    public static Vector3f mul(Matrix3f m, Vector3f v, Vector3f r) {

        float x = m.m00 * v.x + m.m10 * v.y + m.m20 * v.z;
        float y = m.m01 * v.x + m.m11 * v.y + m.m21 * v.z;
        float z = m.m02 * v.x + m.m12 * v.y + m.m22 * v.z;

        r.x = x;
        r.y = y;
        r.z = z;

        return r;
    }


    public static Matrix3f rotationX(float rad) {
        return rotationX(rad, new Matrix3f());
    }

    public static Matrix3f rotationX(float rad, Matrix3f r) {
        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);

        r.m00 = 1.0f;
        r.m01 = 0.0f;
        r.m02 = 0.0f;

        r.m10 = 0.0f;
        r.m11 = c;
        r.m12 = s;

        r.m20 = 0.0f;
        r.m21 = -s;
        r.m22 = c;

        return r;
    }


    public static Matrix3f rotationY(float rad) {
        return rotationY(rad, new Matrix3f());
    }

    public static Matrix3f rotationY(float rad, Matrix3f r) {
        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);

        r.m00 = c;
        r.m01 = 0.0f;
        r.m02 = -s;

        r.m10 = 0.0f;
        r.m11 = 1.0f;
        r.m12 = 0.0f;

        r.m20 = s;
        r.m21 = 0.0f;
        r.m22 = c;

        return r;
    }


    public static Matrix3f rotationZ(float rad) {
        return rotationZ(rad, new Matrix3f());
    }

    public static Matrix3f rotationZ(float rad, Matrix3f r) {
        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);

        r.m00 = c;
        r.m01 = s;
        r.m02 = 0.0f;

        r.m10 = -s;
        r.m11 = c;
        r.m12 = 0.0f;

        r.m20 = 0.0f;
        r.m21 = 0.0f;
        r.m22 = 1.0f;

        return r;
    }


    public static Matrix3f rotation(Vector3f axis, float rad) {
        return rotation(axis.x, axis.y, axis.z, rad, new Matrix3f());
    }

    public static Matrix3f rotation(float x, float y, float z, float rad) {
        return rotation(x, y, z, rad, new Matrix3f());
    }

    public static Matrix3f rotation(Vector3f axis, float rad, Matrix3f r) {
        return rotation(axis.x, axis.y, axis.z, rad, r);
    }

    public static Matrix3f rotation(float x, float y, float z, float rad, Matrix3f r) {

        float c = Mathf.cos(rad);
        float s = Mathf.sin(rad);


        float ic = 1.0f - c;
        r.m00 = x * x * ic + c;
        r.m10 = x * y * ic - z * s;
        r.m20 = x * z * ic + y * s;

        r.m01 = y * x * ic + z * s;
        r.m11 = y * y * ic + c;
        r.m21 = y * z * ic - x * s;

        r.m02 = z * x * ic - y * s;
        r.m12 = z * y * ic + x * s;
        r.m22 = z * z * ic + c;

        return r;
    }


}
