package com.bifrost3d.core.graphics;

import lombok.AllArgsConstructor;

public interface ITexture2D extends ITexture {

    EPixelFormat getFormat ();
    int getWidth();
    int getHeight ();



    void setImage (Image image);

    @AllArgsConstructor
    class Descriptor {
        public final EPixelFormat format;
        public final int width;
        public final int height;
        public final boolean mipmap;
    }


}
