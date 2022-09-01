package com.bifrost3d.core.graphics;

public class InvalidLayerException extends RuntimeException {

    private final int layer;

    public InvalidLayerException(int layer) {
        super (String.format("Layer %d not existing", layer));
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
