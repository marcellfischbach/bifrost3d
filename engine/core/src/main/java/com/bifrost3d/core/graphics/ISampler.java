package com.bifrost3d.core.graphics;

import com.bifrost3d.math.ColorRGBA;

public interface ISampler {


    void setFilter(ETextureFilter filter);
    ETextureFilter getFilter();

    void setAnisotropy(int anisotropy);
    int getAnisotropy ();

    void setMinLOD(int minLOD);
    int getMinLOD();

    void setMaxLOD(int maxLOD);
    int getMaxLOD();

    void setTextureWrapS(ETextureWrap wrap);
    ETextureWrap getTextureWrapS();

    void setTextureWrapT(ETextureWrap wrap);
    ETextureWrap getTextureWrapT();

    void setTextureWrapR(ETextureWrap wrap);
    ETextureWrap getTextureWrapR();

    void setBorderColor(ColorRGBA color);
    ColorRGBA getBorderColor();

    void setCompareMode(ETextureCompareMode mode);
    ETextureCompareMode getCompareMode();

    void setCompareFunc(ETextureCompareFunc func);
    ETextureCompareFunc getCompareFunc();

}
