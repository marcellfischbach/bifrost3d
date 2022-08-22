package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EShaderType;
import com.bifrost3d.core.graphics.IShader;
import com.bifrost3d.core.graphics.IllegalShaderTypeException;
import com.bifrost3d.core.graphics.ShaderCompileException;

import static org.lwjgl.opengl.GL44.*;

public class ShaderGL4 implements IShader {

    private final EShaderType type;

    private int name;


    public ShaderGL4(EShaderType type) {
        this.type = type;
        this.name = glCreateShader(glShaderType(type));
    }


    public EShaderType getType() {
        return type;
    }

    public void delete() {
        if (this.name != 0) {
            glDeleteShader(this.name);
            this.name = 0;
        }
    }

    public int glName() {
        return this.name;
    }

    @Override
    public void setSource(String source) {
        glShaderSource(this.name, source);
    }


    @Override
    public void compile() {
        glCompileShader(this.name);

        int status = glGetShaderi(this.name, GL_COMPILE_STATUS);
        if (status == GL_FALSE) {
            String log = glGetShaderInfoLog(this.name); throw new ShaderCompileException(log);
        }
    }


    private static int glShaderType(EShaderType type) {
        if (type != null) {
            switch (type) {
                case Vertex:
                    return GL_VERTEX_SHADER;
                case Control:
                    return GL_TESS_CONTROL_SHADER;
                case Eval:
                    return GL_TESS_EVALUATION_SHADER;
                case Geometry:
                    return GL_GEOMETRY_SHADER;
                case Fragment:
                    return GL_FRAGMENT_SHADER;
                case Compute:
                    return GL_COMPUTE_SHADER;
            }
        }
        throw new IllegalShaderTypeException();
    }

}
