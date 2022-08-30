package com.bifrost3d.core.graphics;

public enum EShaderAttributeType {

    MODEL_MATRIX(EShaderAttributeFormat.MAT4),
    VIEW_MATRIX(EShaderAttributeFormat.MAT4),
    PROJECTION_MATRIX(EShaderAttributeFormat.MAT4),

    VIEW_MODEL_MATRIX(EShaderAttributeFormat.MAT4),
    PROJECTION_VIEW_MATRIX(EShaderAttributeFormat.MAT4),
    PROJECTION_VIEW_MODEL_MATRIX(EShaderAttributeFormat.MAT4),

    MODEL_MATRIX_INV(EShaderAttributeFormat.MAT4),
    VIEW_MATRIX_INV(EShaderAttributeFormat.MAT4),
    PROJECTION_MATRIX_INV(EShaderAttributeFormat.MAT4),

    VIEW_MODEL_MATRIX_INV(EShaderAttributeFormat.MAT4),
    PROJECTION_VIEW_MATRIX_INV(EShaderAttributeFormat.MAT4),
    PROJECTION_VIEW_MODEL_MATRIX_INV(EShaderAttributeFormat.MAT4);

    EShaderAttributeType(EShaderAttributeFormat format) {
        this.attributeName = convert(name());
        this.format = format;
    }

    public final String attributeName;
    public final EShaderAttributeFormat format;




    private static String convert(String enumName) {
        StringBuilder sb = new StringBuilder();
        enumName = enumName.toLowerCase();
        boolean nextUpperCase = true;
        for (int i = 0, in = enumName.length(); i<in; i++) {
            char ch = enumName.charAt(i);
            if (ch == '_') {
                nextUpperCase = true;
                continue;
            }
            if (nextUpperCase) {
                ch = Character.toUpperCase(ch);
                nextUpperCase = false;
            }

            sb.append(ch);
        }
        return sb.toString();
    }
}
