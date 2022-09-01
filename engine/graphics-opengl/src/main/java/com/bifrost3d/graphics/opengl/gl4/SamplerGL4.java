package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.*;
import com.bifrost3d.math.ColorRGBA;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;

import static org.lwjgl.opengl.GL44.*;

public class SamplerGL4 implements ISampler {

    private final int name;

    private ETextureFilter filter = ETextureFilter.MIN_NEAREST_MAG_MIP_LINEAR;
    private int anisotropy = 1;

    private int minLOD = -1000;
    private int maxLOD = 1000;

    private ETextureWrap textureWrapS = ETextureWrap.REPEAT;
    private ETextureWrap textureWrapT = ETextureWrap.REPEAT;
    private ETextureWrap textureWrapR = ETextureWrap.REPEAT;

    private ColorRGBA borderColor = new ColorRGBA(0.0f, 0.0f, 0.0f, 0.0f);
    private final float[] borderColorBuffer = new float[]{0.0f, 0.0f, 0.0f, 0.0f};

    private ETextureCompareMode compareMode = ETextureCompareMode.NONE;
    private ETextureCompareFunc compareFunc = ETextureCompareFunc.ALWAYS;


    public SamplerGL4() {
        this.name = glGenSamplers();
        glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR);
        glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, this.anisotropy);
        glSamplerParameteri(this.name, GL_TEXTURE_MIN_LOD, this.minLOD);
        glSamplerParameteri(this.name, GL_TEXTURE_MAX_LOD, this.maxLOD);
        glSamplerParameteri(this.name, GL_TEXTURE_WRAP_S, TextureWrapMapGL4.map[this.textureWrapS.ordinal()]);
        glSamplerParameteri(this.name, GL_TEXTURE_WRAP_T, TextureWrapMapGL4.map[this.textureWrapT.ordinal()]);
        glSamplerParameteri(this.name, GL_TEXTURE_WRAP_R, TextureWrapMapGL4.map[this.textureWrapR.ordinal()]);
        glSamplerParameterfv(this.name, GL_TEXTURE_BORDER_COLOR, this.borderColorBuffer);
        glSamplerParameteri(this.name, GL_TEXTURE_COMPARE_MODE, TextureCompareModeMapGL4.map[this.compareMode.ordinal()]);
        glSamplerParameteri(this.name, GL_TEXTURE_COMPARE_FUNC, TextureCompareFuncMapGL4.map[this.compareFunc.ordinal()]);
    }

    public void bind(int unit) {
        glBindSampler(unit, this.name);
    }


    @Override
    public ETextureFilter getFilter() {
        return filter;
    }

    @Override
    public void setFilter(ETextureFilter filter) {
        if (this.filter != filter) {
            this.filter = filter;
            switch (this.filter) {

                case MIN_MAG_NEAREST:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_NEAREST_MAG_LINEAR:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_LINEAR_MAG_NEAREST:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_MAG_LINEAR:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_MAG_MIP_NEAREST:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_MAG_NEAREST_MIP_LINEAR:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_NEAREST_MAG_LINEAR_MIP_NEAREST:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_NEAREST_MAG_MIP_LINEAR:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_LINEAR_MAG_MIP_NEAREST:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_LINEAR_MAG_NEAREST_MIP_LINEAR:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_MAG_LINEAR_MIP_NEAREST:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case MIN_MAG_MIP_LINEAR:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 1);
                    break;
                case ANISOTROPIC:
                    glSamplerParameteri(this.name, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
                    glSamplerParameteri(this.name, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                    glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, this.anisotropy);
                    break;
            }
        }
    }

    @Override
    public int getAnisotropy() {
        return anisotropy;
    }

    @Override
    public void setAnisotropy(int anisotropy) {
        if (this.anisotropy != anisotropy) {
            this.anisotropy = anisotropy;
            glSamplerParameteri(this.name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, this.anisotropy);
        }
    }

    @Override
    public int getMinLOD() {
        return minLOD;
    }

    @Override
    public void setMinLOD(int minLOD) {
        if (this.minLOD != minLOD) {
            this.minLOD = minLOD;
            glSamplerParameteri(this.name, GL_TEXTURE_MIN_LOD, this.minLOD);
        }
    }

    @Override
    public int getMaxLOD() {
        return maxLOD;
    }

    @Override
    public void setMaxLOD(int maxLOD) {
        if (this.maxLOD != maxLOD) {
            this.maxLOD = maxLOD;
            glSamplerParameteri(this.name, GL_TEXTURE_MAX_LOD, this.maxLOD);
        }
    }

    @Override
    public ETextureWrap getTextureWrapS() {
        return textureWrapS;
    }

    @Override
    public void setTextureWrapS(ETextureWrap textureWrapS) {
        if (this.textureWrapS != textureWrapS) {
            this.textureWrapS = textureWrapS;
            glSamplerParameteri(this.name, GL_TEXTURE_WRAP_S, TextureWrapMapGL4.map[this.textureWrapS.ordinal()]);
        }
    }

    @Override
    public ETextureWrap getTextureWrapT() {
        return textureWrapT;
    }

    @Override
    public void setTextureWrapT(ETextureWrap textureWrapT) {
        if (this.textureWrapT != textureWrapT) {
            this.textureWrapT = textureWrapT;
            glSamplerParameteri(this.name, GL_TEXTURE_WRAP_T, TextureWrapMapGL4.map[this.textureWrapT.ordinal()]);
        }
    }

    @Override
    public ETextureWrap getTextureWrapR() {
        return textureWrapR;
    }

    @Override
    public void setTextureWrapR(ETextureWrap textureWrapR) {
        if (this.textureWrapR != textureWrapR) {
            this.textureWrapR = textureWrapR;
            glSamplerParameteri(this.name, GL_TEXTURE_WRAP_R, TextureWrapMapGL4.map[this.textureWrapR.ordinal()]);
        }
    }

    @Override
    public ColorRGBA getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(ColorRGBA borderColor) {
        if (this.borderColor != borderColor) {
            this.borderColor = borderColor;
            this.borderColorBuffer[0] = borderColor.r;
            this.borderColorBuffer[1] = borderColor.g;
            this.borderColorBuffer[2] = borderColor.b;
            this.borderColorBuffer[3] = borderColor.a;
            glSamplerParameterfv(this.name, GL_TEXTURE_BORDER_COLOR, this.borderColorBuffer);
        }
    }

    @Override
    public ETextureCompareMode getCompareMode() {
        return compareMode;
    }

    @Override
    public void setCompareMode(ETextureCompareMode compareMode) {
        if (this.compareMode != compareMode) {
            this.compareMode = compareMode;
            glSamplerParameteri(this.name, GL_TEXTURE_COMPARE_MODE, TextureCompareModeMapGL4.map[this.compareMode.ordinal()]);
        }
    }

    @Override
    public ETextureCompareFunc getCompareFunc() {
        return compareFunc;
    }

    @Override
    public void setCompareFunc(ETextureCompareFunc compareFunc) {
        if (this.compareFunc != compareFunc) {
            this.compareFunc = compareFunc;
            glSamplerParameteri(this.name, GL_TEXTURE_COMPARE_FUNC, TextureCompareFuncMapGL4.map[this.compareFunc.ordinal()]);
        }
    }
}
