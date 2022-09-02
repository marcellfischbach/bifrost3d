package com.bifrost3d.core.graphics.scene;

import com.bifrost3d.core.graphics.ERenderPass;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.graphics.Mesh;
import com.bifrost3d.core.graphics.material.IMaterial;
import com.bifrost3d.math.Matrix4f;

public final class GfxMesh {

    private final Mesh mesh;

    private final IMaterial material;

    private final Matrix4f matrix = new Matrix4f();


    public GfxMesh(Mesh mesh, IMaterial material) {
        this.mesh = mesh;
        this.material = material;
    }

    public void setMatrix(Matrix4f matrix) {
        this.matrix.set(matrix);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public IMaterial getMaterial() {
        return material;
    }

    public Matrix4f getMatrix() {
        return matrix;
    }


    public void render(IGraphics graphics, ERenderPass pass) {
        graphics.setModelMatrix(this.matrix);
        material.bind(graphics, pass);
        graphics.renderMesh(mesh);
    }
}
