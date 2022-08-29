package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EShaderAttributeType;
import com.bifrost3d.core.graphics.IProgram;
import com.bifrost3d.core.graphics.IShader;
import com.bifrost3d.core.graphics.ProgramLinkException;

import static org.lwjgl.opengl.GL44.*;

public class ProgramGL4 implements IProgram {

    private int name;

    public ProgramGL4() {
        this.name = glCreateProgram();
    }

    public int glName() {
        return this.name;
    }

    public void delete() {
        if (this.name != 0) {
            glDeleteProgram(this.name);
            this.name = 0;
        }
    }

    @Override
    public void attach(IShader shader) {
        glAttachShader(this.name, ((ShaderGL4) shader).glName());
    }


    @Override
    public void link() {
        glLinkProgram(this.name);
        int status = glGetProgrami(this.name, GL_LINK_STATUS);
        if (status == GL_FALSE) {
            String log = glGetProgramInfoLog(this.name);
            throw new ProgramLinkException(log);
        }

        registerDefaultAttributes ();
    }

    private void registerDefaultAttributes () {

        for (EShaderAttributeType value : EShaderAttributeType.values()) {
            String attribName = value.attributeName;

            int loc = glGetAttribLocation(this.name, ConstGL4.SHADER_PREFIX + attribName);
            if (loc != -1) {

            }
        }


    }
}
