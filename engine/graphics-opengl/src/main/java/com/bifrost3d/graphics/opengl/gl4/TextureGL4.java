package com.bifrost3d.graphics.opengl.gl4;

import com.bifrost3d.core.graphics.ISampler;
import com.bifrost3d.core.graphics.ITexture;
import static org.lwjgl.opengl.GL44.*;

public abstract class TextureGL4 implements ITexture {

    protected final GraphicsGL4 graphics;

    protected final int name;

    protected final int target;

    protected SamplerGL4 sampler;

    protected TextureGL4(GraphicsGL4 graphics, int target) {
        this.graphics = graphics;
        this.name = glGenTextures();
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public SamplerGL4 getSampler() {
        return sampler;
    }

    public void setSampler(ISampler sampler) {
        this.sampler = (SamplerGL4) sampler;
    }

    public void bind () {
        glBindTexture(this.target, this.name);
    }

    public void unbind () {
        glBindTexture(this.target, 0);
    }


    protected static int numPowerOfTwos (int size) {
        int num = 0;
        while (size > 0) {
            size >>= 1;
            num++;
        }
        return num;
    }

}
