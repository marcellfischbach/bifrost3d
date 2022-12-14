package com.bifrost3d.graphics.opengl;

import com.bifrost3d.core.AbstractModule;
import com.bifrost3d.core.Engine;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.resource.AssetManager;
import com.bifrost3d.core.window.IWindow;
import com.bifrost3d.graphics.opengl.gl4.GraphicsGL4;
import com.bifrost3d.graphics.opengl.gl4.loader.ProgramLoaderGL4;
import com.bifrost3d.graphics.opengl.gl4.loader.ShaderLoaderGL4;
import com.bifrost3d.graphics.opengl.gl4.loader.ShaderSourceLoaderGL4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class ModuleGL4 extends AbstractModule{

    private static final Logger LOGGER = LogManager.getLogger(ModuleGL4.class);

    @Override
    public Set<Class<?>> provides() {
        return classes(IGraphics.class);
    }

    @Override
    public Set<Class<?>> dependencies() {
        return classes(IWindow.class);
    }

    @Override
    public void register(Engine engine) {
        LOGGER.info("Register");

        GraphicsGL4 device = new GraphicsGL4();
        ObjectRegistry.register(IGraphics.class, device);

        AssetManager.instance().registerLoader(new ShaderLoaderGL4());
        AssetManager.instance().registerLoader(new ShaderSourceLoaderGL4());
        AssetManager.instance().registerLoader(new ProgramLoaderGL4());
    }

    @Override
    public void initialize(Engine engine) {
        LOGGER.info("Initialize");

        ObjectRegistry.get(IGraphics.class, GraphicsGL4.class).ifPresent(GraphicsGL4::initialize);
    }
}
