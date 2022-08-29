package com.bifrost3d.graphics.opengl;

import com.bifrost3d.core.graphics.EShaderType;
import com.bifrost3d.core.graphics.ProgramLinkException;
import com.bifrost3d.graphics.opengl.gl4.ProgramGL4;
import com.bifrost3d.graphics.opengl.gl4.ShaderGL4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramGL4Test extends GL4TestBase {

    @Test
    void createProgram () {
        assertDoesNotThrow(this::createProgramCall);
    }

    private void createProgramCall () {
        ProgramGL4 program = null;
        try {
            program = new ProgramGL4();

            assertNotEquals(0, program.glName());
        }
        finally {
            if (program != null) {
                program.delete();
            }
        }
    }

    @Test
    void createFullyLinkedProgram () {
        assertDoesNotThrow(this::createFullyLinkedProgramCall);
    }

    private void createFullyLinkedProgramCall () {
        ProgramGL4 program = null;
        ShaderGL4 vertex = null;
        ShaderGL4 fragment = null;
        try {
            vertex = createShader(EShaderType.VERTEX, "\n" +
                    "#version 330\n" +
                    "out vec4 value;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   value = vec4(1, 1, 1, 1);\n" +
                    "   gl_Position = vec4(0, 0, 0, 1);\n" +
                    "}\n");
            fragment = createShader(EShaderType.FRAGMENT, "\n" +
                    "#version 330\n" +
                    "in vec4 value;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   gl_FragColor = value;\n" +
                    "}\n");

            program = new ProgramGL4();
            program.attach(vertex);
            program.attach(fragment);
            program.link();

        }
        finally {
            if (program != null) {
                program.delete();
            }
            if (vertex != null) {
                vertex.delete();
            }
            if (fragment != null) {
                fragment.delete();
            }
        }
    }

    @Test
    void createNotLinkableProgram () {
        ProgramLinkException exception = assertThrows(ProgramLinkException.class, this::createNotLinkableProgramCall);
        System.out.println(exception.getLinkerLog());
    }

    private void createNotLinkableProgramCall () {
        ProgramGL4 program = null;
        ShaderGL4 vertex = null;
        ShaderGL4 fragment = null;
        try {
            vertex = createShader(EShaderType.VERTEX, "\n" +
                    "#version 330\n" +
                    "out mat4 value;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   value = mat4(vec4(1, 0, 0, 0)," +
                    "                vec4(0, 1, 0, 0)," +
                    "                vec4(0, 0, 1, 0)," +
                    "                vec4(0, 0, 0, 1));\n" +
                    "   gl_Position = vec4(0, 0, 0, 1);\n" +
                    "}\n");
            fragment = createShader(EShaderType.FRAGMENT, "\n" +
                    "#version 330\n" +
                    "in vec4 value;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   gl_FragColor = value;\n" +
                    "}\n");

            program = new ProgramGL4();
            program.attach(vertex);
            program.attach(fragment);
            program.link();

        }
        finally {
            if (program != null) {
                program.delete();
            }
            if (vertex != null) {
                vertex.delete();
            }
            if (fragment != null) {
                fragment.delete();
            }
        }
    }



    private ShaderGL4 createShader (EShaderType type, String src) {
        ShaderGL4 shader = new ShaderGL4(type);
        shader.setSource(src);
        shader.compile();
        return shader;
    }
}
