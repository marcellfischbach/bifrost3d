package com.crimsonedge.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ObjectRegistry {

    private ObjectRegistry() {
    }

    public static <T> Optional<T> get(Class<T> cls) {
        Object value = registry.get(cls);
        if (cls.isInstance(value)) {
            return Optional.of(cls.cast(value));
        }
        return Optional.empty();
    }

    public static <T> void register(Class<T> cls, T value) {
        registry.put(cls, value);
    }


    private static final Map<Class<?>, Object> registry = new HashMap<>();
}
