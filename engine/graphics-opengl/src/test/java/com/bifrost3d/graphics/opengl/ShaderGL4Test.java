package com.bifrost3d.graphics.opengl;

import com.bifrost3d.core.graphics.EShaderType;
import com.bifrost3d.core.graphics.IllegalShaderTypeException;
import com.bifrost3d.core.graphics.ShaderCompileException;
import com.bifrost3d.graphics.opengl.gl4.ShaderGL4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShaderGL4Test extends GL4TestBase {


    @Test
    void createIllegalShader() {
        assertThrows(IllegalShaderTypeException.class, this::createIllegalShaderCall);
    }

    private void createIllegalShaderCall() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(null);
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }
    @Test
    void createVertexShader() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.VERTEX);

            assertNotEquals(0, shader.glName());
            assertEquals(EShaderType.VERTEX, shader.getType());
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }

    @Test
    void createFragmentShader() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.FRAGMENT);

            assertNotEquals(0, shader.glName());
            assertEquals(EShaderType.FRAGMENT, shader.getType());
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }

    @Test
    void createEvalShader() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.TESS_EVAL);

            assertNotEquals(0, shader.glName());
            assertEquals(EShaderType.TESS_EVAL, shader.getType());
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }
    @Test
    void createControlShader() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.TESS_CTRL);

            assertNotEquals(0, shader.glName());
            assertEquals(EShaderType.TESS_CTRL, shader.getType());
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }


    @Test
    void createGeometryShader() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.GEOMETRY);

            assertNotEquals(0, shader.glName());
            assertEquals(EShaderType.GEOMETRY, shader.getType());
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }

    @Test
    void createComputeShader() {
        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.COMPUTE);

            assertNotEquals(0, shader.glName());
            assertEquals(EShaderType.COMPUTE, shader.getType());
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }


    @Test
    void compileSimpleVertexShader() {
        assertDoesNotThrow(this::compileSimpleVertexShaderCall);
    }

    private void compileSimpleVertexShaderCall() {
        String src = "\n"
                + "void main ()\n"
                + "{\n"
                + "  gl_Position = vec4(0, 0, 0, 1);\n"
                + "}\n"
                + "\n";

        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.VERTEX);
            shader.setSource(src);
            shader.compile();
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }


    @Test
    void failCompilationSimpleVertexShader() {
        ShaderCompileException exception = assertThrows(ShaderCompileException.class, this::failCompilationSimpleVertexShaderCall);
        System.out.println(exception.getCompilerLog());

    }

    private void failCompilationSimpleVertexShaderCall() {
        String src = "\n"
                + "void main ()\n"
                + "{\n"
                + "  gl_Position2 = vec4(0, 0, 0, 1);\n"
                + "}\n"
                + "\n";


        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.VERTEX);
            shader.setSource(src);
            shader.compile();
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }





    @Test
    void compileSimpleFragmentShader() {
        assertDoesNotThrow(this::compileSimpleFragmentShaderCall);
    }

    private void compileSimpleFragmentShaderCall() {
        String src = "\n"
                + "void main ()\n"
                + "{\n"
                + "  gl_FragColor = vec4(0, 0, 0, 1);\n"
                + "}\n"
                + "\n";

        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.FRAGMENT);
            shader.setSource(src);
            shader.compile();
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }


    @Test
    void failCompilationSimpleFragmentShader() {
        assertThrows(ShaderCompileException.class, this::failCompilationSimpleFragmentShaderCall);
    }

    private void failCompilationSimpleFragmentShaderCall() {
        String src = "\n"
                + "void main ()\n"
                + "{\n"
                + "  gl_FragColor2 = vec4(0, 0, 0, 1);\n"
                + "}\n"
                + "\n";


        ShaderGL4 shader = null;
        try {
            shader = new ShaderGL4(EShaderType.FRAGMENT);
            shader.setSource(src);
            shader.compile();
        } finally {
            if (shader != null) {
                shader.delete();
            }
        }
    }
}
