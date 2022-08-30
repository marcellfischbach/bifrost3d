package com.bifrost3d.core.graphics;

import com.bifrost3d.math.Matrix3f;
import com.bifrost3d.math.Matrix4f;

public interface IShaderAttribute {

    EShaderAttributeFormat getFormat();

    void setArrayIdx (int arrayIdx);

    void bind (float x);
    void bind (float x, float y);
    void bind (float x, float y, float z);
    void bind (float x, float y, float z, float w);

    void bind (int x);
    void bind (int x, int y);
    void bind (int x, int y, int z);
    void bind (int x, int y, int z, int w);

    void bind (Matrix3f m);
    void bind (Matrix4f m);
}
