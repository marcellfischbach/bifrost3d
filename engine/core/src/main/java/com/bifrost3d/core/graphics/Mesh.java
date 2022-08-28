package com.bifrost3d.core.graphics;

import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Vector2f;
import com.bifrost3d.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class Mesh {


    private List<Vector3f> vertices = null;
    private List<Vector3f> normals = null;
    private List<ColorRGBA> colors = null;
    private List<Vector3f> tangents = null;
    private List<Vector3f> biNormal = null;
    private List<Vector2f> uv = null;
    private List<Vector2f> uv2 = null;

    protected Mesh() {

    }

    protected abstract void markGraphicsBuffersDirty();




    public List<Vector3f> getVertices() {
        return wrap(vertices);
    }


    public void setVertices(List<Vector3f> vertices) {

        this.vertices = vertices;

        validateNoVertices();

        markGraphicsBuffersDirty();

    }

    public List<ColorRGBA> getColors() {
        return wrap(colors);
    }

    public void setColors(List<ColorRGBA> colors) {
        validateNoVertices();
        validateColorsSizeMatchingVertices(colors);

        this.colors = colors;

        markGraphicsBuffersDirty();

    }

    public List<Vector3f> getNormals() {
        return wrap(normals);
    }

    public void setNormals(List<Vector3f> normals) {
        validateNoVertices();
        validateNormalsSizeMatchingVertices(normals);

        this.normals = normals;

        markGraphicsBuffersDirty();
    }

    public List<Vector3f> getTangents() {
        return wrap(tangents);
    }

    public void setTangents(List<Vector3f> tangents) {
        validateNoVertices();
        validTangentsSizeMatchingVertices(tangents);


        this.tangents = tangents;

        markGraphicsBuffersDirty();
    }



    public List<Vector3f> getBiNormal() {
        return wrap(biNormal);
    }

    public void setBiNormal(List<Vector3f> biNormal) {
        validateNoVertices();
        validBiNormalsSizeMatchingVertices(biNormal);


        this.biNormal = biNormal;

        markGraphicsBuffersDirty();
    }


    public List<Vector2f> getUv() {
        return wrap(uv);
    }

    public void setUv(List<Vector2f> uv) {
        validateNoVertices();
        validUVSizeMatchingVertices(uv);


        this.uv = uv;

        markGraphicsBuffersDirty();
    }

    public List<Vector2f> getUv2() {
        return wrap(uv2);
    }

    public void setUv2(List<Vector2f> uv2) {
        validateNoVertices();
        validUVSizeMatchingVertices(uv2);


        this.uv2 = uv2;
        markGraphicsBuffersDirty();
    }

    private <T> List<T> wrap(List<T> values) {
        return values != null ? values : new ArrayList<>();
    }

    private void validateNoVertices () {
        if (this.vertices == null) {
            throw new InvalidMeshConfigurationException(InvalidMeshConfigurationException.Reason.NoVertices);
        }
    }

    private void validateColorsSizeMatchingVertices(List<ColorRGBA> colors) {
        if (colors != null && colors.size() != this.vertices.size()) {
            throw new InvalidMeshConfigurationException(InvalidMeshConfigurationException.Reason.ColorsNotMatchingVertices);
        }
    }
    private void validateNormalsSizeMatchingVertices(List<Vector3f> normals) {
        if (normals != null && normals.size() != this.vertices.size()) {
            throw new InvalidMeshConfigurationException(InvalidMeshConfigurationException.Reason.NormalsNotMatchingVertices);
        }
    }

    private void validTangentsSizeMatchingVertices(List<Vector3f> tangents) {
        if (tangents != null && tangents.size() != this.vertices.size()) {
            throw new InvalidMeshConfigurationException(InvalidMeshConfigurationException.Reason.TangentsNotMatchingVertices);
        }
    }

    private void validBiNormalsSizeMatchingVertices(List<Vector3f> biNormal) {
        if (biNormal != null && biNormal.size() != this.vertices.size()) {
            throw new InvalidMeshConfigurationException(InvalidMeshConfigurationException.Reason.BiNormalsNotMatchingVertices);
        }
    }

    private void validUVSizeMatchingVertices(List<Vector2f> uv) {
        if (uv != null && uv.size() != this.vertices.size()) {
            throw new InvalidMeshConfigurationException(InvalidMeshConfigurationException.Reason.UvsNotMatchingVertices);
        }
    }

}
