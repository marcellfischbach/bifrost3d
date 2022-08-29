package com.bifrost3d.core.resource;

import java.io.InputStream;
import java.util.Optional;

@SuppressWarnings("unused")
public abstract class VFS {

    private String fileRootPath = "";

    public void setFileRootPath(String fileRootPath) {
        this.fileRootPath = fileRootPath;
    }

    public Optional<InputStream> open(String resourceLocator) {
        return openNullSafe(new ResourceLocator(resourceLocator));
    }


    public Optional<InputStream> open(ResourceLocator locator) {
        if (locator == null) {
            return Optional.empty();
        }

        return openNullSafe(locator);
    }


    private Optional<InputStream> openNullSafe(ResourceLocator locator) {
        switch (locator.getProtocol()) {
            case ARCHIVE:
                return openFileFromArchive(locator);
            case FILE:
                return openFileFromFileSystem(locator);

        }
        return Optional.empty();
    }


    private Optional<InputStream> openFileFromArchive(ResourceLocator locator) {
        return Optional.empty();
    }


    private Optional<InputStream> openFileFromFileSystem(ResourceLocator locator) {
        return Optional.empty();
    }


    private VFS() {
    }


    private static final VFS instance = new VFS() {
    };

    public static VFS instance() {
        return instance;
    }

}
