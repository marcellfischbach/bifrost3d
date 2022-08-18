package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.IProgram;
import com.bifrost3d.core.graphics.IShader;

import static org.lwjgl.opengl.GL44.*;

public class ProgramGL4 implements IProgram {

    private final int name;

    public ProgramGL4() {
        this.name = glCreateProgram();
    }

    @Override
    public void attach(IShader shader) {
        glAttachShader(this.name, ((ShaderGL4) shader).glName());
    }

    @Override
    public void detach(IShader shader) {
        glDetachShader(this.name, ((ShaderGL4) shader).glName());
    }

    @Override
    public void link() {
        glLinkProgram(this.name);
        int status = glGetProgrami(this.name, GL_LINK_STATUS);
        if (status == GL_FALSE) {
            String log = glGetProgramInfoLog(this.name);
            throw new RuntimeException(log);
        }
    }
}
