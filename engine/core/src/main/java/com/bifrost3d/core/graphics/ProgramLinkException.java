package com.bifrost3d.core.graphics;

import lombok.Getter;

public class ProgramLinkException extends RuntimeException {

    @Getter
    private final String linkerLog;

    public ProgramLinkException(String linkerLog) {
        super("Program link failed:\n" + linkerLog);
        this.linkerLog = linkerLog;
    }

}
