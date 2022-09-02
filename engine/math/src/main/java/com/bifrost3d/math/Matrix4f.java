package com.bifrost3d.math;

@SuppressWarnings({"Duplicates", "squid:S1104", "squid:S107"})
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

    public void set(Matrix4f other) {
        this.m00 = other.m00;
        this.m01 = other.m01;
        this.m02 = other.m02;
        this.m03 = other.m03;

        this.m10 = other.m10;
        this.m11 = other.m11;
        this.m12 = other.m12;
        this.m13 = other.m13;

        this.m20 = other.m20;
        this.m21 = other.m21;
        this.m22 = other.m22;
        this.m23 = other.m23;

        this.m30 = other.m30;
        this.m31 = other.m31;
        this.m32 = other.m32;
        this.m33 = other.m33;
    }

    public void writeToBuffer(float[] buffer, int offset) {
        buffer[offset] = this.m00;
        buffer[offset + 1] = this.m01;
        buffer[offset + 2] = this.m02;
        buffer[offset + 3] = this.m03;

        buffer[offset + 4] = this.m10;
        buffer[offset + 5] = this.m11;
        buffer[offset + 6] = this.m12;
        buffer[offset + 7] = this.m13;

        buffer[offset + 8] = this.m20;
        buffer[offset + 9] = this.m21;
        buffer[offset + 10] = this.m22;
        buffer[offset + 11] = this.m23;

        buffer[offset + 12] = this.m30;
        buffer[offset + 13] = this.m31;
        buffer[offset + 14] = this.m32;
        buffer[offset + 15] = this.m33;

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
        r.w = m13;
        return r;
    }

    public Vector4f getZAxis() {
        return getZAxis(new Vector4f());
    }

    public Vector4f getZAxis(Vector4f r) {
        r.x = m20;
        r.y = m21;
        r.z = m22;
        r.w = m23;
        return r;
    }


    public Vector4f getTranslation() {
        return getTranslation(new Vector4f());
    }

    public Vector4f getTranslation(Vector4f r) {
        r.x = m30;
        r.y = m31;
        r.z = m32;
        r.w = m33;
        return r;
    }

    public Matrix4f inverted() {
        return inverted(new Matrix4f());
    }

    public Matrix4f inverted(Matrix4f r) {
        float v0 = m20 * m31 - m21 * m30;
        float v1 = m20 * m32 - m22 * m30;
        float v2 = m20 * m33 - m23 * m30;
        float v3 = m21 * m32 - m22 * m31;
        float v4 = m21 * m33 - m23 * m31;
        float v5 = m22 * m33 - m23 * m32;

        float t00 = (v5 * m11 - v4 * m12 + v3 * m13);
        float t10 = -(v5 * m10 - v2 * m12 + v1 * m13);
        float t20 = (v4 * m10 - v2 * m11 + v0 * m13);
        float t30 = -(v3 * m10 - v1 * m11 + v0 * m12);

        float invDet = 1.0f / (t00 * m00 + t10 * m01 + t20 * m02 + t30 * m03);

        float lm00 = t00 * invDet;
        float lm10 = t10 * invDet;
        float lm20 = t20 * invDet;
        float lm30 = t30 * invDet;

        float lm01 = -(v5 * m01 - v4 * m02 + v3 * m03) * invDet;
        float lm11 = (v5 * m00 - v2 * m02 + v1 * m03) * invDet;
        float lm21 = -(v4 * m00 - v2 * m01 + v0 * m03) * invDet;
        float lm31 = (v3 * m00 - v1 * m01 + v0 * m02) * invDet;

        v0 = m10 * m31 - m11 * m30;
        v1 = m10 * m32 - m12 * m30;
        v2 = m10 * m33 - m13 * m30;
        v3 = m11 * m32 - m12 * m31;
        v4 = m11 * m33 - m13 * m31;
        v5 = m12 * m33 - m13 * m32;

        float lm02 = (v5 * m01 - v4 * m02 + v3 * m03) * invDet;
        float lm12 = -(v5 * m00 - v2 * m02 + v1 * m03) * invDet;
        float lm22 = (v4 * m00 - v2 * m01 + v0 * m03) * invDet;
        float lm32 = -(v3 * m00 - v1 * m01 + v0 * m02) * invDet;

        v0 = m21 * m10 - m20 * m11;
        v1 = m22 * m10 - m20 * m12;
        v2 = m23 * m10 - m20 * m13;
        v3 = m22 * m11 - m21 * m12;
        v4 = m23 * m11 - m21 * m13;
        v5 = m23 * m12 - m22 * m13;

        float lm03 = -(v5 * m01 - v4 * m02 + v3 * m03) * invDet;
        float lm13 = (v5 * m00 - v2 * m02 + v1 * m03) * invDet;
        float lm23 = -(v4 * m00 - v2 * m01 + v0 * m03) * invDet;
        float lm33 = (v3 * m00 - v1 * m01 + v0 * m02) * invDet;


        r.m00 = lm00;
        r.m01 = lm01;
        r.m02 = lm02;
        r.m03 = lm03;

        r.m10 = lm10;
        r.m11 = lm11;
        r.m12 = lm12;
        r.m13 = lm13;

        r.m20 = lm20;
        r.m21 = lm21;
        r.m22 = lm22;
        r.m23 = lm23;

        r.m30 = lm30;
        r.m31 = lm31;
        r.m32 = lm32;
        r.m33 = lm33;

        return r;
    }

    public void invert() {
        inverted(this);
    }

    public Matrix4f transposed() {
        return transposed(new Matrix4f());
    }

    public Matrix4f transposed(Matrix4f r) {
        float lm00 = m00;
        float lm01 = m01;
        float lm02 = m02;
        float lm03 = m03;

        float lm10 = m10;
        float lm11 = m11;
        float lm12 = m12;
        float lm13 = m13;

        float lm20 = m20;
        float lm21 = m21;
        float lm22 = m22;
        float lm23 = m23;

        float lm30 = m30;
        float lm31 = m31;
        float lm32 = m32;
        float lm33 = m33;

        r.m00 = lm00;
        r.m01 = lm10;
        r.m02 = lm20;
        r.m03 = lm30;

        r.m10 = lm01;
        r.m11 = lm11;
        r.m12 = lm21;
        r.m13 = lm31;

        r.m20 = lm02;
        r.m21 = lm12;
        r.m22 = lm22;
        r.m23 = lm32;

        r.m30 = lm03;
        r.m31 = lm13;
        r.m32 = lm23;
        r.m33 = lm33;

        return r;
    }

    public void transpose() {
        transposed(this);
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


    public static Matrix4f translation (float x, float y, float z) {
        return translation(x, y, z, new Matrix4f());
    }

    public static Matrix4f translation (float x, float y, float z, Matrix4f r) {
        r.m00 = 1.0f;
        r.m01 = 0.0f;
        r.m02 = 0.0f;
        r.m03 = 0.0f;

        r.m10 = 0.0f;
        r.m11 = 1.0f;
        r.m12 = 0.0f;
        r.m13 = 0.0f;

        r.m20 = 0.0f;
        r.m21 = 0.0f;
        r.m22 = 1.0f;
        r.m23 = 0.0f;

        r.m30 = x;
        r.m31 = y;
        r.m32 = z;
        r.m33 = 1.0f;
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


    public static Matrix4f projection (float l, float r, float b, float t, float n, float f) {
        return projection(l, r, b, t, n, f, new Matrix4f());
    }

    public static Matrix4f projection (float l, float r, float b, float t, float n, float f, Matrix4f res) {

        float n2 = n*2.0f;
        float dx = r - l;
        float dy = t - b;
        float dz = f - n;

        float sx = r + l;
        float sy = t + b;
        float sz = f + n;

        res.m00 = n2 / dx;
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;

        res.m10 = 0.0f;
        res.m11 = n2 / dy;
        res.m12 = 0.0f;
        res.m13 = 0.0f;

        res.m20 = sx / dx;
        res.m21 = sy / dy;
        res.m22 = -sz / dz;
        res.m23 = -1.0f;

        res.m30 = 0.0f;
        res.m31 = 0.0f;
        res.m32 = -2.0f * f * n / dz;
        res.m33 = 0.0f;


        return res;
    }



    public static Matrix4f lookAt (Vector3f from, Vector3f at, Vector3f up) {
        return lookAt(from, at, up, new Matrix4f());
    }


    public static Matrix4f lookAt (Vector3f from, Vector3f at, Vector3f up, Matrix4f r) {
        Vector3f zAxis = Vector3f.sub (from, at);
        zAxis.normalize();
        Vector3f xAxis = Vector3f.cross(up, zAxis);
        xAxis.normalize();
        Vector3f yAxis = Vector3f.cross(zAxis, xAxis);


        r.m00 = xAxis.x;
        r.m01 = yAxis.x;
        r.m02 = zAxis.x;
        r.m03 = 0.0f;

        r.m10 = xAxis.y;
        r.m11 = yAxis.y;
        r.m12 = zAxis.y;
        r.m13 = 0.0f;

        r.m20 = xAxis.z;
        r.m21 = yAxis.z;
        r.m22 = zAxis.z;
        r.m23 = 0.0f;

        r.m30 = -Vector3f.dot(xAxis, from);
        r.m31 = -Vector3f.dot(yAxis, from);
        r.m32 = -Vector3f.dot(zAxis, from);
        r.m33 = 1.0f;

        return r;
    }
}
