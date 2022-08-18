package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathfTest {

    @Test
    public void testClamp_InRange() {
        double value = 20.0;
        double clampedValue = Mathf.clamp(value, 15.0, 25.0);
        assertEquals(clampedValue, 20.0);
    }

    @Test
    public void testClamp_Less() {
        double value = 10.0;
        double clampedValue = Mathf.clamp(value, 15.0, 25.0);
        assertEquals(clampedValue, 15.0);
    }

    @Test
    public void testClamp_Greater() {
        double value = 30.0;
        double clampedValue = Mathf.clamp(value, 15.0, 25.0);
        assertEquals(clampedValue, 25.0);
    }

    @Test
    public void testClamp01_InRange() {
        double value = 0.5;
        double clampedValue = Mathf.clamp01(value);
        assertEquals(clampedValue, 0.5);
    }

    @Test
    public void testClamp01_Less() {
        double value = -1.0;
        double clampedValue = Mathf.clamp01(value);
        assertEquals(clampedValue, 0.0);
    }

    @Test
    public void testClamp01_Greater() {
        double value = 1.5;
        double clampedValue = Mathf.clamp01(value);
        assertEquals(clampedValue, 1.0);
    }


    @Test
    public void testClampf_InRange() {
        float value = 20.0f;
        float clampedValue = Mathf.clamp(value, 15.0f, 25.0f);
        assertEquals(clampedValue, 20.0f);
    }

    @Test
    public void testClampf_Less() {
        float value = 10;
        float clampedValue = Mathf.clamp(value, 15.0f, 25.0f);
        assertEquals(clampedValue, 15.0f);
    }

    @Test
    public void testClampf_Greater() {
        float value = 30.0f;
        float clampedValue = Mathf.clamp(value, 15.0f, 25.0f);
        assertEquals(clampedValue, 25.0f);
    }

    @Test
    public void testClamp01f_InRange() {
        float value = 0.5f;
        float clampedValue = Mathf.clamp01(value);
        assertEquals(clampedValue, 0.5f);
    }

    @Test
    public void testClamp01f_Less() {
        float value = -1.0f;
        float clampedValue = Mathf.clamp01(value);
        assertEquals(clampedValue, 0.0f);
    }

    @Test
    public void testClamp01f_Greater() {
        float value = 1.5f;
        float clampedValue = Mathf.clamp01(value);
        assertEquals(clampedValue, 1.0f);
    }

}
