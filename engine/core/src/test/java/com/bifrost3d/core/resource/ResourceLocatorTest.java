package com.bifrost3d.core.resource;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ResourceLocatorTest {


    @Test
    void testProtocolEquals() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file.txt");
        ResourceLocator locator1 = new ResourceLocator(locator0.getEncoded());

        assertEquals(locator0, locator1);
    }

    @Test
    void testProtocolIdentity() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file.txt");

        assertEquals(locator0, locator0);
    }

    @Test
    void testProtocolDifferentType() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file.txt");

        assertNotEquals(locator0, "This is a string");
    }

    @Test
    void testProtocolHashEquals() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file.txt");
        ResourceLocator locator1 = new ResourceLocator(locator0.getEncoded());

        assertEquals(locator0.hashCode(), locator1.hashCode());
    }

    @Test
    void testProtocolNotEquals() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file1.txt");
        ResourceLocator locator1 = new ResourceLocator("file:///path/to/file2.txt");

        assertNotEquals(locator0, locator1);
    }

    @Test
    void testProtocolNotHashEquals() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file1.txt");
        ResourceLocator locator1 = new ResourceLocator("file:///path/to/file2.txt");

        assertNotEquals(locator0.hashCode(), locator1.hashCode());
    }


    @Test
    void testInHashSet() {
        ResourceLocator locator0 = new ResourceLocator("file:///path/to/file.txt");
        ResourceLocator locator1 = new ResourceLocator(locator0.getEncoded());

        Set<ResourceLocator> hash = new HashSet<>();
        hash.add(locator0);
        hash.add(locator1);

        assertEquals(1, hash.size());
    }


    @Test
    void testProtocolFile() {
        ResourceLocator locator = new ResourceLocator("file://some_path");
        assertEquals(ResourceLocator.Protocol.File, locator.getProtocol());
    }

    @Test
    void testProtocolArchive() {
        ResourceLocator locator = new ResourceLocator("archive://some_path");
        assertEquals(ResourceLocator.Protocol.Archive, locator.getProtocol());
    }

    @Test
    void testProtocolLocal() {
        ResourceLocator locator = new ResourceLocator(":some_path");
        assertEquals(ResourceLocator.Protocol.Local, locator.getProtocol());
    }
    @Test
    void testNoProtocol() {
        InvalidProtocolException exception = assertThrows(InvalidProtocolException.class, () -> new ResourceLocator("/some_path"));

        assertEquals("/some_path", exception.getResource());
    }

    @Test
    void testInvalidProtocol() {
        InvalidProtocolException exception = assertThrows(InvalidProtocolException.class, () -> new ResourceLocator("invalid://some_path"));
        assertEquals("invalid://some_path", exception.getResource());
    }


    @Test
    void testPath() {
        ResourceLocator locator = new ResourceLocator("file:///some/path/file.ext");

        assertEquals("/some/path/", locator.getPath());
    }

    @Test
    void testPathWithArchive() {
        ResourceLocator locator = new ResourceLocator("file:///some/path/file.ext");

        assertEquals("/some/path/", locator.getPath());
    }


    @Test
    void testNoPath() {
        ResourceLocator locator = new ResourceLocator("file://file.ext");

        assertEquals("", locator.getPath());
    }

    @Test
    void testNoPathWithArchive() {
        ResourceLocator locator = new ResourceLocator("file://file.ext");

        assertEquals("", locator.getPath());
    }

    @Test
    void testNoPathAbs() {
        ResourceLocator locator = new ResourceLocator("file:///file.ext");

        assertEquals("/", locator.getPath());
    }

    @Test
    void testNoPathAbsWithArchive() {
        ResourceLocator locator = new ResourceLocator("file:///file.ext");

        assertEquals("/", locator.getPath());
    }

    @Test
    void testName() {
        ResourceLocator locator = new ResourceLocator("file://relative/path/file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }

    @Test
    void testNameAbs() {
        ResourceLocator locator = new ResourceLocator("file:///absolute/path/file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }

    @Test
    void testNameNoPath() {
        ResourceLocator locator = new ResourceLocator("file://file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }

    @Test
    void testNameAbsNoPath() {
        ResourceLocator locator = new ResourceLocator("file:///file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }


    @Test
    void testNameNoExtension() {
        ResourceLocator locator = new ResourceLocator("file://file");

        assertEquals("file", locator.getName());

    }


    @Test
    void testExtension() {
        ResourceLocator locator = new ResourceLocator("file://file.ext");

        assertEquals("ext", locator.getExt());

    }

    @Test
    void testNoExtension() {
        ResourceLocator locator = new ResourceLocator("file://file");

        assertEquals("", locator.getExt());
    }

    @Test
    void testEncoded() {
        ResourceLocator locator = new ResourceLocator("file:///absolute/path/file.ext.xml");

        assertEquals("file:///absolute/path/file.ext.xml", locator.getEncoded());

    }

    @Test
    void testAbsolute() {
        ResourceLocator locator = new ResourceLocator("file:///absolute/path/file.ext.xml");

        assertTrue(locator.isAbsolute());

    }

    @Test
    void testNotAbsolute() {
        ResourceLocator locator = new ResourceLocator("file://absolute/path/file.ext.xml");

        assertFalse(locator.isAbsolute());

    }

}
