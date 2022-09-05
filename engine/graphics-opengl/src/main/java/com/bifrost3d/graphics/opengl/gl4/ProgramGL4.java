package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.*;
import lombok.AllArgsConstructor;

import java.util.*;

import static org.lwjgl.opengl.GL44.*;

public class ProgramGL4 implements IProgram {

    private int name;

    private final ShaderAttributeGL4[] defaultAttributes = new ShaderAttributeGL4[EShaderAttribute.values().length];

    private final Map<String, ShaderAttributeGL4> namedAttributesMap = new HashMap<>();
    private final List<NamedAttribute> namedAttributes = new ArrayList<>();

    public ProgramGL4() {
        this.name = glCreateProgram();
    }

    public int glName() {
        return this.name;
    }


    public void delete() {
        if (this.name != 0) {
            glDeleteProgram(this.name);
            this.name = 0;
        }
    }

    @Override
    public Set<String> namedAttributes() {
        return new HashSet<>(this.namedAttributesMap.keySet());
    }

    @Override
    public int indexOf(String attributeName) {
        for (int i = 0; i < this.namedAttributes.size(); i++) {
            NamedAttribute namedAttribute = this.namedAttributes.get(i);
            if (attributeName.equals(namedAttribute.name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public IShaderAttribute getAttribute(int idx) {
        if (idx < 0 || idx >= this.namedAttributes.size()) {
            return null;
        }
        return this.namedAttributes.get(idx).attribute;
    }

    @Override
    public void registerAttribute(String attributeName, EShaderAttributeType format) {
        int idx = indexOf(attributeName);
        if (idx != -1) {
            return;
        }

        String attribteNameInSource = ConstGL4.SHADER_PREFIX + attributeName;
        int loc = glGetUniformLocation(this.name, attribteNameInSource);
        if (loc == -1) {
            return;
        }

        ShaderAttributeGL4 attribute = new ShaderAttributeGL4(format, loc);

        namedAttributes.add(new NamedAttribute(attributeName, attribute));
        namedAttributesMap.put(attributeName, attribute);

    }

    @Override
    public ShaderAttributeGL4 getAttribute(String attributeName) {
        return namedAttributesMap.get(attributeName);
    }

    @Override
    public IShaderAttribute getAttribute(EShaderAttribute type) {
        return this.defaultAttributes[type.ordinal()];
    }

    @Override
    public void attach(IShader shader) {
        glAttachShader(this.name, ((ShaderGL4) shader).glName());
    }


    @Override
    public void link() {
        glLinkProgram(this.name);
        int status = glGetProgrami(this.name, GL_LINK_STATUS);
        if (status == GL_FALSE) {
            String log = glGetProgramInfoLog(this.name);
            throw new ProgramLinkException(log);
        }

        registerDefaultAttributes();
    }

    private void registerDefaultAttributes() {

        for (EShaderAttribute value : EShaderAttribute.values()) {
            String attribName = value.attributeName;

            int loc = glGetUniformLocation(this.name, ConstGL4.SHADER_PREFIX + attribName);
            if (loc != -1) {
                this.defaultAttributes[value.ordinal()] = new ShaderAttributeGL4(value.format, loc);
            } else {
                this.defaultAttributes[value.ordinal()] = null;
            }
        }
    }


    @AllArgsConstructor
    private static class NamedAttribute {
        public final String name;
        public final ShaderAttributeGL4 attribute;


    }
}
