package com.bifrost3d.math;

@SuppressWarnings("unused")
public abstract class Mathf {

    private static final float FLOAT_EQUALITY_THRESHOLD = 1e-6f;

    public static final float PI = 3.14159265358979323846f;

    private Mathf() {

    }

    public static float rad2Deg(float rad) {
        return rad * 180.0f / PI;
    }

    public static float deg2Rad(float rad) {
        return rad * PI / 180.0f;
    }


    public static float cos(float rad) {
        return (float) Math.cos(rad);
    }

    public static float sin(float rad) {
        return (float) Math.sin(rad);
    }

    public static float acos(float rad) {
        return (float) Math.acos(rad);
    }

    public static float asin(float rad) {
        return (float) Math.asin(rad);
    }

    public static float tan(float rad) {
        return (float) Math.tan(rad);
    }

    public static float atan(float rad) {
        return (float) Math.atan(rad);
    }


    public static float clamp(float value, float min, float max) {
        value = Math.max(value, min);
        value = Math.min(value, max);
        return value;
    }

    public static float clamp01(float value) {
        return clamp(value, 0.0f, 1.0f);
    }

    public static double clamp(double value, double min, double max) {
        value = Math.max(value, min);
        value = Math.min(value, max);
        return value;
    }


    public static double clamp01(double value) {
        return clamp(value, 0.0, 1.0);
    }


    public static boolean equals(float v0, float v1) {
        return Math.abs(v0 - v1) < FLOAT_EQUALITY_THRESHOLD;
    }


    public static float sqrt(float v) {
        return (float) Math.sqrt(v);
    }


    public static int numPowerOfTwo (int size) {
        int num = 0;
        while (size > 0) {
            size >>= 1;
            num++;
        }
        return num;
    }

}
