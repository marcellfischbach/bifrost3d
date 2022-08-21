package com.bifrost3d.core.resource;

public class InvalidProtocolException extends RuntimeException {

    private final String resource;

    public InvalidProtocolException(String resource) {
        super("Invalid resource for resource: " + resource);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
