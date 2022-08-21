package com.bifrost3d.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssetManager {

    private final List<IAssetLoader> loaders = new ArrayList<>();

    private final List<ResourceLocator> locatorStack = new ArrayList<>();


    public void reset() {
        this.loaders.clear();
        this.locatorStack.clear();
    }

    public void registerLoader(IAssetLoader loader) {
        this.loaders.add(loader);
    }

    public <T> Optional<T> load(Class<T> cls, ResourceLocator locator) {
        pushStack(locator);

        ResourceLocator absoluteLocator = buildAbsoluteResourceLocator();
        try {
            for (IAssetLoader loader : this.loaders) {
                if (loader.canLoad(cls, absoluteLocator)) {

                    Optional<T> optObject = loader.load(cls, absoluteLocator);
                    if (optObject.isPresent()) {
                        return optObject;
                    }
                }
            }
            return Optional.empty();
        } finally {
            popStack();
        }
    }


    private void pushStack(ResourceLocator locator) {
        this.locatorStack.add(locator);
    }

    private void popStack() {
        if (this.locatorStack.isEmpty()) {
            throw new StackUnderflowException();
        }
        this.locatorStack.remove(this.locatorStack.size() - 1);
    }

    private ResourceLocator buildAbsoluteResourceLocator() {
        String absoluteEncodedResource = buildAbsoluteEncodedResourceFromStack();
        return new ResourceLocator(absoluteEncodedResource);
    }

    private String buildAbsoluteEncodedResourceFromStack() {
        StringBuilder sb = new StringBuilder();

        int lastAbsolutePathIndexOnStack = findLastAbsolutePathIndexOnStack();
        ResourceLocator locator = this.locatorStack.get(lastAbsolutePathIndexOnStack);
        sb.append(locator.getProtocol().value);
        sb.append(locator.getPath());

        for (int i = lastAbsolutePathIndexOnStack + 1, in = this.locatorStack.size() - 1; i < in; i++) {
            locator = this.locatorStack.get(i);
            sb.append(locator.getPath());
        }

        locator = this.locatorStack.get(this.locatorStack.size() - 1);
        sb.append(locator.getPath());
        sb.append(locator.getName());
        if (!locator.getExt().isEmpty()) {
            sb.append(".");
            sb.append(locator.getExt());
        }

        return sb.toString();
    }

    private int findLastAbsolutePathIndexOnStack() {
        for (int i = this.locatorStack.size() - 1; i >= 0; i--) {
            ResourceLocator locator = this.locatorStack.get(i);
            if (locator.isAbsolute()) {
                return i;
            }
        }
        return 0;
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
