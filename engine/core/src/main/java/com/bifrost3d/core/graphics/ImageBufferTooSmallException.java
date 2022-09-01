package com.bifrost3d.core.graphics;

public class ImageBufferTooSmallException extends RuntimeException {

    private final int minimumSizeExpected;

    private final int actualSize;

    public ImageBufferTooSmallException(int minimumSizeExpected, int actualSize) {
        super(String.format("Need at least %d byte but actually have %d bytes", minimumSizeExpected, actualSize));
        this.minimumSizeExpected = minimumSizeExpected;
        this.actualSize = actualSize;
    }

    public int getMinimumSizeExpected() {
        return minimumSizeExpected;
    }

    public int getActualSize() {
        return actualSize;
    }
}
