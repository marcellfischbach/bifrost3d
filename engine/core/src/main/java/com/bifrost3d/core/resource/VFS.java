package com.bifrost3d.core.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

@SuppressWarnings("unused")
public abstract class VFS {

    private static final Logger LOGGER = LogManager.getLogger(VFS.class);

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
            case LOCAL:
                return Optional.empty();

        }
        return Optional.empty();
    }


    private Optional<InputStream> openFileFromArchive(ResourceLocator locator) {
        String fullFilename = locator.getFullFilename();
        InputStream resourceAsStream = VFS.class.getResourceAsStream(fullFilename);
        return Optional.ofNullable(resourceAsStream);
    }


    private Optional<InputStream> openFileFromFileSystem(ResourceLocator locator) {
        try {
            String absoluteFilename = this.fileRootPath + File.separator + locator.getFullFilename();
            File file = new File(absoluteFilename);
            if (file.exists()) {
                return Optional.of(Files.newInputStream(file.toPath()));
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }

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
