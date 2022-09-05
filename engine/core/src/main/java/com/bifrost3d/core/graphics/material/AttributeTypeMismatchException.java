package com.bifrost3d.core.graphics.material;

import com.bifrost3d.core.graphics.EShaderAttributeType;

@SuppressWarnings("unused")
public class AttributeTypeMismatchException extends RuntimeException {

    private final String attributeName;

    private final EShaderAttributeType currentFormat;

    private final EShaderAttributeType newFormat;

    public AttributeTypeMismatchException(String attributeName, EShaderAttributeType currentFormat, EShaderAttributeType newFormat) {
        super ("Attribute '" + attributeName + "' already registered as '" + currentFormat + "'. Try to register as '" + newFormat + "'");
        this.attributeName = attributeName;
        this.currentFormat = currentFormat;
        this.newFormat = newFormat;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public EShaderAttributeType getCurrentFormat() {
        return currentFormat;
    }

    public EShaderAttributeType getNewFormat() {
        return newFormat;
    }
}
