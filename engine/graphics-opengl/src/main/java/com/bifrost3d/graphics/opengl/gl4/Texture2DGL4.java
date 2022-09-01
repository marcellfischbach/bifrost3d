package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EPixelFormat;
import com.bifrost3d.core.graphics.ITexture2D;
import com.bifrost3d.core.graphics.Image;
import com.bifrost3d.math.Mathf;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL44.*;

public class Texture2DGL4 extends TextureGL4 implements ITexture2D {

    private final EPixelFormat format;

    private final int width;

    private final int height;

    private final boolean mipmap;

    public Texture2DGL4(GraphicsGL4 graphics, ITexture2D.Descriptor descriptor) {
        super(graphics, GL_TEXTURE_2D);
        this.format = descriptor.format;
        this.width = descriptor.width;
        this.height = descriptor.height;
        this.mipmap = descriptor.mipmap;

        initBuffers();
    }

    @Override
    public EPixelFormat getFormat() {
        return format;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private void initBuffers() {
        int numLevels = 1;
        if (this.mipmap) {
            int numLevelsWidth = Mathf.numPowerOfTwo(this.width);
            int numLevelsHeight = Mathf.numPowerOfTwo(this.height);
            numLevels = Math.max(numLevelsWidth, numLevelsHeight);
        }

        glTexStorage2D(
                this.target,
                numLevels,
                PixelFormatMapGL4.internal[this.format.ordinal()],
                this.width,
                this.height);
    }

    @Override
    public void setImage(Image image) {
        this.graphics.bindTemp(this);


        int pfOrdinal = image.getPixelFormat().ordinal();

        for (int i = 0; i < image.getLayerCount(); i++) {
            Image.Layer layer = image.getLayer(i);
            ByteBuffer buffer = BufferUtils.createByteBuffer(layer.bBuffer.length);
            buffer.put(layer.bBuffer);
            buffer.clear();

            glTexImage2D(
                    this.target,
                    i,
                    PixelFormatMapGL4.internal[pfOrdinal],
                    layer.width,
                    layer.height,
                    0,
                    PixelFormatMapGL4.client[pfOrdinal],
                    PixelFormatMapGL4.format[pfOrdinal],
                    buffer
            );


        }
    }
}
