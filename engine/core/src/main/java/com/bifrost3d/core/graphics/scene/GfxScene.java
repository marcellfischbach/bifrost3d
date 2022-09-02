package com.bifrost3d.core.graphics.scene;

import com.bifrost3d.core.graphics.Camera;
import com.bifrost3d.core.graphics.ERenderPass;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.graphics.Projector;

import java.util.ArrayList;
import java.util.List;

public final class GfxScene {

    private final IGraphics graphics;

    private final List<GfxMesh> meshes = new ArrayList<>();

    public GfxScene(IGraphics graphics) {
        this.graphics = graphics;
    }

    public void add(GfxMesh mesh) {
        this.meshes.add(mesh);
    }

    public void remove(GfxMesh mesh) {
        this.meshes.remove(mesh);
    }


    public void render (ERenderPass pass, Camera camera, Projector projector) {

        graphics.setProjectionMatrix(projector.getMatrix());
        graphics.setViewMatrix(camera.getMatrix());

        for (GfxMesh mesh : this.meshes) {
            render(pass, mesh);
        }

    }

    private void render (ERenderPass pass, GfxMesh mesh) {
        mesh.render(this.graphics, pass);
    }


}
