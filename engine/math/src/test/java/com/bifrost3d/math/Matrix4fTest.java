package com.bifrost3d.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Matrix4fTest {

    @Test
    void testIdentity() {
        Matrix4f m = new Matrix4f();

        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testArbitrary() {
        Matrix4f m = new Matrix4f(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);


        assertEquals(new Vector4f(1.0f, 2.0f, 3.0f, 4.0f), m.getXAxis());
        assertEquals(new Vector4f(5.0f, 6.0f, 7.0f, 8.0f), m.getYAxis());
        assertEquals(new Vector4f(9.0f, 10.0f, 11.0f, 12.0f), m.getZAxis());
        assertEquals(new Vector4f(13.0f, 14.0f, 15.0f, 16.0f), m.getTranslation());
    }


    @Test
    void testArbitraryWithReset() {
        Matrix4f m = new Matrix4f(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);


        assertEquals(new Vector4f(1.0f, 2.0f, 3.0f, 4.0f), m.getXAxis());
        assertEquals(new Vector4f(5.0f, 6.0f, 7.0f, 8.0f), m.getYAxis());
        assertEquals(new Vector4f(9.0f, 10.0f, 11.0f, 12.0f), m.getZAxis());
        assertEquals(new Vector4f(13.0f, 14.0f, 15.0f, 16.0f), m.getTranslation());

        m.setIdentity();

        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationX() {
        Matrix4f m = Matrix4f.rotationX(Mathf.deg2Rad(90));


        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, -1.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testRotationNegX() {
        Matrix4f m = Matrix4f.rotationX(Mathf.deg2Rad(-90));


        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationY() {
        Matrix4f m = Matrix4f.rotationY(Mathf.deg2Rad(90));


        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testRotationNegY() {
        Matrix4f m = Matrix4f.rotationY(Mathf.deg2Rad(-90));


        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationZ() {
        Matrix4f m = Matrix4f.rotationZ(Mathf.deg2Rad(90));


        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationNegZ() {
        Matrix4f m = Matrix4f.rotationZ(Mathf.deg2Rad(-90));


        assertEquals(new Vector4f(0.0f, -1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationXAxis() {
        Matrix4f m = Matrix4f.rotation(new Vector3f(1.0f, 0.0f, 0.0f), Mathf.deg2Rad(90), new Matrix4f());


        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, -1.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testRotationNegXAxis() {
        Matrix4f m = Matrix4f.rotation(1.0f, 0.0f, 0.0f, Mathf.deg2Rad(-90));


        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationYAxis() {
        Matrix4f m = Matrix4f.rotation(new Vector3f(0.0f, 1.0f, 0.0f), Mathf.deg2Rad(90));


        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testRotationNegYAxis() {
        Matrix4f m = Matrix4f.rotation(0.0f, 1.0f, 0.0f, Mathf.deg2Rad(-90));


        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationZAxis() {
        Matrix4f m = Matrix4f.rotation(new Vector3f(0.0f, 0.0f, 1.0f), Mathf.deg2Rad(90));


        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationNegZAxis() {
        Matrix4f m = Matrix4f.rotation(0.0f, 0.0f, 1.0f, Mathf.deg2Rad(-90));


        assertEquals(new Vector4f(0.0f, -1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testRotationXNegAxis() {
        Matrix4f m = Matrix4f.rotation(-1.0f, 0.0f, 0.0f, Mathf.deg2Rad(90));


        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testRotationYNegAxis() {
        Matrix4f m = Matrix4f.rotation(0.0f, -1.0f, 0.0f, Mathf.deg2Rad(90));


        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testRotationZNegAxis() {
        Matrix4f m = Matrix4f.rotation(0.0f, 0.0f, -1.0f, Mathf.deg2Rad(90));


        assertEquals(new Vector4f(0.0f, -1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), m.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testMultZ90Y90() {
        Matrix4f z90 = Matrix4f.rotationZ(Mathf.deg2Rad(90));
        Matrix4f y90 = Matrix4f.rotationY(Mathf.deg2Rad(90));
        Matrix4f res = Matrix4f.mul(z90, y90);

        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), res.getXAxis());
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), res.getYAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), res.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), res.getTranslation());

    }

    @Test
    void testMultZN90X90YN90() {
        Matrix4f zn90 = Matrix4f.rotationZ(Mathf.deg2Rad(-90));
        Matrix4f yn90 = Matrix4f.rotationY(Mathf.deg2Rad(-90));
        Matrix4f x90 = Matrix4f.rotationX(Mathf.deg2Rad(90));
        Matrix4f res = Matrix4f.mul(zn90, Matrix4f.mul(x90, yn90));

        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), res.getXAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), res.getYAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), res.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), res.getTranslation());

    }


    @Test
    void testMultXN90Y90Z90YN90() {
        Matrix4f xn90 = Matrix4f.rotationX(Mathf.deg2Rad(-90));
        Matrix4f y90 = Matrix4f.rotationY(Mathf.deg2Rad(90));
        Matrix4f z90 = Matrix4f.rotationZ(Mathf.deg2Rad(90));
        Matrix4f yn90 = Matrix4f.rotationY(Mathf.deg2Rad(-90));

        Matrix4f res = Matrix4f.mul(xn90, Matrix4f.mul(y90, Matrix4f.mul(z90, yn90)));

        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), res.getXAxis());
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), res.getYAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), res.getZAxis());
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), res.getTranslation());

    }


    @Test
    void testMulVectorMatrixIdentity() {
        Vector4f vx = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        Vector4f vy = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f);
        Vector4f vz = new Vector4f(0.0f, 0.0f, 1.0f, 0.0f);

        Matrix4f m = new Matrix4f();

        Vector4f rx = Matrix4f.mul(m, vx);
        Vector4f ry = Matrix4f.mul(m, vy);
        Vector4f rz = Matrix4f.mul(m, vz);

        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), rx);
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), ry);
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), rz);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }

    @Test
    void testMulVectorMatrixX90() {
        Vector4f vx = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        Vector4f vy = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f);
        Vector4f vz = new Vector4f(0.0f, 0.0f, 1.0f, 0.0f);

        Matrix4f m = Matrix4f.rotationX(Mathf.deg2Rad(90));

        Vector4f rx = Matrix4f.mul(m, vx);
        Vector4f ry = Matrix4f.mul(m, vy);
        Vector4f rz = Matrix4f.mul(m, vz);

        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), rx);
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), ry);
        assertEquals(new Vector4f(0.0f, -1.0f, 0.0f, 0.0f), rz);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testMulVectorMatrixY90() {
        Vector4f vx = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        Vector4f vy = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f);
        Vector4f vz = new Vector4f(0.0f, 0.0f, 1.0f, 0.0f);

        Matrix4f m = Matrix4f.rotationY(Mathf.deg2Rad(90));

        Vector4f rx = Matrix4f.mul(m, vx);
        Vector4f ry = Matrix4f.mul(m, vy);
        Vector4f rz = Matrix4f.mul(m, vz);

        assertEquals(new Vector4f(0.0f, 0.0f, -1.0f, 0.0f), rx);
        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), ry);
        assertEquals(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), rz);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }


    @Test
    void testMulVectorMatrixZ90() {
        Vector4f vx = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        Vector4f vy = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f);
        Vector4f vz = new Vector4f(0.0f, 0.0f, 1.0f, 0.0f);

        Matrix4f m = Matrix4f.rotationZ(Mathf.deg2Rad(90));

        Vector4f rx = Matrix4f.mul(m, vx);
        Vector4f ry = Matrix4f.mul(m, vy);
        Vector4f rz = Matrix4f.mul(m, vz);

        assertEquals(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f), rx);
        assertEquals(new Vector4f(-1.0f, 0.0f, 0.0f, 0.0f), ry);
        assertEquals(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f), rz);
        assertEquals(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), m.getTranslation());
    }
}


