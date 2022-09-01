package com.bifrost3d.core.graphics.material;

public class AttributeNotFoundException extends RuntimeException{

    private final String attributeName;

    public AttributeNotFoundException(String attributeName) {
        super("No attribute '" + attributeName + "' found");
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
