package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.EVertexStream;
import com.bifrost3d.core.graphics.Mesh;
import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Vector2f;
import com.bifrost3d.math.Vector3f;
import com.bifrost3d.math.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL44.*;

public class MeshGL4 extends Mesh {

    private final GraphicsGL4 graphics;

    private boolean dirty = true;

    private final int vao;

    private VertexBufferGL4 vb;

    private IndexBufferGL4 ib;

    private int indexBufferCount;
    private int indexBufferType;

    public MeshGL4(GraphicsGL4 graphics) {
        this.graphics = graphics;
        this.vao = glGenVertexArrays();
    }

    @Override
    protected void markGraphicsBuffersDirty() {
        this.dirty = true;
    }


    public void render() {
        // bind the vertices and the indices
        glBindVertexArray(this.vao);

        if (this.dirty) {
            GLError.check();
            regenerateBuffers();
            GLError.check();
        }

        glDrawElements(GL_TRIANGLES, this.indexBufferCount, this.indexBufferType, 0L);

    }


    private void regenerateBuffers() {
        GLError.check();

        generateVertexBuffer();
        GLError.check();
        generateIndexBuffer();
        GLError.check();

        bindVertexBufferChannels();
        GLError.check();

        this.dirty = false;
    }

    private void generateVertexBuffer() {
        int floatsPerVertex = countNumberOfFloatsPerVertex();
        float[] vertexBuffer = new float[this.vertices.size() * floatsPerVertex];

        int vIdx = 0;
        for (int i = 0; i < this.vertices.size(); i++) {
            vIdx = pushVector4f(vertexBuffer, vIdx, this.vertices, i);
            vIdx = pushColorRGBA(vertexBuffer, vIdx, this.colors, i);
            vIdx = pushVector3f(vertexBuffer, vIdx, this.normals, i);
            vIdx = pushVector3f(vertexBuffer, vIdx, this.tangents, i);
            vIdx = pushVector3f(vertexBuffer, vIdx, this.biNormal, i);
            vIdx = pushVector2f(vertexBuffer, vIdx, this.uv, i);
            vIdx = pushVector2f(vertexBuffer, vIdx, this.uv2, i);
        }

        if (vb != null) {
            vb.destroy();
            vb = null;
        }
        GLError.check();

        vb = graphics.createVertexBuffer();
        GLError.check();
        vb.bind();
        GLError.check();
        vb.generateBuffer(vertexBuffer.length * (long) Float.BYTES);
        GLError.check();
        vb.copy(0, vertexBuffer);
        GLError.check();

    }

    private void generateIndexBuffer() {
        if (this.vertices.size() > 32767) {
            generate32bitIndexBuffer();
        } else {
            generate16bitIndexBuffer();
        }
        this.indexBufferCount = this.indices.size();
    }


    private void generate32bitIndexBuffer() {
        int[] indexBuffer = new int[this.indices.size()];
        int iIdx = 0;
        for (Integer index : this.indices) {
            indexBuffer[iIdx++] = index;
        }

        if (ib != null) {
            ib.destroy();
            ib = null;
        }

        ib = graphics.createIndexBuffer();
        ib.bind();
        ib.generateBuffer((long) this.indices.size() * (long) Integer.BYTES);
        ib.copy(0, indexBuffer);

        indexBufferType = GL_UNSIGNED_INT;
    }

    private void generate16bitIndexBuffer() {
        short[] indexBuffer = new short[this.indices.size()];
        int iIdx = 0;
        for (Integer index : this.indices) {
            indexBuffer[iIdx++] = index.shortValue();
        }

        if (ib != null) {
            ib.destroy();
            ib = null;
        }

        ib = graphics.createIndexBuffer();
        ib.bind();
        ib.generateBuffer((long) this.indices.size() * (long) Short.BYTES);
        ib.copy(0, indexBuffer);

        indexBufferType = GL_UNSIGNED_SHORT;
    }


    private void bindVertexBufferChannels() {
        int floatsPerVertex = countNumberOfFloatsPerVertex();
        int bytesPerVertex = floatsPerVertex * Float.BYTES;

        int offset = 0;
        offset = bindAndEnableVertexAttrib(EVertexStream.POSITION, offset, 3, bytesPerVertex, this.vertices);
        offset = bindAndEnableVertexAttrib(EVertexStream.COLOR, offset, 4, bytesPerVertex, this.colors);
        offset = bindAndEnableVertexAttrib(EVertexStream.NORMAL, offset, 3, bytesPerVertex, this.normals);
        offset = bindAndEnableVertexAttrib(EVertexStream.TANGENT, offset, 3, bytesPerVertex, this.tangents);
        offset = bindAndEnableVertexAttrib(EVertexStream.BI_NORMAL, offset, 3, bytesPerVertex, this.biNormal);
        offset = bindAndEnableVertexAttrib(EVertexStream.UV, offset, 2, bytesPerVertex, this.uv);
        bindAndEnableVertexAttrib(EVertexStream.UV2, offset, 2, bytesPerVertex, this.uv2);
    }

    private int bindAndEnableVertexAttrib(EVertexStream vertexStream, int offset, int size, int strideInBytes, List<?> buffer) {
        if (buffer != null) {
            glVertexAttribPointer(
                    vertexStream.ordinal(),
                    size,
                    GL_FLOAT,
                    false,
                    strideInBytes,
                    offset
            );
            glEnableVertexAttribArray(vertexStream.ordinal());
            offset += size * Float.BYTES;
        }

        return offset;
    }

    private int pushVector4f(float[] buffer, int vIdx, List<Vector4f> vectors, int idx) {
        if (vectors != null) {
            Vector4f v = vectors.get(idx);
            buffer[vIdx++] = v.x;
            buffer[vIdx++] = v.y;
            buffer[vIdx++] = v.z;
            buffer[vIdx++] = v.w;
        }
        return vIdx;
    }

    private int pushVector3f(float[] buffer, int vIdx, List<Vector3f> vectors, int idx) {
        if (vectors != null) {
            Vector3f v = vectors.get(idx);
            buffer[vIdx++] = v.x;
            buffer[vIdx++] = v.y;
            buffer[vIdx++] = v.z;
        }
        return vIdx;
    }

    private int pushVector2f(float[] buffer, int vIdx, List<Vector2f> vectors, int idx) {
        if (vectors != null) {
            Vector2f v = vectors.get(idx);
            buffer[vIdx++] = v.x;
            buffer[vIdx++] = v.y;
        }
        return vIdx;
    }

    private int pushColorRGBA(float[] buffer, int vIdx, List<ColorRGBA> colors, int idx) {
        if (colors != null) {
            ColorRGBA v = colors.get(idx);
            buffer[vIdx++] = v.r;
            buffer[vIdx++] = v.g;
            buffer[vIdx++] = v.b;
            buffer[vIdx++] = v.a;
        }
        return vIdx;
    }


    private int countNumberOfFloatsPerVertex() {
        int numberOfFloatsPerVertex = 4;

        numberOfFloatsPerVertex += countNumberOfFloatsPerVertex(this.colors, 4);
        numberOfFloatsPerVertex += countNumberOfFloatsPerVertex(this.normals, 3);
        numberOfFloatsPerVertex += countNumberOfFloatsPerVertex(this.tangents, 3);
        numberOfFloatsPerVertex += countNumberOfFloatsPerVertex(this.biNormal, 3);
        numberOfFloatsPerVertex += countNumberOfFloatsPerVertex(this.uv, 2);
        numberOfFloatsPerVertex += countNumberOfFloatsPerVertex(this.uv2, 2);

        return numberOfFloatsPerVertex;
    }

    private int countNumberOfFloatsPerVertex(List<?> buffer, int size) {
        return buffer != null
                ? size
                : 0;
    }
}
