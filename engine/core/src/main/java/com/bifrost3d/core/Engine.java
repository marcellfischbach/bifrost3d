package com.bifrost3d.core;

import java.util.ServiceLoader;

public class Engine {

    private Engine () {

    }

    public static Engine create () {
        return new Engine();
    }

    public void initializeModules () {
        EngineBootstrapper bootstrapper = new EngineBootstrapper(this);
        bootstrapper.register();
        bootstrapper.initialize();
    }



}
