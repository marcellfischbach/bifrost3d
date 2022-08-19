package com.bifrost3d.core.resource;

import java.util.Optional;

public interface IAssetLoader {


    boolean canLoad(Class<?> cls, ResourceLocator locator);

    <T> Optional<T> load(Class<T> cls, ResourceLocator locator);

}
