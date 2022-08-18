package com.bifrost3d.core;

import java.util.HashSet;
import java.util.Set;

public interface IModule {

    default Set<Class<?>> dependencies() {
        return new HashSet<>();
    }

    Set<Class<?>> provides ();

    void register(Engine engine);

    void initialize(Engine engine);

}
