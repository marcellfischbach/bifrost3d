package com.bifrost3d.core.graphics.material;

import com.bifrost3d.core.graphics.ERenderPass;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.graphics.ITexture;
import com.bifrost3d.math.*;

import java.util.ArrayList;
import java.util.List;

public class MaterialInstance implements IMaterial {

    private final List<Attribute> attributes = new ArrayList<>();

    private final Material material;

    public MaterialInstance(Material material) {
        this.material = material;
        this.material.getAttributes().forEach(attrib -> attributes.add(new Attribute(attrib)));
    }

    @Override
    public int indexOf(String attributeName) {
        return material.indexOf(attributeName);
    }

    public boolean isOverride (int idx) {
        return this.attributes.get(idx).override;
    }

    public void setOverride (int idx, boolean override) {
        this.attributes.get(idx).override = override;
    }

    @Override
    public void setAttributeFloat(int idx, float value) {
        this.attributes.get(idx).valueFloat = value;
    }

    @Override
    public void setAttributeVec2(int idx, Vector2f value) {
        this.attributes.get(idx).valueVec2 = value;
    }

    @Override
    public void setAttributeVec3(int idx, Vector3f value) {
        this.attributes.get(idx).valueVec3 = value;
    }

    @Override
    public void setAttributeVec4(int idx, Vector4f value) {
        this.attributes.get(idx).valueVec4 = value;
    }

    @Override
    public void setAttributeColor(int idx, ColorRGBA value) {
        this.attributes.get(idx).valueColor = value;
    }

    @Override
    public void setAttributeMat3(int idx, Matrix3f value) {
        this.attributes.get(idx).valueMat3 = value;
    }

    @Override
    public void setAttributeMat4(int idx, Matrix4f value) {
        this.attributes.get(idx).valueMat4 = value;
    }

    @Override
    public void setAttributeTexture(int idx, ITexture texture) {
        this.attributes.get(idx).texture = texture;
    }


    @Override
    public float getAttributeFloat(int idx) {
        return this.attributes.get(idx).valueFloat;
    }

    @Override
    public Vector2f getAttributeVec2(int idx) {
        return this.attributes.get(idx).valueVec2;
    }

    @Override
    public Vector3f getAttributeVec3(int idx) {
        return this.attributes.get(idx).valueVec3;
    }

    @Override
    public Vector4f getAttributeVec4(int idx) {
        return this.attributes.get(idx).valueVec4;
    }

    @Override
    public ColorRGBA getAttributeColor(int idx) {
        return this.attributes.get(idx).valueColor;
    }

    @Override
    public Matrix3f getAttributeMat3(int idx) {
        return this.attributes.get(idx).valueMat3;
    }

    @Override
    public Matrix4f getAttributeMat4(int idx) {
        return this.attributes.get(idx).valueMat4;
    }

    @Override
    public ITexture getAttributeTexture(int idx) {
        return this.attributes.get(idx).texture;
    }

    @Override
    public void bind(IGraphics graphics, ERenderPass pass) {
        if (!material.bindProgram(graphics, pass)) {
            return;
        }
        graphics.resetTextureUnits();

        this.attributes.forEach(attrib -> bindAttribute(graphics, pass, attrib));
    }

    public void bindAttribute(IGraphics graphics, ERenderPass pass, Attribute attribute) {
        if (attribute.override) {
            material.bindAttribute(graphics, pass, attribute);
        } else {
            material.bindAttribute(graphics, pass, attribute.ref);
        }
    }

    public static class Attribute extends Material.Attribute {

        final Material.Attribute ref;

        boolean override = false;

        public Attribute(Material.Attribute ref) {
            super(ref.name, ref.format);
            System.arraycopy(ref.attributes, 0, this.attributes, 0, ref.attributes.length);
            this.ref = ref;
        }
    }

}
