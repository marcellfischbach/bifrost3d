package com.bifrost3d.core.resource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceLocatorTest {

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
    void testNoProtocol () {
        InvalidProtocolException exception = assertThrows(InvalidProtocolException.class, () -> new ResourceLocator("/some_path"));

        assertEquals("", exception.getProtocol());
    }

    @Test
    void testInvalidProtocol () {
        InvalidProtocolException exception = assertThrows(InvalidProtocolException.class, () -> new ResourceLocator("invalid://some_path"));
        assertEquals("invalid://", exception.getProtocol());
    }

    @Test
    void testArchive () {
        ResourceLocator locator = new ResourceLocator("archive://SomeArchive!some_path");

        assertEquals("SomeArchive", locator.getArchive());
    }

    @Test
    void testNoArchive () {
        ResourceLocator locator = new ResourceLocator("archive://some_path");

        assertEquals("", locator.getArchive());
    }



    @Test
    void testPath () {
        ResourceLocator locator = new ResourceLocator("file://archive!/some/path/file.ext");

        assertEquals("/some/path/", locator.getPath());
    }

    @Test
    void testPathWithArchive () {
        ResourceLocator locator = new ResourceLocator("file://archive!/some/path/file.ext");

        assertEquals("/some/path/", locator.getPath());
    }



    @Test
    void testNoPath () {
        ResourceLocator locator = new ResourceLocator("file://archive!file.ext");

        assertEquals("", locator.getPath());
    }

    @Test
    void testNoPathWithArchive () {
        ResourceLocator locator = new ResourceLocator("file://file.ext");

        assertEquals("", locator.getPath());
    }

    @Test
    void testNoPathAbs () {
        ResourceLocator locator = new ResourceLocator("file://archive!/file.ext");

        assertEquals("/", locator.getPath());
    }

    @Test
    void testNoPathAbsWithArchive () {
        ResourceLocator locator = new ResourceLocator("file:///file.ext");

        assertEquals("/", locator.getPath());
    }

    @Test
    void testName () {
        ResourceLocator locator = new ResourceLocator("file://archive!relative/path/file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }

    @Test
    void testNameAbs () {
        ResourceLocator locator = new ResourceLocator("file://archive!//absolute/path/file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }

    @Test
    void testNameNoPath () {
        ResourceLocator locator = new ResourceLocator("file://archive!file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }

    @Test
    void testNameAbsNoPath () {
        ResourceLocator locator = new ResourceLocator("file://archive!/file.ext.xml");

        assertEquals("file.ext", locator.getName());

    }


    @Test
    void testNameNoExtension () {
        ResourceLocator locator = new ResourceLocator("file://archive!file");

        assertEquals("file", locator.getName());

    }


    @Test
    void testExtension () {
        ResourceLocator locator = new ResourceLocator("file://archive!file.ext");

        assertEquals("ext", locator.getExt());

    }

    @Test
    void testNoExtension () {
        ResourceLocator locator = new ResourceLocator("file://archive!file");

        assertEquals("", locator.getExt());
    }

    @Test
    void testEncoded () {
        ResourceLocator locator = new ResourceLocator("file://archive!//absolute/path/file.ext.xml");

        assertEquals("file://archive!//absolute/path/file.ext.xml", locator.getEncoded());

    }

}
