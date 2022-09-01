package com.bifrost3d.core.graphics;

import java.util.ArrayList;
import java.util.List;

public class Image {

    private final EPixelFormat pixelFormat;

    private final int bpp;

    private final Layer master = new Layer();

    private final List<Layer> layers = new ArrayList<>();


    public Image(EPixelFormat pixelFormat) {
        this(pixelFormat, 0, 0);
    }

    public Image(EPixelFormat pixelFormat, int width, int height) {
        validatePixelFormat(pixelFormat);
        this.pixelFormat = pixelFormat;
        this.bpp = bytesPerPixel(pixelFormat);

        master.width = width;
        master.height = height;
        this.layers.add(master);
        fillLayerData(master);
    }

    public int getWidth () {
        return this.master.width;
    }

    public int getHeight () {
        return this.master.height;
    }

    public EPixelFormat getPixelFormat() {
        return pixelFormat;
    }

    public void setImageData(byte[] data) {
        setImageData(0, data);
    }

    public void setImageData(int layerIdx, byte[] data) {
        Layer layer = getLayer(layerIdx);
        if (data.length < layer.bBuffer.length) {
            throw new ImageBufferTooSmallException(layer.bBuffer.length, data.length);
        }

        System.arraycopy(data, 0, layer.bBuffer, 0, layer.bBuffer.length);
    }

    public void generateMipMaps(EMipMapGenerationMode mode) {
        switch (mode) {
            case MIP_NONE:
                break;
            case MIP_2X2:
                generate2X2MipMaps();
                break;
            case MIP_NORMAL:
                generateNormalMipMaps();
                break;
        }
    }

    private void generate2X2MipMaps() {
        Layer top = master;
        int bottomLayerIdx = 1;
        while (top.width > 1 || top.height > 1) {
            Layer bottom = getLayer(bottomLayerIdx);
            generate2X2MipMaps(top, bottom);
            top = bottom;
            bottomLayerIdx++;
        }
    }

    private void generate2X2MipMaps(Layer top, Layer bottom) {

        int top00Idx = 0;
        int top01Idx = bpp;
        int top10Idx = top.width * bpp;
        int top11Idx = top.width * bpp + bpp;
        int bottomIdx = 0;
        for (int y = 0; y < bottom.height; y++) {
            for (int x = 0; x < bottom.width; x++) {

                for (int i = 0; i < bpp; ++i) {
                    float v00 = (top.bBuffer[top00Idx] & 0xff) / (float) 0xff;
                    float v01 = (top.bBuffer[top01Idx] & 0xff) / (float) 0xff;
                    float v10 = (top.bBuffer[top10Idx] & 0xff) / (float) 0xff;
                    float v11 = (top.bBuffer[top11Idx] & 0xff) / (float) 0xff;

                    float v = (v00 + v01 + v10 + v11) / 4.0f;

                    bottom.bBuffer[bottomIdx] = (byte) (v * 0xff);

                    top00Idx++;
                    top01Idx++;
                    top10Idx++;
                    top11Idx++;
                    bottomIdx++;
                }


                top00Idx += bpp;
                top01Idx += bpp;
                top10Idx += bpp;
                top11Idx += bpp;
            }
            top00Idx += top.width * bpp;
            top01Idx += top.width * bpp;
            top10Idx += top.width * bpp;
            top11Idx += top.width * bpp;

        }
    }


    private void generateNormalMipMaps() {
        Layer top = master;
        int bottomLayerIdx = 1;
        while (top.width > 1 || top.height > 1) {
            Layer bottom = getLayer(bottomLayerIdx);
            generate2X2MipMaps(top, bottom);
            top = bottom;
        }
    }

    private void generateNormalMipMaps(Layer top, Layer bottom) {

    }

    public int getLayerCount () {
        return this.layers.size();
    }

    public Layer getLayer(int layerIdx) {
        ensureLayerExisting(layerIdx);
        return this.layers.get(layerIdx);
    }

    private void ensureLayerExisting(int layerIdx) {
        while (layers.size() <= layerIdx) {
            if (!addLayer()) {
                throw new InvalidLayerException(layerIdx);
            }
        }
    }

    private boolean addLayer() {
        Layer lastLayer = this.layers.get(layers.size() - 1);
        if (lastLayer.width == 1 && lastLayer.height == 1) {
            return false;
        }
        Layer nextLayer = new Layer();
        nextLayer.width = Math.max(lastLayer.width >> 1, 1);
        nextLayer.height = Math.max(lastLayer.height >> 1, 1);
        fillLayerData(nextLayer);

        this.layers.add(nextLayer);
        return true;
    }

    private void fillLayerData(Layer layer) {
        layer.bBuffer = new byte[layer.width * layer.height * bpp];
    }


    @SuppressWarnings("java:S1301")
    private static void validatePixelFormat(EPixelFormat pf) {
        switch (pf) {
            case R8G8B8:
            case R8G8B8A8:
                return;
        }

        throw new IllegalPixelFormatException(pf);
    }

    @SuppressWarnings("java:S1301")
    private static int bytesPerPixel(EPixelFormat pf) {
        switch (pf) {
            case R8G8B8:
                return 3;
            case R8G8B8A8:
                return 4;
        }
        return 0;
    }


    public static class Layer {
        public int width;
        public int height;
        public byte[] bBuffer;
        float[] fBuffer;
    }

}
