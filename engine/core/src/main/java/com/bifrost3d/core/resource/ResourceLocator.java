package com.bifrost3d.core.resource;

public final class ResourceLocator {
    public enum Protocol {
        File("file://"),
        Archive("archive://"),
        ;

        Protocol(String value) {
            this.value = value;
        }

        public final String value;
    }

    private final String encoded;

    private final Protocol protocol;

    private final String archive;

    private final String path;

    private final String name;

    private final String ext;


    public ResourceLocator(String encoded) {
        this.encoded = encoded;
        this.protocol = extractProtocol(encoded);
        encoded = removeProtocol (encoded);
        this.archive = extractArchive(encoded);
        encoded = removeArchive(encoded);
        this.path = extractPath(encoded);
        encoded = removePath(encoded);
        this.name = extractName(encoded);
        encoded = removeName (encoded);
        this.ext = extractExt(encoded);
    }

    public String getEncoded() {
        return encoded;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getArchive() {
        return archive;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getExt() {
        return ext;
    }



    private Protocol extractProtocol(String encodedResource) {
        int idx = encodedResource.indexOf("://");
        if (idx != -1) {
            String protoString = encodedResource.substring(0, idx + 3);

            for (Protocol proto : Protocol.values()) {
                if (proto.value.equals(protoString)) {
                    return proto;
                }
            }
            throw new InvalidProtocolException(protoString);
        }
        throw new InvalidProtocolException("");
    }

    private String removeProtocol (String encodedResource) {
        return encodedResource.substring(this.protocol.value.length());
    }



    private String extractArchive(String encodedResource) {
        int archiveMarkerIdx = encodedResource.indexOf("!");
        if (archiveMarkerIdx == -1) {
            return "";
        }

        return encodedResource.substring(0, archiveMarkerIdx);
    }

    private String removeArchive (String encodedResource) {
        if (this.archive.isEmpty()) {
            return encodedResource;
        }
        return encodedResource.substring(this.archive.length()+1);
    }

    private String extractPath(String encodedResource) {
        int idx = encodedResource.lastIndexOf("/");
        if (idx == -1) {
            return "";
        }
        return encodedResource.substring(0, idx+1);
    }

    private String removePath (String encodedResource) {
        return encodedResource.substring(this.path.length());
    }

    private String extractName (String encodedResource) {
        int idx = encodedResource.lastIndexOf(".");
        if (idx == -1) {
            return encodedResource;
        }
        return encodedResource.substring(0, idx);
    }

    private String removeName(String encodedResource) {
        return encodedResource.substring(this.name.length());
    }

    private String extractExt (String encodedResource) {
        int idx = encodedResource.lastIndexOf(".");
        if (idx == -1) {
            return encodedResource;
        }
        return encodedResource.substring(idx+1);
    }



}
