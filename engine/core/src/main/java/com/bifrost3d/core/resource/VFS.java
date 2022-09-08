package com.bifrost3d.core.resource;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public final class VFS {

    private static final Logger LOGGER = LogManager.getLogger(VFS.class);

    private final List<VFSVolume> volumes = new ArrayList<>();

    private String fileRootPath = "";

    public void addVolume(String volumeName, String rootPath, int priority) {
        addVolume(new VFSVolume(volumeName, rootPath, priority));
    }

    public void addVolume(VFSVolume volume) {
        this.volumes.add(volume);
        this.volumes.sort((v0, v1) -> -Integer.compare(v0.getPriority(), v1.getPriority()));
    }

    public String getFileRootPath() {
        return fileRootPath;
    }

    public void setFileRootPath(String fileRootPath) {
        this.fileRootPath = fileRootPath;
        if (!this.fileRootPath.endsWith("/")) {
            this.fileRootPath = this.fileRootPath + "/";
        }
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
        for (VFSVolume volume : this.volumes) {
            String absFilename = "/" + volume.getRootPath() + fullFilename;
            InputStream resourceAsStream = VFS.class.getResourceAsStream(absFilename);
            if (resourceAsStream != null) {
                return Optional.of(resourceAsStream);
            }
        }
        return Optional.empty();
    }


    private Optional<InputStream> openFileFromFileSystem(ResourceLocator locator) {
        try {
            for (VFSVolume volume : this.volumes) {

                String absoluteFilename = this.fileRootPath + volume.rootPath + locator.getFullFilename();
                File file = new File(absoluteFilename);
                if (file.exists()) {
                    return Optional.of(Files.newInputStream(file.toPath()));
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }

        return Optional.empty();
    }


    private VFS() {
    }


    private static final VFS instance = new VFS();

    public static VFS instance() {
        return instance;
    }

    @Getter
    public static final class VFSVolume {

        private final String volumeName;

        private final String rootPath;

        private final int priority;

        public VFSVolume(String volumeName, String rootPath, int priority) {
            if (!rootPath.endsWith("/")) {
                rootPath = rootPath + "/";
            }
            this.volumeName = volumeName;
            this.rootPath = rootPath;
            this.priority = priority;
        }
    }

}
