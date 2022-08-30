package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EShaderAttributeType;

public abstract class ShaderAttribTypeMapGL4 {

    protected static final String[] map = new String[EShaderAttributeType.values().length];

    static {
        register(EShaderAttributeType.MODEL_MATRIX, "ModelMatrix");
        register(EShaderAttributeType.VIEW_MATRIX, "ViewMatrix");
        register(EShaderAttributeType.PROJECTION_MATRIX, "ProjectionMatrix");

        register(EShaderAttributeType.VIEW_MODEL_MATRIX, "ViewModelMatrix");
        register(EShaderAttributeType.PROJECTION_VIEW_MATRIX, "ProjectionViewMatrix");
        register(EShaderAttributeType.PROJECTION_VIEW_MODEL_MATRIX, "ProjectionViewModelMatrix");

        register(EShaderAttributeType.MODEL_MATRIX_INV, "ModelMatrixInv");
        register(EShaderAttributeType.VIEW_MATRIX_INV, "ViewMatrixInv");
        register(EShaderAttributeType.PROJECTION_MATRIX_INV, "ProjectionMatrixInv");

        register(EShaderAttributeType.VIEW_MODEL_MATRIX_INV, "ViewModelMatrixInv");
        register(EShaderAttributeType.PROJECTION_VIEW_MATRIX_INV, "ProjectionViewMatrixInv");
        register(EShaderAttributeType.PROJECTION_VIEW_MODEL_MATRIX_INV, "ProjectionViewModelMatrixInv");
    }




    private static void register (EShaderAttributeType type, String name) {
        map[type.ordinal()] = name;
    }

    private ShaderAttribTypeMapGL4 () {

    }

}
