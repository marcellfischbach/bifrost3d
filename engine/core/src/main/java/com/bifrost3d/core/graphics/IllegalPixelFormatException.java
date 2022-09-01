package com.bifrost3d.core.graphics;

public class IllegalPixelFormatException extends RuntimeException {

    private final EPixelFormat pixelFormat;

    public IllegalPixelFormatException(EPixelFormat pixelFormat) {
        super(String.format("Illegal pixel format %s", pixelFormat));
        this.pixelFormat = pixelFormat;
    }

    public EPixelFormat getPixelFormat() {
        return pixelFormat;
    }
}
