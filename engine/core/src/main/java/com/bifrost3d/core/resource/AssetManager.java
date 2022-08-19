package com.bifrost3d.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssetManager {


    private final List<IAssetLoader> loaders = new ArrayList<>();

    public void reset() {
        this.loaders.clear();
    }

    public void registerLoader(IAssetLoader loader) {
        this.loaders.add(loader);
    }


    public <T> Optional<T> load(Class<T> cls, ResourceLocator locator) {
        for (IAssetLoader loader : this.loaders) {
            if (loader.canLoad(cls, locator)) {

                Optional<T> optObject = loader.load(cls, locator);
                if (optObject.isPresent()) {
                    return optObject;
                }
            }
        }
        return Optional.empty();
    }


    private static AssetManager instance;

    private AssetManager() {
    }

    public static AssetManager instance() {
        if (AssetManager.instance == null) {
            AssetManager.instance = new AssetManager();
        }
        return AssetManager.instance;
    }



}
