package com.bifrost3d.core.graphics;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface ITexture2D extends ITexture {

    void setImage (Image image);

    @NoArgsConstructor
    @AllArgsConstructor
    class Descriptor {
        EPixelFormat format;
        int width;
        int height;
        boolean mipmap;
    }


}
