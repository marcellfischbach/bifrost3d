package com.bifrost3d.graphics.opengl.gl4.loader;

import lombok.Getter;

public class ShaderSource {

    @Getter
    private final String source;

    public ShaderSource(String source) {
        this.source = source;
    }
}
