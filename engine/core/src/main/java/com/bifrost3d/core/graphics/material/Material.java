package com.bifrost3d.core.graphics.material;

import com.bifrost3d.core.graphics.*;
import com.bifrost3d.math.*;

import java.util.ArrayList;
import java.util.List;

public class Material implements IMaterial {

    private final IProgram[] programs = new IProgram[ERenderPass.values().length];

    private final List<Attribute> attributes = new ArrayList<>();

    public List<Attribute> getAttributes() {
        return new ArrayList<>(attributes);
    }

    @Override
    public int indexOf(String attributeName) {
        for (int i = 0; i < this.attributes.size(); i++) {
            if (attributeName.equals(this.attributes.get(i).name)) {
                return i;
            }
        }
        return -1;
    }

    public void bind(IGraphics graphics, ERenderPass pass) {
        if (!bindProgram(graphics, pass)) {
            return;
        }
        graphics.resetTextureUnits();

        this.attributes.forEach(attrib -> bindAttribute(graphics, pass, attrib));
    }

    public boolean bindProgram(IGraphics graphics, ERenderPass pass) {

        IProgram program = this.programs[pass.ordinal()];
        if (program != null) {
            graphics.setProgram(program);
            return true;
        }
        return false;
    }

    public void bindAttribute(IGraphics graphics, ERenderPass pass, Attribute attribute) {
        IShaderAttribute shaderAttribute = attribute.attributes[pass.ordinal()];
        if (shaderAttribute == null) {
            return;
        }

        switch (shaderAttribute.getFormat()) {
            case FLOAT:
                shaderAttribute.bind(attribute.valueFloat);
                break;
            case VEC2:
                Vector2f v2 = attribute.valueVec2;
                shaderAttribute.bind(v2.x, v2.y);
                break;
            case VEC3:
                Vector3f v3 = attribute.valueVec3;
                shaderAttribute.bind(v3.x, v3.y, v3.z);
                break;
            case VEC4:
                Vector4f v4 = attribute.valueVec4;
                shaderAttribute.bind(v4.x, v4.y, v4.z, v4.w);
                break;
            case COL4:
                ColorRGBA c4 = attribute.valueColor;
                shaderAttribute.bind(c4.r, c4.g, c4.b, c4.a);
                break;
            case MAT3:
                shaderAttribute.bind(attribute.valueMat3);
                break;
            case MAT4:
                shaderAttribute.bind(attribute.valueMat4);
                break;
            case TEXTURE:
                if (attribute.texture != null) {
                    int unit = graphics.bind(attribute.texture);
                    if (unit != -1) {
                        shaderAttribute.bind(unit);
                    }
                }
                break;
        }
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

    public void setProgram(ERenderPass pass, IProgram program) {
        programs[pass.ordinal()] = program;

        updateAttributes(pass);
    }


    private void updateAttributes(ERenderPass pass) {
        IProgram program = programs[pass.ordinal()];
        program.namedAttributes().forEach(attribName -> updateAttribute(pass, program, attribName));
    }

    private void updateAttribute(ERenderPass pass, IProgram program, String attributeName) {
        IShaderAttribute shaderAttribute = program.getAttribute(attributeName);
        if (shaderAttribute == null) {
            // TODO: Throw exception here
            return;
        }

        Attribute attribute = getAttributeByName(attributeName);
        if (attribute != null && attribute.format != shaderAttribute.getFormat()) {
            // TODO: Throw exception here
            return;
        } else if (attribute == null) {
            attribute = new Attribute(attributeName, shaderAttribute.getFormat());
            this.attributes.add(attribute);
        }

        attribute.attributes[pass.ordinal()] = shaderAttribute;
    }


    private Attribute getAttributeByName(String name) {
        return this.attributes.stream()
                .filter(a -> name.equals(a.name))
                .findFirst()
                .orElse(null);
    }


    public static class Attribute {
        final String name;
        final IShaderAttribute[] attributes = new IShaderAttribute[ERenderPass.values().length];
        final EShaderAttributeFormat format;

        float valueFloat;
        Vector2f valueVec2;
        Vector3f valueVec3;
        Vector4f valueVec4;
        ColorRGBA valueColor;
        Matrix3f valueMat3;
        Matrix4f valueMat4;
        ITexture texture;

        public Attribute(String name, EShaderAttributeFormat format) {
            this.name = name;
            this.format = format;
        }
    }

}
