package com.bifrost3d.core.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssetManagerTest {

    @BeforeEach
    void setupTest() {
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

    @Test
    void testRelativeLoading() {
        registerReloadLoader(new ReloadData("root_name", new ResourceLocator(":more/sub/path/relative_name.txt")));
        Optional<String> optString = AssetManager.instance().load(String.class, new ResourceLocator("file:///root/path/root_name.txt"));

        assertTrue(optString.isPresent());
        assertEquals("file:///root/path/more/sub/path/relative_name.txt", optString.get());
    }

    @Test
    void testRelativeDeepLoading() {
        registerReloadLoader(
                new ReloadData("root_name", new ResourceLocator(":more/sub/path/relative_name.txt")),
                new ReloadData("relative_name", new ResourceLocator(":../another/relative/final_file.txt"))
        );
        Optional<String> optString = AssetManager.instance().load(String.class, new ResourceLocator("file:///root/path/root_name.txt"));

        assertTrue(optString.isPresent());
        assertEquals("file:///root/path/more/sub/path/../another/relative/final_file.txt", optString.get());
    }

    @Test
    void testStackEmptyAfterDeepLoading() {
        registerReloadLoader(
                new ReloadData("root_name", new ResourceLocator(":more/sub/path/relative_name.txt")),
                new ReloadData("relative_name", new ResourceLocator(":../another/relative/final_file.txt"))
        );
        Optional<String> optString = AssetManager.instance().load(String.class, new ResourceLocator("file:///root/path/root_name.txt"));


        assertTrue(optString.isPresent());
        assertEquals("file:///root/path/more/sub/path/../another/relative/final_file.txt", optString.get());

        assertDoesNotThrow(() -> {
            Field locatorStack = AssetManager.class.getDeclaredField("locatorStack");
            locatorStack.setAccessible(true);
            List<?> stack = (List<?>) locatorStack.get(AssetManager.instance());
            assertTrue(stack.isEmpty());
        });
    }


    private void registerDefaultStringLoader(String returnValue) {
        AssetManager.instance().registerLoader(new IAssetLoader<String>() {
            @Override
            public boolean canLoad(Class<?> cls, ResourceLocator locator) {
                return cls.isAssignableFrom(String.class);
            }

            @Override
            public Optional<String> load(Class<?> cls, ResourceLocator locator) {
                if (cls.isAssignableFrom(String.class)) {
                    return Optional.of(returnValue);
                }
                return Optional.empty();
            }
        });
    }


    @Data
    @AllArgsConstructor
    private static class ReloadData {
        private final String onName;
        private final ResourceLocator loadLocator;
    }

    private void registerReloadLoader(ReloadData... data) {
        AssetManager.instance().registerLoader(new IAssetLoader<String>() {
            @Override
            public boolean canLoad(Class<?> cls, ResourceLocator locator) {
                return cls.isAssignableFrom(String.class);
            }

            @Override
            public Optional<String> load(Class<?> cls, ResourceLocator locator) {
                if (cls.isAssignableFrom(String.class)) {

                    for (ReloadData datum : data) {
                        if (locator.getName().equals(datum.onName)) {
                            return AssetManager.instance().load(cls, datum.loadLocator);
                        }
                    }
                    return Optional.of(locator.getEncoded());
                }
                return Optional.empty();
            }
        });

    }
}
