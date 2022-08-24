package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("squid:S5976")
class MathfTest {

    @Test
    void testClamp_InRange() {
        double value = 20.0;
        double clampedValue = Mathf.clamp(value, 15.0, 25.0);
        assertEquals(20.0, clampedValue);
    }

    @Test
    void testClamp_Less() {
        double value = 10.0;
        double clampedValue = Mathf.clamp(value, 15.0, 25.0);
        assertEquals(15.0, clampedValue);
    }

    @Test
    void testClamp_Greater() {
        double value = 30.0;
        double clampedValue = Mathf.clamp(value, 15.0, 25.0);
        assertEquals(25.0, clampedValue);
    }

    

    @Test
    void testClamp01_InRange() {
        double value = 0.5;
        double clampedValue = Mathf.clamp01(value);
        assertEquals(0.5, clampedValue);
    }

    @Test
    void testClamp01_Less() {
        double value = -1.0;
        double clampedValue = Mathf.clamp01(value);
        assertEquals(0.0, clampedValue);
    }

    @Test
    void testClamp01_Greater() {
        double value = 1.5;
        double clampedValue = Mathf.clamp01(value);
        assertEquals(1.0, clampedValue);
    }


    @Test
    void testClampf_InRange() {
        float value = 20.0f;
        float clampedValue = Mathf.clamp(value, 15.0f, 25.0f);
        assertEquals(20.0f, clampedValue);
    }

    @Test
    void testClampf_Less() {
        float value = 10;
        float clampedValue = Mathf.clamp(value, 15.0f, 25.0f);
        assertEquals(15.0f, clampedValue);
    }

    @Test
    void testClampf_Greater() {
        float value = 30.0f;
        float clampedValue = Mathf.clamp(value, 15.0f, 25.0f);
        assertEquals(25.0f, clampedValue);
    }

    @Test
    void testClamp01f_InRange() {
        float value = 0.5f;
        float clampedValue = Mathf.clamp01(value);
        assertEquals(0.5f, clampedValue);
    }

    @Test
    void testClamp01f_Less() {
        float value = -1.0f;
        float clampedValue = Mathf.clamp01(value);
        assertEquals(0.0f, clampedValue);
    }

    @Test
    void testClamp01f_Greater() {
        float value = 1.5f;
        float clampedValue = Mathf.clamp01(value);
        assertEquals(1.0f, clampedValue);
    }

}
