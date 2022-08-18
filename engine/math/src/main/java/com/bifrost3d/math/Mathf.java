package com.bifrost3d.math;

@SuppressWarnings("unused")
public abstract class Mathf {

    private Mathf() {

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


}
