package com.bifrost3d.graphics.opengl.gl4.loader;

import com.bifrost3d.core.resource.IAssetLoader;
import com.bifrost3d.core.resource.ReadAllFromInputStream;
import com.bifrost3d.core.resource.ResourceLocator;
import com.bifrost3d.core.resource.VFS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Optional;

public class ShaderSourceLoaderGL4 implements IAssetLoader<ShaderSource> {

    private static final Logger LOGGER = LogManager.getLogger(ShaderSourceLoaderGL4.class);

    @Override
    public boolean canLoad(Class<?> cls, ResourceLocator locator) {
        String ext = locator.getExt();
        return cls.isAssignableFrom(ShaderSource.class) &&
                ("vert".equals(ext)
                        || "glsl".equals(ext)
                        || "ctrl".equals(ext)
                        || "eval".equals(ext)
                        || "geom".equals(ext)
                        || "frag".equals(ext)
                        || "comp".equals(ext));
    }

    @Override
    public Optional<ShaderSource> load(Class<?> cls, ResourceLocator locator) {
        try {
            Optional<InputStream> optInputStream = VFS.instance().open(locator);
            if (optInputStream.isPresent()) {
                InputStream inputStream = optInputStream.get();
                String source = ReadAllFromInputStream.read(inputStream);

                return Optional.of(new ShaderSource(source));
            }
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
        return Optional.empty();
    }

}
