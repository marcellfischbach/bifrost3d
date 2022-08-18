package com.bifrost3d.graphics.opengl;

import com.bifrost3d.core.AbstractModule;
import com.bifrost3d.core.Engine;
import com.bifrost3d.core.IModule;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.IDevice;
import com.bifrost3d.core.window.IWindow;
import com.bifrost3d.graphics.opengl.gl4.DeviceGL4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class ModuleGL4 extends AbstractModule{

    private static final Logger LOGGER = LogManager.getLogger(ModuleGL4.class);

    @Override
    public Set<Class<?>> provides() {
        return classes(IDevice.class);
    }

    @Override
    public Set<Class<?>> dependencies() {
        return classes(IWindow.class);
    }

    @Override
    public void register(Engine engine) {
        LOGGER.info("Register");

        DeviceGL4 device = new DeviceGL4();
        ObjectRegistry.register(IDevice.class, device);

    }

    @Override
    public void initialize(Engine engine) {
        LOGGER.info("Initialize");

        ObjectRegistry.get(IDevice.class, DeviceGL4.class).ifPresent(device -> {
            device.initialize();
        });
    }
}
