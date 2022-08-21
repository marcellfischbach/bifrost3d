package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.resource.IAssetLoader;
import com.bifrost3d.core.resource.ResourceLocator;

import java.util.Optional;

@SuppressWarnings("unused")
public class ShaderLoaderGL4 implements IAssetLoader {


    @Override
    public boolean canLoad(Class<?> cls, ResourceLocator locator) {
        String ext = locator.getExt();
        return cls.isAssignableFrom(ShaderGL4.class) && (
                "vert".equals(ext)
                        || "ctrl".equals(ext)
                        || "eval".equals(ext)
                        || "geom".equals(ext)
                        || "frag".equals(ext)
                        || "comp".equals(ext)
        );

    }

    @Override
    public <T> Optional<T> load(Class<T> cls, ResourceLocator locator) {
        return Optional.empty();
    }
}
