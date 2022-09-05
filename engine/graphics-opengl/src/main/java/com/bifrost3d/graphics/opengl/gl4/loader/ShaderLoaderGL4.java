package com.bifrost3d.graphics.opengl.gl4.loader;

import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.EShaderType;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.graphics.IllegalShaderTypeException;
import com.bifrost3d.core.resource.IAssetLoader;
import com.bifrost3d.core.resource.ReadAllFromInputStream;
import com.bifrost3d.core.resource.ResourceLocator;
import com.bifrost3d.core.resource.VFS;
import com.bifrost3d.graphics.opengl.gl4.GraphicsGL4;
import com.bifrost3d.graphics.opengl.gl4.ShaderGL4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Optional;

public class ShaderLoaderGL4 implements IAssetLoader<ShaderGL4> {

    private static final Logger LOGGER = LogManager.getLogger(ShaderLoaderGL4.class);

    @Override
    public boolean canLoad(Class<?> cls, ResourceLocator locator) {
        String ext = locator.getExt();
        return cls.isAssignableFrom(ShaderGL4.class) &&
                ("vert".equals(ext)
                        || "ctrl".equals(ext)
                        || "eval".equals(ext)
                        || "geom".equals(ext)
                        || "frag".equals(ext)
                        || "comp".equals(ext));
    }

    @Override
    public Optional<ShaderGL4> load(Class<?> cls, ResourceLocator locator) {
        Optional<InputStream> optInputStream = VFS.instance().open(locator);
        return optInputStream.map(inputStream -> loadShader(getType(locator), inputStream));
    }

    private ShaderGL4 loadShader(EShaderType shaderType, InputStream inputStream) {
        try {
            String source = ReadAllFromInputStream.read(inputStream);
            Optional<GraphicsGL4> optGraphics = ObjectRegistry.get(IGraphics.class, GraphicsGL4.class);
            if (optGraphics.isPresent()) {
                GraphicsGL4 gl4 = optGraphics.get();
                ShaderGL4 shader = gl4.createShader(shaderType);
                shader.setSource(source);
                shader.compile();
                return shader;
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    private EShaderType getType(ResourceLocator locator) {
        String ext = locator.getExt().toLowerCase();
        if ("vert".equals(ext) || "vs".equals(ext)) {
            return EShaderType.VERTEX;
        } else if ("frag".equals(ext)) {
            return EShaderType.FRAGMENT;
        } else if ("geom".equals(ext)) {
            return EShaderType.GEOMETRY;
        } else if ("ctrl".equals(ext)) {
            return EShaderType.TESS_CTRL;
        } else if ("eval".equals(ext)) {
            return EShaderType.TESS_EVAL;
        } else if ("comp".equals(ext)) {
            return EShaderType.COMPUTE;
        }
        throw new IllegalShaderTypeException();
    }

}
