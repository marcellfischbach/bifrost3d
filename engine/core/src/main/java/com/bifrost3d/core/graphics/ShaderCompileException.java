package com.bifrost3d.core.graphics;

import lombok.Getter;

public class ShaderCompileException extends RuntimeException {

    @Getter
    private final String compilerLog;

    public ShaderCompileException(String compilerLog) {
        super("Compilation failed:\n" + compilerLog);
        this.compilerLog = compilerLog;
    }

}
