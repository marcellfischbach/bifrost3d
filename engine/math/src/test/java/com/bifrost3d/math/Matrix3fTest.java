package com.bifrost3d.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix3fTest {

    @Test
    void testIdentity () {
        Matrix3f m = new Matrix3f();

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());
    }

    @Test
    void testArbitrary () {
        Matrix3f m = new Matrix3f(1, 2, 3, 4, 5, 6, 7, 8, 9);


        assertEquals(new Vector3f(1.0f, 2.0f, 3.0f), m.getXAxis());
        assertEquals(new Vector3f(4.0f, 5.0f, 6.0f), m.getYAxis());
        assertEquals(new Vector3f(7.0f, 8.0f, 9.0f), m.getZAxis());
    }



    @Test
    void testArbitraryWithReset () {
        Matrix3f m = new Matrix3f(1, 2, 3, 4, 5, 6, 7, 8, 9);


        assertEquals(new Vector3f(1.0f, 2.0f, 3.0f), m.getXAxis());
        assertEquals(new Vector3f(4.0f, 5.0f, 6.0f), m.getYAxis());
        assertEquals(new Vector3f(7.0f, 8.0f, 9.0f), m.getZAxis());

        m.setIdentity();

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());

    }


    @Test
    void testRotationX() {
        Matrix3f m = Matrix3f.rotationX(Mathf.deg2Rad(90));


        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, -1.0f, 0.0f), m.getZAxis());
    }

    @Test
    void testRotationNegX() {
        Matrix3f m = Matrix3f.rotationX(Mathf.deg2Rad(-90));


        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getZAxis());
    }


    @Test
    void testRotationY() {
        Matrix3f m = Matrix3f.rotationY(Mathf.deg2Rad(90));


        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getZAxis());
    }

    @Test
    void testRotationNegY() {
        Matrix3f m = Matrix3f.rotationY(Mathf.deg2Rad(-90));


        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), m.getZAxis());
    }


    @Test
    void testRotationZ() {
        Matrix3f m = Matrix3f.rotationZ(Mathf.deg2Rad(90));


        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());
    }


    @Test
    void testRotationNegZ() {
        Matrix3f m = Matrix3f.rotationZ(Mathf.deg2Rad(-90));


        assertEquals(new Vector3f(0.0f, -1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());
    }














    @Test
    void testRotationXAxis() {
        Matrix3f m = Matrix3f.rotation(new Vector3f(1.0f, 0.0f, 0.0f), Mathf.deg2Rad(90), new Matrix3f());


        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, -1.0f, 0.0f), m.getZAxis());
    }

    @Test
    void testRotationNegXAxis() {
        Matrix3f m = Matrix3f.rotation(1.0f, 0.0f, 0.0f, Mathf.deg2Rad(-90));


        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getZAxis());
    }


    @Test
    void testRotationYAxis() {
        Matrix3f m = Matrix3f.rotation(new Vector3f(0.0f, 1.0f, 0.0f), Mathf.deg2Rad(90));


        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getZAxis());
    }

    @Test
    void testRotationNegYAxis() {
        Matrix3f m = Matrix3f.rotation(0.0f, 1.0f, 0.0f, Mathf.deg2Rad(-90));


        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), m.getZAxis());
    }


    @Test
    void testRotationZAxis() {
        Matrix3f m = Matrix3f.rotation(new Vector3f (0.0f, 0.0f, 1.0f), Mathf.deg2Rad(90));


        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());
    }


    @Test
    void testRotationNegZAxis() {
        Matrix3f m = Matrix3f.rotation(0.0f, 0.0f, 1.0f, Mathf.deg2Rad(-90));


        assertEquals(new Vector3f(0.0f, -1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());
    }



    @Test
    void testRotationXNegAxis() {
        Matrix3f m = Matrix3f.rotation(-1.0f, 0.0f, 0.0f, Mathf.deg2Rad(90));


        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getZAxis());
    }
    @Test
    void testRotationYNegAxis() {
        Matrix3f m = Matrix3f.rotation(0.0f, -1.0f, 0.0f, Mathf.deg2Rad(90));


        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), m.getZAxis());
    }

    @Test
    void testRotationZNegAxis() {
        Matrix3f m = Matrix3f.rotation(0.0f, 0.0f, -1.0f, Mathf.deg2Rad(90));


        assertEquals(new Vector3f(0.0f, -1.0f, 0.0f), m.getXAxis());
        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), m.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), m.getZAxis());
    }
    @Test
    void testMultZ90Y90() {
        Matrix3f z90 = Matrix3f.rotationZ(Mathf.deg2Rad(90));
        Matrix3f y90 = Matrix3f.rotationY(Mathf.deg2Rad(90));
        Matrix3f res = Matrix3f.mul(z90, y90);

        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), res.getXAxis());
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), res.getYAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f),res.getZAxis());

    }

    @Test
    void testMultZN90X90YN90() {
        Matrix3f zn90 = Matrix3f.rotationZ(Mathf.deg2Rad(-90));
        Matrix3f yn90 = Matrix3f.rotationY(Mathf.deg2Rad(-90));
        Matrix3f x90 = Matrix3f.rotationX(Mathf.deg2Rad(90));
        Matrix3f res = Matrix3f.mul(zn90, Matrix3f.mul(x90, yn90));

        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), res.getXAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), res.getYAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f),res.getZAxis());

    }


    @Test
    void testMultXN90Y90Z90YN90() {
        Matrix3f xn90 = Matrix3f.rotationX(Mathf.deg2Rad(-90));
        Matrix3f y90 = Matrix3f.rotationY(Mathf.deg2Rad(90));
        Matrix3f z90 = Matrix3f.rotationZ(Mathf.deg2Rad(90));
        Matrix3f yn90 = Matrix3f.rotationY(Mathf.deg2Rad(-90));

        Matrix3f res = Matrix3f.mul(xn90, Matrix3f.mul(y90, Matrix3f.mul(z90, yn90)));

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), res.getXAxis());
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), res.getYAxis());
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), res.getZAxis());

    }


    @Test
    void testMulVectorMatrixIdentity () {
        Vector3f vx = new Vector3f(1.0f, 0.0f, 0.0f);
        Vector3f vy = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f vz = new Vector3f(0.0f, 0.0f, 1.0f);

        Matrix3f m = new Matrix3f();

        Vector3f rx = Matrix3f.mul(m, vx);
        Vector3f ry = Matrix3f.mul(m, vy);
        Vector3f rz = Matrix3f.mul(m, vz);

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), rx);
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), ry);
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), rz);
    }

    @Test
    void testMulVectorMatrixX90 () {
        Vector3f vx = new Vector3f(1.0f, 0.0f, 0.0f);
        Vector3f vy = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f vz = new Vector3f(0.0f, 0.0f, 1.0f);

        Matrix3f m = Matrix3f.rotationX(Mathf.deg2Rad(90));

        Vector3f rx = Matrix3f.mul(m, vx);
        Vector3f ry = Matrix3f.mul(m, vy);
        Vector3f rz = Matrix3f.mul(m, vz);

        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), rx);
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), ry);
        assertEquals(new Vector3f(0.0f, -1.0f, 0.0f), rz);
    }


    @Test
    void testMulVectorMatrixY90 () {
        Vector3f vx = new Vector3f(1.0f, 0.0f, 0.0f);
        Vector3f vy = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f vz = new Vector3f(0.0f, 0.0f, 1.0f);

        Matrix3f m = Matrix3f.rotationY(Mathf.deg2Rad(90));

        Vector3f rx = Matrix3f.mul(m, vx);
        Vector3f ry = Matrix3f.mul(m, vy);
        Vector3f rz = Matrix3f.mul(m, vz);

        assertEquals(new Vector3f(0.0f, 0.0f, -1.0f), rx);
        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), ry);
        assertEquals(new Vector3f(1.0f, 0.0f, 0.0f), rz);
    }


    @Test
    void testMulVectorMatrixZ90 () {
        Vector3f vx = new Vector3f(1.0f, 0.0f, 0.0f);
        Vector3f vy = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f vz = new Vector3f(0.0f, 0.0f, 1.0f);

        Matrix3f m = Matrix3f.rotationZ(Mathf.deg2Rad(90));

        Vector3f rx = Matrix3f.mul(m, vx);
        Vector3f ry = Matrix3f.mul(m, vy);
        Vector3f rz = Matrix3f.mul(m, vz);

        assertEquals(new Vector3f(0.0f, 1.0f, 0.0f), rx);
        assertEquals(new Vector3f(-1.0f, 0.0f, 0.0f), ry);
        assertEquals(new Vector3f(0.0f, 0.0f, 1.0f), rz);
    }
}


