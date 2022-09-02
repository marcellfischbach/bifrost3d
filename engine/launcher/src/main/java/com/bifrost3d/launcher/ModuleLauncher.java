package com.bifrost3d.launcher;

import com.bifrost3d.core.AbstractModule;
import com.bifrost3d.core.Engine;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.window.IWindow;
import com.bifrost3d.launcher.window.WindowGLFW;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;
import java.util.Set;

public class ModuleLauncher extends AbstractModule {

    private static final Logger LOGGER = LogManager.getLogger(ModuleLauncher.class);

    @Override
    public Set<Class<?>> provides() {
        return classes(IWindow.class);
    }

    @Override
    public void register(Engine engine) {
        LOGGER.info("Register");

        WindowGLFW window = new WindowGLFW();
        ObjectRegistry.register(IWindow.class, window);
    }

    @Override
    public void initialize(Engine engine) {
        LOGGER.info("Initialize");
        GLFW.glfwInit();


        Optional<WindowGLFW> iWindow = ObjectRegistry.get(IWindow.class, WindowGLFW.class);
        iWindow.ifPresent(window -> {
            window.setWidth(1024);
            window.setHeight(768);
//            window.setVSync(true);
            window.initialize();
        });
    }
}
