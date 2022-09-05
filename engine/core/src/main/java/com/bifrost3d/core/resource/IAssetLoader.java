package com.bifrost3d.core.resource;

import java.util.Optional;

public interface IAssetLoader<T> {

    boolean canLoad(Class<?> cls, ResourceLocator locator);

    Optional<T> load(Class<?> cls, ResourceLocator locator);

}
