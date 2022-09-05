package com.bifrost3d.graphics.opengl.gl4.loader;

import com.bifrost3d.core.resource.IAssetLoader;
import com.bifrost3d.core.resource.ResourceLocator;
import com.bifrost3d.graphics.opengl.gl4.ProgramGL4;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class ProgramLoaderGL4 implements IAssetLoader<ProgramGL4> {

    @Override
    public boolean canLoad(Class<?> cls, ResourceLocator locator) {
        return cls.isAssignableFrom(ProgramGL4.class)
                && "prog".equals(locator.getExt());
    }

    @Override
    public Optional<ProgramGL4> load(Class<?> cls, ResourceLocator locator) {

        ObjectMapper om = new ObjectMapper();


        return Optional.empty();
    }


    @Getter
    @Setter
    public static final class ProgramData {

        private ShaderData[] shaders;

        private AttributeData[] attributes;


    }

    @Getter
    @Setter
    private static final class ShaderData {
        private String locator;
    }

    private static final class AttributeData {
        private String name;
    }
}
