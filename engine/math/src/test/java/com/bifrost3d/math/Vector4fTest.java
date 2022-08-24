package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("squid:S5863")
class Vector4fTest {


    @Test
    void testIdentity () {
        Vector4f v = new Vector4f();
        assertEquals(v, v);
    }

    @Test
    void testEquality () {
        Vector4f v0 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        assertEquals(v0, v1);
    }

    @Test
    void testNotEquality () {
        Vector4f v0 = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        Vector4f v1 = new Vector4f(0.0f, 1.0f, 1.0f, 1.0f);
        Vector4f v2 = new Vector4f(1.0f, 0.0f, 1.0f, 1.0f);
        Vector4f v3 = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
        Vector4f v4 = new Vector4f(1.0f, 1.0f, 0.0f, 0.0f);
        assertNotEquals(v0, v1);
        assertNotEquals(v0, v2);
        assertNotEquals(v0, v3);
        assertNotEquals(v0, v4);
    }

    @Test
    void testIdentityStatic () {
        Vector4f v = new Vector4f();
        assertTrue(Vector4f.equals(v, v));
    }

    @Test
    void testEqualityStatic () {
        Vector4f v0 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        assertTrue(Vector4f.equals(v0, v1));
    }

    @Test
    void testNotEqualityStatic () {
        Vector4f v0 = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        Vector4f v1 = new Vector4f(0.0f, 1.0f, 1.0f, 1.0f);
        Vector4f v2 = new Vector4f(1.0f, 0.0f, 1.0f, 1.0f);
        Vector4f v3 = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
        Vector4f v4 = new Vector4f(1.0f, 1.0f, 0.0f, 0.0f);
        assertFalse(Vector4f.equals(v0, v1));
        assertFalse(Vector4f.equals(v0, v2));
        assertFalse(Vector4f.equals(v0, v3));
        assertFalse(Vector4f.equals(v0, v4));
    }


    @Test
    void testHashEqual () {
        Vector4f v0 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        assertEquals(v0.hashCode(), v1.hashCode());
    }



    @Test
    void testHashNotEqual () {
        Vector4f v0 = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        Vector4f v1 = new Vector4f(0.0f, 1.0f, 1.0f, 1.0f);
        Vector4f v2 = new Vector4f(1.0f, 0.0f, 1.0f, 1.0f);
        Vector4f v3 = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
        Vector4f v4 = new Vector4f(1.0f, 1.0f, 0.0f, 0.0f);
        assertNotEquals(v0.hashCode(), v1.hashCode());
        assertNotEquals(v0.hashCode(), v2.hashCode());
        assertNotEquals(v0.hashCode(), v3.hashCode());
        assertNotEquals(v0.hashCode(), v4.hashCode());
    }


    @Test
    void testAdd() {
        Vector4f v0 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v1 = new Vector4f(2.0f, 3.0f, 4.0f, 5.0f);

        Vector4f r = Vector4f.add(v0, v1);
        assertEquals(new Vector4f(3.0f, 5.0f, 7.0f, 9.0f), r);
    }

    @Test
    void testSub() {
        Vector4f v0 = new Vector4f(4.0f, 8.0f, 16.0f, 32.0f);
        Vector4f v1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);

        Vector4f r = Vector4f.sub(v0, v1);
        assertEquals(new Vector4f(3.0f, 6.0f, 13.0f, 28.0f), r);
    }

    @Test
    void testMul() {
        Vector4f v0 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v1 = new Vector4f(2.0f, 3.0f, 4.0f, 5.0f);

        Vector4f r0 = Vector4f.mul(v0, v1);
        Vector4f r1 = Vector4f.mul(4.0f, v1);
        Vector4f r2 = Vector4f.mul(v0, 6.0f);
        assertEquals(new Vector4f(2.0f, 6.0f, 12.0f, 20.0f), r0);
        assertEquals(new Vector4f(8.0f, 12.0f, 16.0f, 20.0f), r1);
        assertEquals(new Vector4f(6.0f, 12.0f, 18.0f, 24.0f), r2);
    }

    @Test
    void testDiv() {
        Vector4f v0 = new Vector4f(2.0f, 6.0f, 12.0f, 24.0f);
        Vector4f v1 = new Vector4f(2.0f, 3.0f, 4.0f, 6.0f);

        Vector4f r0 = Vector4f.div(v0, v1);
        Vector4f r1 = Vector4f.div(v0, 2.0f);
        Vector4f r2 = Vector4f.div(30.0f, v1);
        assertEquals(new Vector4f(1.0f, 2.0f, 3.0f, 4.0f), r0);
        assertEquals(new Vector4f(1.0f, 3.0f, 6.0f, 12.0f), r1);
        assertEquals(new Vector4f(15.0f, 10.0f, 7.5f, 5.0f), r2);
    }

    @Test
    void testDot () {
        Vector4f v = new Vector4f(2.0f, 4.0f, 6.0f, 8.0f);

        assertEquals(120.0f, v.dot());
    }

    @Test
    void testLength () {
        Vector4f v = new Vector4f(2.0f, 4.0f, 6.0f, 8.0f);

        assertEquals((float)Math.sqrt(120.0f), v.length());
    }

    @Test
    void testDotStatic () {
        Vector4f v0 = new Vector4f(2.0f, 4.0f, 6.0f, 8.0f);
        Vector4f v1 = new Vector4f(3.0f, 5.0f, 7.0f, 9.0f);

        // 2*3=6
        // 4*5=20
        // 6*7=42
        // 8*9=72

        assertEquals(120.0f, Vector4f.dot(v0));
        assertEquals(140.0f, Vector4f.dot(v0, v1));
    }

    @Test
    void testLengthStatic () {
        Vector4f v = new Vector4f(2.0f, 4.0f, 6.0f, 8.0f);

        assertEquals((float)Math.sqrt(120.0f), Vector4f.length(v));
    }

    @Test
    void testNormalize () {
        Vector4f x10 = new Vector4f(10.0f, 0.0f, 0.0f, 0.0f);
        Vector4f y10 = new Vector4f(0.0f, 10.0f, 0.0f, 0.0f);
        Vector4f z10 = new Vector4f(0.0f, 0.0f, 10.0f, 0.0f);
        Vector4f w10 = new Vector4f(0.0f, 0.0f, 0.0f, 10.0f);

        x10.normalize();
        y10.normalize();
        z10.normalize();
        w10.normalize();

        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), x10);
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), y10);
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), z10);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), w10);
    }

    @Test
    void testNormalized () {
        Vector4f x10 = new Vector4f(10.0f, 0.0f, 0.0f, 0.0f);
        Vector4f y10 = new Vector4f(0.0f, 10.0f, 0.0f, 0.0f);
        Vector4f z10 = new Vector4f(0.0f, 0.0f, 10.0f, 0.0f);
        Vector4f w10 = new Vector4f(0.0f, 0.0f, 0.0f, 10.0f);

        Vector4f x1 = x10.normalized();
        Vector4f y1 = y10.normalized();
        Vector4f z1 = z10.normalized();
        Vector4f w1 = w10.normalized();

        assertEquals(new Vector4f(10.0f, 0.0f, 0.0f, 0.0f), x10);
        assertEquals(new Vector4f(0.0f, 10.0f, 0.0f, 0.0f), y10);
        assertEquals(new Vector4f(0.0f, 0.0f, 10.0f, 0.0f), z10);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 10.0f), w10);
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), x1);
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), y1);
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), z1);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), w1);
    }


    @Test
    void testToString () {
        float x = 20.0f;
        float y = 458.023f;
        float z = 232900.23f;
        float w = 1230.23f;
        Vector4f v = new Vector4f(x, y, z, w);

        assertEquals(x + ", " + y + ", " + z + ", " + w, v.toString());
    }

}
