package com.bifrost3d.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("squid:S5863")
class Vector3fTest {


    @Test
    void testIdentity () {
        Vector3f v = new Vector3f();
        assertEquals(v, v);
    }

    @Test
    void testEquality () {
        Vector3f v0 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f v1 = new Vector3f(1.0f, 2.0f, 3.0f);
        assertEquals(v0, v1);
    }

    @Test
    void testNotEquality () {
        Vector3f v0 = new Vector3f(1.0f, 1.0f, 1.0f);
        Vector3f v1 = new Vector3f(0.0f, 1.0f, 1.0f);
        Vector3f v2 = new Vector3f(1.0f, 0.0f, 1.0f);
        Vector3f v3 = new Vector3f(1.0f, 1.0f, 0.0f);
        assertNotEquals(v0, v1);
        assertNotEquals(v0, v2);
        assertNotEquals(v0, v3);
    }

    @Test
    void testIdentityStatic () {
        Vector3f v = new Vector3f();
        assertTrue(Vector3f.equals(v, v));
    }

    @Test
    void testEqualityStatic () {
        Vector3f v0 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f v1 = new Vector3f(1.0f, 2.0f, 3.0f);
        assertTrue(Vector3f.equals(v0, v1));
    }

    @Test
    void testNotEqualityStatic () {
        Vector3f v0 = new Vector3f(1.0f, 1.0f, 1.0f);
        Vector3f v1 = new Vector3f(0.0f, 1.0f, 1.0f);
        Vector3f v2 = new Vector3f(1.0f, 0.0f, 1.0f);
        Vector3f v3 = new Vector3f(1.0f, 1.0f, 0.0f);
        assertFalse(Vector3f.equals(v0, v1));
        assertFalse(Vector3f.equals(v0, v2));
        assertFalse(Vector3f.equals(v0, v3));
    }


    @Test
    void testHashEqual () {
        Vector3f v0 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f v1 = new Vector3f(1.0f, 2.0f, 3.0f);
        assertEquals(v0.hashCode(), v1.hashCode());
    }



    @Test
    void testHashNotEqual () {
        Vector3f v0 = new Vector3f(1.0f, 1.0f, 1.0f);
        Vector3f v1 = new Vector3f(0.0f, 1.0f, 1.0f);
        Vector3f v2 = new Vector3f(1.0f, 0.0f, 1.0f);
        Vector3f v3 = new Vector3f(1.0f, 1.0f, 0.0f);
        assertNotEquals(v0.hashCode(), v1.hashCode());
        assertNotEquals(v0.hashCode(), v2.hashCode());
        assertNotEquals(v0.hashCode(), v3.hashCode());
    }


    @Test
    void testAdd() {
        Vector3f v0 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f v1 = new Vector3f(2.0f, 3.0f, 4.0f);

        Vector3f r = Vector3f.add(v0, v1);
        assertEquals(new Vector3f(3.0f, 5.0f, 7.0f), r);
    }

    @Test
    void testSub() {
        Vector3f v0 = new Vector3f(4.0f, 8.0f, 16.0f);
        Vector3f v1 = new Vector3f(1.0f, 2.0f, 3.0f);

        Vector3f r = Vector3f.sub(v0, v1);
        assertEquals(new Vector3f(3.0f, 6.0f, 13.0f), r);
    }

    @Test
    void testMul() {
        Vector3f v0 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f v1 = new Vector3f(2.0f, 3.0f, 4.0f);

        Vector3f r0 = Vector3f.mul(v0, v1);
        Vector3f r1 = Vector3f.mul(4.0f, v1);
        Vector3f r2 = Vector3f.mul(v0, 6.0f);
        assertEquals(new Vector3f(2.0f, 6.0f, 12.0f), r0);
        assertEquals(new Vector3f(8.0f, 12.0f, 16.0f), r1);
        assertEquals(new Vector3f(6.0f, 12.0f, 18.0f), r2);
    }

    @Test
    void testDiv() {
        Vector3f v0 = new Vector3f(2.0f, 6.0f, 12.0f);
        Vector3f v1 = new Vector3f(2.0f, 3.0f, 4.0f);

        Vector3f r0 = Vector3f.div(v0, v1);
        Vector3f r1 = Vector3f.div(v0, 2.0f);
        Vector3f r2 = Vector3f.div(30.0f, v1);
        assertEquals(new Vector3f(1.0f, 2.0f, 3.0f), r0);
        assertEquals(new Vector3f(1.0f, 3.0f, 6.0f), r1);
        assertEquals(new Vector3f(15.0f, 10.0f, 7.5f), r2);
    }

    @Test
    void testDot () {
        Vector3f v = new Vector3f(2.0f, 4.0f, 6.0f);

        assertEquals(56.0f, v.dot());


        float dotSameDirection = Vector3f.dot(new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f));
        float dotOrthogonal1 = Vector3f.dot(new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f));
        float dotOrthogonal2 = Vector3f.dot(new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(0.0f, -1.0f, 0.0f));
        float dotBackDirection = Vector3f.dot(new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(-1.0f, 0.0f, 0.0f));

        assertEquals(1.0f, dotSameDirection);
        assertEquals(0.0f, dotOrthogonal1);
        assertEquals(0.0f, dotOrthogonal2);
        assertEquals(-1.0f, dotBackDirection);
    }

    @Test
    void testLength () {
        Vector3f v = new Vector3f(2.0f, 4.0f, 6.0f);

        assertEquals((float)Math.sqrt(56.0f), v.length());
    }

    @Test
    void testDotStatic () {
        Vector3f v0 = new Vector3f(2.0f, 4.0f, 6.0f);
        Vector3f v1 = new Vector3f(3.0f, 5.0f, 7.0f);

        // 2*3=6
        // 4*5=20
        // 6*7=42

        assertEquals(56.0f, Vector3f.dot(v0));
        assertEquals(68.0f, Vector3f.dot(v0, v1));
    }

    @Test
    void testLengthStatic () {
        Vector3f v = new Vector3f(2.0f, 4.0f, 6.0f);

        assertEquals((float)Math.sqrt(56.0f), Vector3f.length(v));
    }

    @Test
    void testNormalize () {
        Vector3f x10 = new Vector3f(10.0f, 0.0f, 0.0f);
        Vector3f y10 = new Vector3f(0.0f, 10.0f, 0.0f);
        Vector3f z10 = new Vector3f(0.0f, 0.0f, 10.0f);

        x10.normalize();
        y10.normalize();
        z10.normalize();

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), x10);
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), y10);
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), z10);
    }

    @Test
    void testNormalized () {
        Vector3f x10 = new Vector3f(10.0f, 0.0f, 0.0f);
        Vector3f y10 = new Vector3f(0.0f, 10.0f, 0.0f);
        Vector3f z10 = new Vector3f(0.0f, 0.0f, 10.0f);

        Vector3f x1 = x10.normalized();
        Vector3f y1 = y10.normalized();
        Vector3f z1 = z10.normalized();

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), x1);
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), y1);
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), z1);
        assertEquals(new Vector3f(10.0f, 0.0f, 0.0f), x10);
        assertEquals(new Vector3f(0.0f, 10.0f, 0.0f), y10);
        assertEquals(new Vector3f(0.0f, 0.0f, 10.0f), z10);
    }


    @Test
    void testCross () {
        Vector3f x = new Vector3f(1.0f, 0.0f, 0.0f);
        Vector3f nx = new Vector3f(-1.0f, 0.0f, 0.0f);
        Vector3f y = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f ny = new Vector3f(0.0f, -1.0f, 0.0f);
        Vector3f z = new Vector3f(0.0f, 0.0f, 1.0f);
        Vector3f nz = new Vector3f(0.0f, 0.0f, -1.0f);

        assertEquals(x, Vector3f.cross(y, z));
        assertEquals(nx, Vector3f.cross(z, y));
        assertEquals(y, Vector3f.cross(z, x));
        assertEquals(ny, Vector3f.cross(x, z));
        assertEquals(z, Vector3f.cross(x, y));
        assertEquals(nz, Vector3f.cross(y, x));
    }

    @Test
    void testToString () {
        float x = 20.0f;
        float y = 458.023f;
        float z = 232900.23f;
        Vector3f v = new Vector3f(x, y, z);

        assertEquals(x + ", " + y + ", " + z, v.toString());
    }

}
