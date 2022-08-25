package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("squid:S5863")
class Vector2fTest {


    @Test
    void testIdentity () {
        Vector2f v = new Vector2f();
        assertEquals(v, v);
    }

    @Test
    void testEquality () {
        Vector2f v0 = new Vector2f(1.0f, 2.0f);
        Vector2f v1 = new Vector2f(1.0f, 2.0f);
        assertEquals(v0, v1);
    }

    @Test
    void testNotEquality () {
        Vector2f v0 = new Vector2f(1.0f, 1.0f);
        Vector2f v1 = new Vector2f(0.0f, 1.0f);
        Vector2f v2 = new Vector2f(1.0f, 0.0f);
        assertNotEquals(v0, v1);
        assertNotEquals(v0, v2);
    }

    @Test
    void testIdentityStatic () {
        Vector2f v = new Vector2f();
        assertTrue(Vector2f.equals(v, v));
    }

    @Test
    void testEqualityStatic () {
        Vector2f v0 = new Vector2f(1.0f, 2.0f);
        Vector2f v1 = new Vector2f(1.0f, 2.0f);
        assertTrue(Vector2f.equals(v0, v1));
    }

    @Test
    void testNotEqualityStatic () {
        Vector2f v0 = new Vector2f(1.0f, 1.0f);
        Vector2f v1 = new Vector2f(0.0f, 1.0f);
        Vector2f v2 = new Vector2f(1.0f, 0.0f);
        assertFalse(Vector2f.equals(v0, v1));
        assertFalse(Vector2f.equals(v0, v2));
    }


    @Test
    void testHashEqual () {
        Vector2f v0 = new Vector2f(1.0f, 2.0f);
        Vector2f v1 = new Vector2f(1.0f, 2.0f);
        assertEquals(v0.hashCode(), v1.hashCode());
    }



    @Test
    void testHashNotEqual () {
        Vector2f v0 = new Vector2f(1.0f, 1.0f);
        Vector2f v1 = new Vector2f(0.0f, 1.0f);
        Vector2f v2 = new Vector2f(1.0f, 0.0f);
        assertNotEquals(v0.hashCode(), v1.hashCode());
        assertNotEquals(v0.hashCode(), v2.hashCode());
    }


    @Test
    void testAdd() {
        Vector2f v0 = new Vector2f(1.0f, 2.0f);
        Vector2f v1 = new Vector2f(2.0f, 3.0f);

        Vector2f r = Vector2f.add(v0, v1);
        assertEquals(new Vector2f(3.0f, 5.0f), r);
    }

    @Test
    void testSub() {
        Vector2f v0 = new Vector2f(4.0f, 8.0f);
        Vector2f v1 = new Vector2f(1.0f, 2.0f);

        Vector2f r = Vector2f.sub(v0, v1);
        assertEquals(new Vector2f(3.0f, 6.0f), r);
    }

    @Test
    void testMul() {
        Vector2f v0 = new Vector2f(1.0f, 2.0f);
        Vector2f v1 = new Vector2f(2.0f, 3.0f);

        Vector2f r0 = Vector2f.mul(v0, v1);
        Vector2f r1 = Vector2f.mul(4.0f, v1);
        Vector2f r2 = Vector2f.mul(v0, 6.0f);
        assertEquals(new Vector2f(2.0f, 6.0f), r0);
        assertEquals(new Vector2f(8.0f, 12.0f), r1);
        assertEquals(new Vector2f(6.0f, 12.0f), r2);
    }

    @Test
    void testDiv() {
        Vector2f v0 = new Vector2f(2.0f, 6.0f);
        Vector2f v1 = new Vector2f(2.0f, 3.0f);

        Vector2f r0 = Vector2f.div(v0, v1);
        Vector2f r1 = Vector2f.div(v0, 2.0f);
        Vector2f r2 = Vector2f.div(30.0f, v1);
        assertEquals(new Vector2f(1.0f, 2.0f), r0);
        assertEquals(new Vector2f(1.0f, 3.0f), r1);
        assertEquals(new Vector2f(15.0f, 10.0f), r2);
    }

    @Test
    void testDot () {
        Vector2f v = new Vector2f(2.0f, 4.0f);

        assertEquals(20.0f, v.dot());

        float dotSameDirection = Vector2f.dot(new Vector2f(1.0f, 0.0f), new Vector2f(1.0f, 0.0f));
        float dotOrthogonal1 = Vector2f.dot(new Vector2f(1.0f, 0.0f), new Vector2f(0.0f, 1.0f));
        float dotOrthogonal2 = Vector2f.dot(new Vector2f(1.0f, 0.0f), new Vector2f(0.0f, -1.0f));
        float dotBackDirection = Vector2f.dot(new Vector2f(1.0f, 0.0f), new Vector2f(-1.0f, 0.0f));

        assertEquals(1.0f, dotSameDirection);
        assertEquals(0.0f, dotOrthogonal1);
        assertEquals(0.0f, dotOrthogonal2);
        assertEquals(-1.0f, dotBackDirection);
    }

    @Test
    void testLength () {
        Vector2f v = new Vector2f(2.0f, 4.0f);

        assertEquals(Mathf.sqrt(20.0f), v.length());
    }

    @Test
    void testDotStatic () {
        Vector2f v0 = new Vector2f(2.0f, 4.0f);
        Vector2f v1 = new Vector2f(3.0f, 5.0f);

        // 2*3=6
        // 4*5=20

        assertEquals(20.0f, Vector2f.dot(v0));
        assertEquals(26.0f, Vector2f.dot(v0, v1));
    }

    @Test
    void testLengthStatic () {
        Vector2f v = new Vector2f(2.0f, 4.0f);

        assertEquals((float)Math.sqrt(20.0f), Vector2f.length(v));
    }

    @Test
    void testNormalize () {
        Vector2f x10 = new Vector2f(10.0f, 0.0f);
        Vector2f y10 = new Vector2f(0.0f, 10.0f);

        x10.normalize();
        y10.normalize();

        assertEquals(new Vector2f(1.0f, 0.0f), x10);
        assertEquals(new Vector2f(0.0f, 1.0f), y10);
    }

    @Test
    void testNormalized () {
        Vector2f x10 = new Vector2f(10.0f, 0.0f);
        Vector2f y10 = new Vector2f(0.0f, 10.0f);

        Vector2f x1 = x10.normalized();
        Vector2f y1 = y10.normalized();

        assertEquals(new Vector2f(1.0f, 0.0f), x1);
        assertEquals(new Vector2f(0.0f, 1.0f), y1);
        assertEquals(new Vector2f(10.0f, 0.0f), x10);
        assertEquals(new Vector2f(0.0f, 10.0f), y10);
    }



    @Test
    void testToString () {
        float x = 20.0f;
        float y = 458.023f;
        Vector2f v = new Vector2f(x, y);

        assertEquals(x + ", " + y, v.toString());
    }

}
