package com.bifrost3d.graphics.opengl.gl4.loader;

import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.EShaderAttributeType;
import com.bifrost3d.core.graphics.IGraphics;
import com.bifrost3d.core.graphics.IShader;
import com.bifrost3d.core.resource.*;
import com.bifrost3d.graphics.opengl.gl4.GraphicsGL4;
import com.bifrost3d.graphics.opengl.gl4.ProgramGL4;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Optional;

public class ProgramLoaderGL4 implements IAssetLoader<ProgramGL4> {


    private static final Logger LOGGER = LogManager.getLogger(ProgramLoaderGL4.class);

    @Override
    public boolean canLoad(Class<?> cls, ResourceLocator locator) {
        return cls.isAssignableFrom(ProgramGL4.class)
                && "prog".equals(locator.getExt());
    }

    @Override
    public Optional<ProgramGL4> load(Class<?> cls, ResourceLocator locator) {
        ProgramData data = loadProgramData(locator);
        if (data == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(loadProgram(data));
    }

    private ProgramGL4 loadProgram(ProgramData data) {
        try {
            ProgramGL4 program = createProgram();
            if (program == null) {
                return null;
            }
            AssetManager assetManager = AssetManager.instance();
            for (ShaderData shaderData : data.getShaders()) {
                ResourceLocator locator = new ResourceLocator(shaderData.getLocator());
                Optional<IShader> optShader = assetManager.load(IShader.class, locator);
                optShader.ifPresent(program::attach);
            }
            program.link();

            for (AttributeData attributeData : data.getAttributes()) {
                program.registerAttribute(attributeData.getAttribute(), attributeData.getType());
            }
            return program;
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    private ProgramGL4 createProgram() {
        GraphicsGL4 graphicsGL4 = ObjectRegistry.get(IGraphics.class, GraphicsGL4.class).orElse(null);
        if (graphicsGL4 == null) {
            return null;
        }
        return graphicsGL4.createProgram();
    }

    private ProgramData loadProgramData(ResourceLocator locator) {
        Optional<InputStream> optInputStream = VFS.instance().open(locator);

        if (optInputStream.isPresent()) {
            return loadProgramData(optInputStream.get());
        }
        return null;
    }

    private ProgramData loadProgramData(InputStream inputStream) {
        try {
            String source = ReadAllFromInputStream.read(inputStream);
            ObjectMapper om = new ObjectMapper();
            return om.readValue(source, ProgramData.class);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
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

    @Getter
    @Setter
    private static final class AttributeData {
        private String attribute;
        private EShaderAttributeType type;
    }
}
