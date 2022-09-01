package com.bifrost3d.core.graphics.material;

import com.bifrost3d.core.graphics.ITexture;
import com.bifrost3d.math.*;

public interface IMaterial {

    int indexOf(String attributeName);

    void setAttributeFloat(int idx, float value);
    void setAttributeVec2(int idx, Vector2f value);
    void setAttributeVec3(int idx, Vector3f value);
    void setAttributeVec4(int idx, Vector4f value);
    void setAttributeColor(int idx, ColorRGBA value);
    void setAttributeMat3(int idx, Matrix3f value);
    void setAttributeMat4(int idx, Matrix4f value);
    void setAttributeTexture(int idx, ITexture texture);

    float getAttributeFloat(int idx);
    Vector2f getAttributeVec2(int idx);
    Vector3f getAttributeVec3(int idx);
    Vector4f getAttributeVec4(int idx);
    ColorRGBA getAttributeColor(int idx);
    Matrix3f getAttributeMat3(int idx);
    Matrix4f getAttributeMat4(int idx);
    ITexture getAttributeTexture(int idx);
}
