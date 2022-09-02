package com.bifrost3d.core.graphics;

import com.bifrost3d.math.Matrix4f;
import com.bifrost3d.math.Vector3f;

public final class Camera {

    private final Vector3f eye = new Vector3f(0.0f, 0.0f, 0.0f);

    private final Vector3f spot = new Vector3f(0.0f, 0.0f, -1.0f);

    private final Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);

    private final Matrix4f matrix = new Matrix4f();

    private boolean dirty = true;

    public Vector3f getEye() {
        return eye;
    }

    public void setEye (Vector3f eye) {
        this.eye.set(eye);
        this.dirty = true;
    }

    public Vector3f getSpot() {
        return spot;
    }

    public void setSpot(Vector3f spot) {
        this.spot.set(spot);
        this.dirty = true;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up.set(up);
        this.dirty = true;
    }

    public Matrix4f getMatrix() {
        if (this.dirty) {
            Matrix4f.lookAt(this.eye, this.spot, this.up, this.matrix);
            this.dirty = false;
        }
        return matrix;
    }
}
