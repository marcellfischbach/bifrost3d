package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("squid:S5863")
class ColorRGBATest {

    @Test
    void testIdentity () {
        ColorRGBA v = new ColorRGBA();
        assertEquals(v, v);
    }

    @Test
    void testEquality () {
        ColorRGBA v0 = new ColorRGBA(1.0f, 0.2f, 0.3f, 0.5f);
        ColorRGBA v1 = new ColorRGBA(1.0f, 0.2f, 0.3f, 0.5f);
        assertEquals(v0, v1);
    }

    @Test
    void testNotEquality () {
        ColorRGBA v0 = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);
        ColorRGBA v1 = new ColorRGBA(0.0f, 1.0f, 1.0f, 1.0f);
        ColorRGBA v2 = new ColorRGBA(1.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA v3 = new ColorRGBA(1.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA v4 = new ColorRGBA(1.0f, 1.0f, 1.0f, 0.0f);
        assertNotEquals(v0, v1);
        assertNotEquals(v0, v2);
        assertNotEquals(v0, v3);
        assertNotEquals(v0, v4);
    }

    @Test
    void testIdentityStatic () {
        ColorRGBA v = new ColorRGBA();
        assertTrue(ColorRGBA.equals(v, v));
    }

    @Test
    void testEqualityStatic () {
        ColorRGBA v0 = new ColorRGBA(1.0f, 2.0f, 3.0f, 4.0f);
        ColorRGBA v1 = new ColorRGBA(1.0f, 2.0f, 3.0f, 4.0f);
        assertTrue(ColorRGBA.equals(v0, v1));
    }

    @Test
    void testNotEqualityStatic () {
        ColorRGBA v0 = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);
        ColorRGBA v1 = new ColorRGBA(0.0f, 1.0f, 1.0f, 1.0f);
        ColorRGBA v2 = new ColorRGBA(1.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA v3 = new ColorRGBA(1.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA v4 = new ColorRGBA(1.0f, 1.0f, 1.0f, 0.0f);
        assertFalse(ColorRGBA.equals(v0, v1));
        assertFalse(ColorRGBA.equals(v0, v2));
        assertFalse(ColorRGBA.equals(v0, v3));
        assertFalse(ColorRGBA.equals(v0, v4));
    }


    @Test
    void testHashEqual () {
        ColorRGBA v0 = new ColorRGBA(1.0f, 2.0f, 3.0f, 4.0f);
        ColorRGBA v1 = new ColorRGBA(1.0f, 2.0f, 3.0f, 4.0f);
        assertEquals(v0.hashCode(), v1.hashCode());
    }



    @Test
    void testHashNotEqual () {
        ColorRGBA v0 = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);
        ColorRGBA v1 = new ColorRGBA(0.0f, 1.0f, 1.0f, 1.0f);
        ColorRGBA v2 = new ColorRGBA(1.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA v3 = new ColorRGBA(1.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA v4 = new ColorRGBA(1.0f, 1.0f, 1.0f, 0.0f);
        assertNotEquals(v0.hashCode(), v1.hashCode());
        assertNotEquals(v0.hashCode(), v2.hashCode());
        assertNotEquals(v0.hashCode(), v3.hashCode());
        assertNotEquals(v0.hashCode(), v4.hashCode());
    }



    @Test
    void testRGBA() {
        ColorRGBA r = new ColorRGBA(1.0f, 0.0f, 0.0f, 1.0f);
        ColorRGBA g = new ColorRGBA(0.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA b = new ColorRGBA(0.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA p = new ColorRGBA(1.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA y = new ColorRGBA(1.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA c = new ColorRGBA(0.0f, 1.0f, 1.0f, 1.0f);

        assertEquals(0xff0000ff, r.rgba());
        assertEquals(0x00ff00ff, g.rgba());
        assertEquals(0x0000ffff, b.rgba());
        assertEquals(0xffff00ff, y.rgba());
        assertEquals(0x00ffffff, c.rgba());
        assertEquals(0xff00ffff, p.rgba());
    }

    @Test
    void testARGB() {
        ColorRGBA r = new ColorRGBA(1.0f, 0.0f, 0.0f, 1.0f);
        ColorRGBA g = new ColorRGBA(0.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA b = new ColorRGBA(0.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA p = new ColorRGBA(1.0f, 0.0f, 1.0f, 1.0f);
        ColorRGBA y = new ColorRGBA(1.0f, 1.0f, 0.0f, 1.0f);
        ColorRGBA c = new ColorRGBA(0.0f, 1.0f, 1.0f, 1.0f);

        assertEquals(0xffff0000, r.argb());
        assertEquals(0xff00ff00, g.argb());
        assertEquals(0xff0000ff, b.argb());
        assertEquals(0xffffff00, y.argb());
        assertEquals(0xff00ffff, c.argb());
        assertEquals(0xffff00ff, p.argb());
    }
    @Test
    void testToString () {
        float r = 1.0f;
        float g = 0.25f;
        float b = 0.50f;
        float a = 0.75f;
        ColorRGBA v = new ColorRGBA(r, g, b, a);

        assertEquals(r + ", " + g + ", " + b + ", " + a, v.toString());
    }
}
