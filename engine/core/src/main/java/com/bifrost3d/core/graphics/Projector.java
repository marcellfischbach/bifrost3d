package com.bifrost3d.core.graphics;

import com.bifrost3d.math.Mathf;
import com.bifrost3d.math.Matrix4f;

public final class Projector {

    private int width;
    private int height;


    private float angle = Mathf.deg2Rad(45.0f);
    private float near = 1.0f;
    private float far = 1024.0f;

    private boolean dirty = true;

    private final Matrix4f matrix = new Matrix4f();

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        if (this.angle != angle) {
            this.angle = angle;
            this.dirty = true;
        }
    }

    public float getNear() {
        return near;
    }

    public void setNear(float near) {
        if (this.near != near) {
            this.near = near;
            this.dirty = true;
        }
    }


    public float getFar() {
        return far;
    }

    public void setFar(float far) {
        if (this.far != far) {
            this.far = far;
            this.dirty = true;
        }
    }

    public void setDimension (int width, int height) {
        if (this.width != width || this.height != height) {
            this.width = width;
            this.height = height;
            this.dirty = true;
        }
    }


    public Matrix4f getMatrix() {
        if (this.dirty) {

            float aspect = (float)height / (float) width;

            float h = Mathf.tan(this.angle) * this.near;
            float v = aspect * h;

            Matrix4f.projection(-h, h, -v, v, this.near, this.far, this.matrix);
        }
        return matrix;
    }
}
