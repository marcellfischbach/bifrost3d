package com.bifrost3d.core.graphics.material;

import com.bifrost3d.core.graphics.EShaderAttributeFormat;

public class AttributeTypeMismatchException extends RuntimeException {

    private final String attributeName;

    private final EShaderAttributeFormat currentFormat;

    private final EShaderAttributeFormat newFormat;

    public AttributeTypeMismatchException(String attributeName, EShaderAttributeFormat currentFormat, EShaderAttributeFormat newFormat) {
        super ("Attribute '" + attributeName + "' already registered as '" + currentFormat + "'. Try to register as '" + newFormat + "'");
        this.attributeName = attributeName;
        this.currentFormat = currentFormat;
        this.newFormat = newFormat;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public EShaderAttributeFormat getCurrentFormat() {
        return currentFormat;
    }

    public EShaderAttributeFormat getNewFormat() {
        return newFormat;
    }
}
