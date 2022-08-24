package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("squid:S5863")
class ColorRGBTest {

    @Test
    void testIdentity () {
        ColorRGB v = new ColorRGB();
        assertEquals(v, v);
    }

    @Test
    void testEquality () {
        ColorRGB v0 = new ColorRGB(1.0f, 0.2f, 0.3f);
        ColorRGB v1 = new ColorRGB(1.0f, 0.2f, 0.3f);
        assertEquals(v0, v1);
    }

    @Test
    void testNotEquality () {
        ColorRGB v0 = new ColorRGB(1.0f, 1.0f, 1.0f);
        ColorRGB v1 = new ColorRGB(0.0f, 1.0f, 1.0f);
        ColorRGB v2 = new ColorRGB(1.0f, 0.0f, 1.0f);
        ColorRGB v3 = new ColorRGB(1.0f, 1.0f, 0.0f);
        assertNotEquals(v0, v1);
        assertNotEquals(v0, v2);
        assertNotEquals(v0, v3);
    }

    @Test
    void testIdentityStatic () {
        ColorRGB v = new ColorRGB();
        assertTrue(ColorRGB.equals(v, v));
    }

    @Test
    void testEqualityStatic () {
        ColorRGB v0 = new ColorRGB(1.0f, 2.0f, 3.0f);
        ColorRGB v1 = new ColorRGB(1.0f, 2.0f, 3.0f);
        assertTrue(ColorRGB.equals(v0, v1));
    }

    @Test
    void testNotEqualityStatic () {
        ColorRGB v0 = new ColorRGB(1.0f, 1.0f, 1.0f);
        ColorRGB v1 = new ColorRGB(0.0f, 1.0f, 1.0f);
        ColorRGB v2 = new ColorRGB(1.0f, 0.0f, 1.0f);
        ColorRGB v3 = new ColorRGB(1.0f, 1.0f, 0.0f);
        assertFalse(ColorRGB.equals(v0, v1));
        assertFalse(ColorRGB.equals(v0, v2));
        assertFalse(ColorRGB.equals(v0, v3));
    }


    @Test
    void testHashEqual () {
        ColorRGB v0 = new ColorRGB(1.0f, 2.0f, 3.0f);
        ColorRGB v1 = new ColorRGB(1.0f, 2.0f, 3.0f);
        assertEquals(v0.hashCode(), v1.hashCode());
    }



    @Test
    void testHashNotEqual () {
        ColorRGB v0 = new ColorRGB(1.0f, 1.0f, 1.0f);
        ColorRGB v1 = new ColorRGB(0.0f, 1.0f, 1.0f);
        ColorRGB v2 = new ColorRGB(1.0f, 0.0f, 1.0f);
        ColorRGB v3 = new ColorRGB(1.0f, 1.0f, 0.0f);
        assertNotEquals(v0.hashCode(), v1.hashCode());
        assertNotEquals(v0.hashCode(), v2.hashCode());
        assertNotEquals(v0.hashCode(), v3.hashCode());
    }

    @Test
    void testRGB() {
        ColorRGB r = new ColorRGB(1.0f, 0.0f, 0.0f);
        ColorRGB g = new ColorRGB(0.0f, 1.0f, 0.0f);
        ColorRGB b = new ColorRGB(0.0f, 0.0f, 1.0f);
        ColorRGB p = new ColorRGB(1.0f, 0.0f, 1.0f);
        ColorRGB y = new ColorRGB(1.0f, 1.0f, 0.0f);
        ColorRGB c = new ColorRGB(0.0f, 1.0f, 1.0f);

        assertEquals(0x00ff0000, r.rgb());
        assertEquals(0x0000ff00, g.rgb());
        assertEquals(0x000000ff, b.rgb());
        assertEquals(0x00ffff00, y.rgb());
        assertEquals(0x0000ffff, c.rgb());
        assertEquals(0x00ff00ff, p.rgb());
    }

    @Test
    void testRGBA() {
        ColorRGB r = new ColorRGB(1.0f, 0.0f, 0.0f);
        ColorRGB g = new ColorRGB(0.0f, 1.0f, 0.0f);
        ColorRGB b = new ColorRGB(0.0f, 0.0f, 1.0f);
        ColorRGB p = new ColorRGB(1.0f, 0.0f, 1.0f);
        ColorRGB y = new ColorRGB(1.0f, 1.0f, 0.0f);
        ColorRGB c = new ColorRGB(0.0f, 1.0f, 1.0f);

        assertEquals(0xff0000ff, r.rgba());
        assertEquals(0x00ff00ff, g.rgba());
        assertEquals(0x0000ffff, b.rgba());
        assertEquals(0xffff00ff, y.rgba());
        assertEquals(0x00ffffff, c.rgba());
        assertEquals(0xff00ffff, p.rgba());
    }

    @Test
    void testARGB() {
        ColorRGB r = new ColorRGB(1.0f, 0.0f, 0.0f);
        ColorRGB g = new ColorRGB(0.0f, 1.0f, 0.0f);
        ColorRGB b = new ColorRGB(0.0f, 0.0f, 1.0f);
        ColorRGB p = new ColorRGB(1.0f, 0.0f, 1.0f);
        ColorRGB y = new ColorRGB(1.0f, 1.0f, 0.0f);
        ColorRGB c = new ColorRGB(0.0f, 1.0f, 1.0f);

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
        ColorRGB v = new ColorRGB(r, g, b);

        assertEquals(r + ", " + g + ", " + b, v.toString());
    }

}
