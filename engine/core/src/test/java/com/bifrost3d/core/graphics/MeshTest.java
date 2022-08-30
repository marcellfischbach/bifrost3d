package com.bifrost3d.core.graphics;

import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Vector2f;
import com.bifrost3d.math.Vector3f;
import com.bifrost3d.math.Vector4f;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("java:S5778")
class MeshTest {

    static class MeshMock extends Mesh {

        private boolean dirty = false;

        @Override
        protected void markGraphicsBuffersDirty() {
            this.dirty = true;
        }

        public boolean isDirty() {
            return dirty;
        }
    }

    @Test
    void testEmptyMeshReturnsData () {
        Mesh mesh = new MeshMock();

        List<Vector4f> vertices = mesh.getVertices();
        assertNotNull(vertices);
        assertEquals(0, vertices.size());

        List<ColorRGBA> colors = mesh.getColors();
        assertNotNull(colors);
        assertEquals(0, colors.size());

        List<Vector3f> normals = mesh.getNormals();
        assertNotNull(normals);
        assertEquals(0, normals.size());

        List<Vector3f> tangents = mesh.getTangents();
        assertNotNull(tangents);
        assertEquals(0, tangents.size());

        List<Vector3f> biNormals = mesh.getBiNormal();
        assertNotNull(biNormals);
        assertEquals(0, biNormals.size());

        List<Vector2f> uvs = mesh.getUv();
        assertNotNull(uvs);
        assertEquals(0, uvs.size());

        List<Vector2f> uv2s = mesh.getUv2();
        assertNotNull(uv2s);
        assertEquals(0, uv2s.size());

        List<Integer> indices = mesh.getIndices();
        assertNotNull(indices);
        assertEquals(0, indices.size());

    }


    @Test
    void testNonEmptyMeshReturnsNonEmptyData () {
        Mesh mesh = new MeshMock();

        List<Vector4f> vertices = mesh.getVertices();
        assertEquals(0, vertices.size());
        vertices.add(new Vector4f());
        vertices.add(new Vector4f());
        mesh.setVertices(vertices);
        assertEquals(2, mesh.getVertices().size());

        List<ColorRGBA> colors = mesh.getColors();
        assertEquals(0, colors.size());
        colors.add(new ColorRGBA());
        colors.add(new ColorRGBA());
        mesh.setColors(colors);
        assertEquals(2, mesh.getColors().size());

        List<Vector3f> normals = mesh.getNormals();
        assertEquals(0, normals.size());
        normals.add(new Vector3f());
        normals.add(new Vector3f());
        mesh.setNormals(normals);
        assertEquals(2, mesh.getNormals().size());


        List<Vector3f> tangent = mesh.getTangents();
        assertEquals(0, tangent.size());
        tangent.add(new Vector3f());
        tangent.add(new Vector3f());
        mesh.setTangents(tangent);
        assertEquals(2, mesh.getTangents().size());


        List<Vector3f> biNormals = mesh.getBiNormal();
        assertEquals(0, biNormals.size());
        biNormals.add(new Vector3f());
        biNormals.add(new Vector3f());
        mesh.setBiNormal(biNormals);
        assertEquals(2, mesh.getBiNormal().size());


        List<Vector2f> uvs = mesh.getUv();
        assertEquals(0, uvs.size());
        uvs.add(new Vector2f());
        uvs.add(new Vector2f());
        mesh.setUv(uvs);
        assertEquals(2, mesh.getUv().size());


        List<Vector2f> uv2s = mesh.getUv2();
        assertEquals(0, uv2s.size());
        uv2s.add(new Vector2f());
        uv2s.add(new Vector2f());
        mesh.setUv2(uv2s);
        assertEquals(2, mesh.getUv2().size());


        List<Integer> indices = mesh.getIndices();
        assertEquals(0, indices.size());
        indices.add(0);
        indices.add(1);
        mesh.setIndices(indices);
        assertEquals(2, mesh.getIndices().size());

    }



    @Test
    void noVertices() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<Vector3f> normals = new ArrayList<>();
            normals.add(new Vector3f());
            normals.add(new Vector3f());


            Mesh mesh = new MeshMock();
            mesh.setNormals(normals);
            mesh.setVertices(vertices);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.NO_VERTICES, exception.getReason());
    }

    @Test
    void invalidColors() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<ColorRGBA> colors = new ArrayList<>();
            colors.add(new ColorRGBA());
            colors.add(new ColorRGBA());


            Mesh mesh = new MeshMock();
            mesh.setVertices(vertices);
            mesh.setColors(colors);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.COLORS_NOT_MATCHING_VERTICES, exception.getReason());
    }


    @Test
    void invalidNormals() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<Vector3f> normals = new ArrayList<>();
            normals.add(new Vector3f());
            normals.add(new Vector3f());


            Mesh mesh = new MeshMock();
            mesh.setVertices(vertices);
            mesh.setNormals(normals);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.NORMALS_NOT_MATCHING_VERTICES, exception.getReason());
    }

    @Test
    void invalidTangents() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<Vector3f> tangents = new ArrayList<>();
            tangents.add(new Vector3f());
            tangents.add(new Vector3f());


            Mesh mesh = new MeshMock();
            mesh.setVertices(vertices);
            mesh.setTangents(tangents);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.TANGENTS_NOT_MATCHING_VERTICES, exception.getReason());
    }


    @Test
    void invalidBiNormals() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<Vector3f> biNormals = new ArrayList<>();
            biNormals.add(new Vector3f());
            biNormals.add(new Vector3f());


            Mesh mesh = new MeshMock();
            mesh.setVertices(vertices);
            mesh.setBiNormal(biNormals);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.BI_NORMALS_NOT_MATCHING_VERTICES, exception.getReason());
    }


    @Test
    void invalidUVs() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<Vector2f> uvs = new ArrayList<>();
            uvs.add(new Vector2f());
            uvs.add(new Vector2f());


            Mesh mesh = new MeshMock();
            mesh.setVertices(vertices);
            mesh.setUv(uvs);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.UVS_NOT_MATCHING_VERTICES, exception.getReason());
    }


    @Test
    void invalidUV2s() {

        InvalidMeshConfigurationException exception = assertThrows(InvalidMeshConfigurationException.class, () -> {
            List<Vector4f> vertices = new ArrayList<>();
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());
            vertices.add(new Vector4f());

            List<Vector2f> uvs = new ArrayList<>();
            uvs.add(new Vector2f());
            uvs.add(new Vector2f());


            Mesh mesh = new MeshMock();
            mesh.setVertices(vertices);
            mesh.setUv2(uvs);
        });
        assertNotNull(exception);
        assertEquals(InvalidMeshConfigurationException.Reason.UVS_NOT_MATCHING_VERTICES, exception.getReason());
    }

    @Test
    void testMeshSetupAllData() {
        List<Vector4f> vertices = new ArrayList<>();
        vertices.add(new Vector4f());

        List<ColorRGBA> colors = new ArrayList<>();
        colors.add(new ColorRGBA());

        List<Vector3f> normals = new ArrayList<>();
        normals.add(new Vector3f());

        List<Vector3f> tangents = new ArrayList<>();
        tangents.add(new Vector3f());

        List<Vector3f> biNormals = new ArrayList<>();
        biNormals.add(new Vector3f());

        List<Vector2f> uvs = new ArrayList<>();
        uvs.add(new Vector2f());

        List<Vector2f> uv2s = new ArrayList<>();
        uv2s.add(new Vector2f());


        MeshMock mesh = new MeshMock();
        mesh.setVertices(vertices);
        mesh.setColors(colors);
        mesh.setNormals(normals);
        mesh.setTangents(tangents);
        mesh.setBiNormal(biNormals);
        mesh.setUv(uvs);
        mesh.setUv2(uvs);
        assertTrue(mesh.isDirty());

    }

}
