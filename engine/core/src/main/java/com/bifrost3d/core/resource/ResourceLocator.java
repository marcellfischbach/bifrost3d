package com.bifrost3d.core.resource;

import lombok.Getter;

public final class ResourceLocator {
    public enum Protocol {
        FILE("file://"),
        ARCHIVE("archive://"),
        LOCAL(":");

        Protocol(String value) {
            this.value = value;
        }

        public final String value;
    }

    private final int hashCode;

    @Getter
    private final String encoded;

    @Getter
    private final Protocol protocol;

    @Getter
    private final String path;

    @Getter
    private final String name;

    @Getter
    private final String ext;

    @Getter
    private final boolean absolute;

    public ResourceLocator(String encoded) {
        this.encoded = encoded;
        this.protocol = extractProtocol(encoded);
        encoded = removeProtocol(encoded);
        this.path = extractPath(encoded);
        encoded = removePath(encoded);
        this.name = extractName(encoded);
        encoded = removeName(encoded);
        this.ext = extractExt(encoded);
        this.hashCode = this.encoded.hashCode();
        this.absolute = !this.path.isEmpty() && this.path.charAt(0) == '/';
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    public String getFullFilename () {
        if (this.ext.isEmpty()) {
            return this.path + this.name;
        }
        else {
            return this.path + this.name + "." + this.ext;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ResourceLocator) {
            return encoded.equals(((ResourceLocator) obj).encoded);
        }
        return false;
    }

    private Protocol extractProtocol(String encodedResource) {
        for (Protocol proto : Protocol.values()) {
            if (encodedResource.startsWith(proto.value)) {
                return proto;
            }
        }
        throw new InvalidProtocolException(encodedResource);
    }

    private String removeProtocol(String encodedResource) {
        return encodedResource.substring(this.protocol.value.length());
    }


    private String extractPath(String encodedResource) {
        int idx = encodedResource.lastIndexOf("/");
        if (idx == -1) {
            return "";
        }
        return encodedResource.substring(0, idx + 1);
    }

    private String removePath(String encodedResource) {
        return encodedResource.substring(this.path.length());
    }

    private String extractName(String encodedResource) {
        int idx = encodedResource.lastIndexOf(".");
        if (idx == -1) {
            return encodedResource;
        }
        return encodedResource.substring(0, idx);
    }

    private String removeName(String encodedResource) {
        return encodedResource.substring(this.name.length());
    }

    private String extractExt(String encodedResource) {
        int idx = encodedResource.lastIndexOf(".");
        if (idx == -1) {
            return encodedResource;
        }
        return encodedResource.substring(idx + 1);
    }

    @Override
    public String toString() {
        return encoded;
    }
}
