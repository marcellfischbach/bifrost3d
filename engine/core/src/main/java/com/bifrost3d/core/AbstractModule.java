package com.bifrost3d.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractModule implements IModule{

    protected Set<Class<?>> classes (Class<?> ... cls) {
        return new HashSet<>(Arrays.asList(cls));
    }
}
