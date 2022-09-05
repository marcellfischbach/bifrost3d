package com.bifrost3d.core.resource;

import java.io.IOException;
import java.io.InputStream;

public abstract class ReadAllFromInputStream {

    public static String read(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (inputStream.available() > 0) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            sb.append(new String(buffer));
        }
        return sb.toString();
    }


    private ReadAllFromInputStream() {
    }
}
