package com.bifrost3d.core.graphics;

public class InvalidMeshConfigurationException extends RuntimeException {

    public enum Reason {
        NoVertices,
        ColorsNotMatchingVertices,
        NormalsNotMatchingVertices,
        TangentsNotMatchingVertices,
        BiNormalsNotMatchingVertices,
        UvsNotMatchingVertices,
    }

    private final Reason reason;

    public InvalidMeshConfigurationException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
