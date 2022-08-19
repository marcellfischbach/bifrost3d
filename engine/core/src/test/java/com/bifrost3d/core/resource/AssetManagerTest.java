package com.bifrost3d.core.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssetManagerTest {

    @BeforeEach
    void setupTest () {
        AssetManager.instance().reset();
    }

    @Test
    void testNoLoadRegistered() {
        Optional<String> optString = AssetManager.instance().load(String.class, new ResourceLocator("file://test.txt"));

        assertFalse(optString.isPresent());
    }

    @Test
    void testLoadRegistered() {
        registerDefaultStringLoader("Test");

        Optional<String> optString = AssetManager.instance().load(String.class, new ResourceLocator("file://test.txt"));
        assertTrue(optString.isPresent());
        assertEquals("Test", optString.get());
    }


    @Test
    void testLoadNotRegistered() {
        registerDefaultStringLoader("Another");

        Optional<Date> optString = AssetManager.instance().load(Date.class, new ResourceLocator("file://test.txt"));
        assertFalse(optString.isPresent());
    }


    private void registerDefaultStringLoader (String returnValue) {
        AssetManager.instance().registerLoader(new IAssetLoader() {
            @Override
            public boolean canLoad(Class<?> cls, ResourceLocator locator) {
                return cls.isAssignableFrom(String.class);
            }

            @Override
            public <T> Optional<T> load(Class<T> cls, ResourceLocator locator) {
                if (cls.isAssignableFrom(String.class)) {
                    return Optional.of(cls.cast(returnValue));
                }
                return Optional.empty();
            }
        });
    }
}
