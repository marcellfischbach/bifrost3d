package com.bifrost3d.core.resource;

import java.io.IOException;
import java.io.InputStream;

public interface ReadAllFromInputStream {

    static String read(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (inputStream.available() > 0) {
            byte[] buffer = new byte[inputStream.available() + 1];
            int size = inputStream.read(buffer, 0, buffer.length - 1);
            if (size >= 0) {
                buffer[size] = '\0';
            }
            sb.append(new String(buffer));
        }
        return sb.toString();
    }


}