package com.bifrost3d.graphics.opengl.gl4.loader;

import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.EShaderType;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.graphics.IllegalShaderTypeException;
import com.bifrost3d.core.resource.*;
import com.bifrost3d.graphics.opengl.gl4.GraphicsGL4;
import com.bifrost3d.graphics.opengl.gl4.ShaderGL4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        Optional<ShaderSource> optShaderSource = AssetManager.instance().load(ShaderSource.class, locator.asLocal());

        return optShaderSource.map(shaderSource -> loadShader(getType(locator), shaderSource));
    }

    private ShaderGL4 loadShader(EShaderType shaderType, ShaderSource shaderSource) {
        try {

            String source = shaderSource.getSource();
            source = ShaderCodeParser.parse(source);
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
        switch (ext) {
            case "vert":
            case "vs":
                return EShaderType.VERTEX;
            case "frag":
                return EShaderType.FRAGMENT;
            case "geom":
                return EShaderType.GEOMETRY;
            case "ctrl":
                return EShaderType.TESS_CTRL;
            case "eval":
                return EShaderType.TESS_EVAL;
            case "comp":
                return EShaderType.COMPUTE;
            default:
                break;
        }
        throw new IllegalShaderTypeException();
    }

}
