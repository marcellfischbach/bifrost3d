package com.bifrost3d.core.graphics;

public class InvalidMeshConfigurationException extends RuntimeException {

    public enum Reason {
        NO_VERTICES,
        COLORS_NOT_MATCHING_VERTICES,
        NORMALS_NOT_MATCHING_VERTICES,
        TANGENTS_NOT_MATCHING_VERTICES,
        BI_NORMALS_NOT_MATCHING_VERTICES,
        UVS_NOT_MATCHING_VERTICES,
    }

    private final Reason reason;

    public InvalidMeshConfigurationException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
