package com.bifrost3d.core.resource;

public class InvalidProtocolException extends RuntimeException {

    private final String protocol;

    public InvalidProtocolException(String protocol) {
        super("Invalid protocol: " + protocol);
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }
}
