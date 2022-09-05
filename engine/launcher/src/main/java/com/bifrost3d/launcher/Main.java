package com.bifrost3d.launcher;

import com.bifrost3d.core.Engine;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.*;
import com.bifrost3d.core.graphics.material.Material;
import com.bifrost3d.core.graphics.scene.GfxMesh;
import com.bifrost3d.core.graphics.scene.GfxScene;
import com.bifrost3d.core.resource.AssetManager;
import com.bifrost3d.core.resource.ResourceLocator;
import com.bifrost3d.core.window.*;
import com.bifrost3d.math.*;

import java.util.List;
import java.util.Optional;


public class Main {

    private static Mesh generateMesh(IGraphics graphics, float textureStretch) {

        Mesh mesh = graphics.createMesh();

        List<Vector4f> vertices = mesh.getVertices();
        vertices.add(new Vector4f(-5f, -5f, 0.0f, 1.0f));
        vertices.add(new Vector4f(-5f, 5f, 0.0f, 1.0f));
        vertices.add(new Vector4f(5f, -5f, 0.0f, 1.0f));
        vertices.add(new Vector4f(5f, 5f, 0.0f, 1.0f));
        mesh.setVertices(vertices);

        List<Vector2f> uvs = mesh.getUv();
        uvs.add(new Vector2f(0.0f, 0.0f));
        uvs.add(new Vector2f(0.0f, textureStretch));
        uvs.add(new Vector2f(textureStretch, 0.0f));
        uvs.add(new Vector2f(textureStretch, textureStretch));
        mesh.setUv(uvs);


        List<Integer> indices = mesh.getIndices();
        indices.add(0);
        indices.add(1);
        indices.add(3);
        indices.add(0);
        indices.add(3);
        indices.add(2);

        indices.add(0);
        indices.add(3);
        indices.add(1);
        indices.add(0);
        indices.add(2);
        indices.add(3);

        mesh.setIndices(indices);


        return mesh;
    }

    private static IProgram createProgram(IGraphics graphics) {
        IProgram program = graphics.createProgram();
        program.attach(createVertexShader(graphics));
        program.attach(createFragmentShader(graphics));
        program.link();

        program.registerAttribute("Diffuse", EShaderAttributeFormat.TEXTURE);
        program.registerAttribute("DiffuseColor", EShaderAttributeFormat.COL4);
        return program;
    }


    private static IShader createVertexShader(IGraphics graphics) {
        String source = "" +
                "#version 330\n" +
                "" +
                "layout(location = 0) in vec4 bf_Position;" +
                "layout(location = 5) in vec2 bf_UV;" +
                "" +
                "uniform mat4 bf_ModelMatrix;" +
                "uniform mat4 bf_ProjectionViewMatrix;" +
                "" +
                "out vec4 color;" +
                "out vec2 uv;" +
                "" +
                "void main ()" +
                "{" +
                "   gl_Position = bf_ProjectionViewMatrix * bf_ModelMatrix * bf_Position;" +
                "   color = vec4(1.0, 1.0, 1.0, 1.0);" +
                "   uv = bf_UV;" +
                "}";
        IShader shader = graphics.createShader(EShaderType.VERTEX);
        shader.setSource(source);
        shader.compile();
        return shader;
    }

    private static IShader createFragmentShader(IGraphics graphics) {
        Optional<IShader> optShader = AssetManager.instance().load(IShader.class, new ResourceLocator("archive:///shaders/test.frag"));
        return optShader.orElse(null);
        /*
        String source = "" +
                "#version 330\n" +
                "" +
                "layout(location = 0) out vec4 bf_FragColor;" +
                "" +
                "uniform vec4 bf_DiffuseColor;" +
                "uniform sampler2D bf_Diffuse;" +
                "" +
                "in vec4 color;" +
                "in vec2 uv;" +
                "" +
                "void main ()" +
                "{" +
                "   vec4 tex = texture(bf_Diffuse, uv);" +
                "   bf_FragColor = tex * color * bf_DiffuseColor;" +
                "}";

        IShader shader = graphics.createShader(EShaderType.FRAGMENT);
        shader.setSource(source);
        shader.compile();
        return shader;

         */
    }

    private static Image createCheckerBoardImage(int size, int checkSize, int dither) {

        byte[] buffer = new byte[size * size * 3];
        int idx = 0;
        for (int y = 0; y < size; y++) {
            boolean oddY = ((y / checkSize) % 2) == 1;

            for (int x = 0; x < size; x++) {
                boolean oddX = ((x / checkSize) % 2) == 1;

                boolean check = oddX == oddY;
                byte value = check ? (byte) 0xef : (byte) 0x20;
                byte rnd = (byte) (Math.random() * dither);
                if (check) {
                    value -= rnd;
                }
                else {
                    value += rnd;
                }

                buffer[idx++] = value;
                buffer[idx++] = value;
                buffer[idx++] = value;
            }
        }

        Image image = new Image(EPixelFormat.R8G8B8, size, size);
        image.setImageData(buffer);
        image.generateMipMaps(EMipMapGenerationMode.MIP_2X2);
        return image;
    }

    public static void main(String[] args) {
        Engine engine = Engine.create();
        engine.initializeModules();

        IKeyboard keyboard = ObjectRegistry.get(IKeyboard.class).orElseThrow(NullPointerException::new);
        IMouse mouse = ObjectRegistry.get(IMouse.class).orElseThrow(NullPointerException::new);
        IGraphics graphics = ObjectRegistry.get(IGraphics.class).orElseThrow(NullPointerException::new);
        IWindow window = ObjectRegistry.get(IWindow.class).orElseThrow(NullPointerException::new);


        Mesh mesh = generateMesh(graphics, 8);
        IProgram program = createProgram(graphics);


        ISampler sampler = graphics.createSampler();
        sampler.setFilter(ETextureFilter.ANISOTROPIC);
        sampler.setAnisotropy(16);


        int checkSize = 512;
        Image image = createCheckerBoardImage(checkSize, checkSize / 2, 128);

        ITexture2D texture2d = graphics.createTexture2D(image);
        texture2d.setSampler(sampler);

        Material material = new Material();
        material.setProgram(ERenderPass.FORWARD, program);

        int diffuseColorIdx = material.indexOf("DiffuseColor");
        int diffuseIdx = material.indexOf("Diffuse");
        material.setAttributeColor(diffuseColorIdx, new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
        material.setAttributeTexture(diffuseIdx, texture2d);

        GfxMesh gfxMesh = new GfxMesh(mesh, material);
        gfxMesh.setMatrix(Matrix4f.translation(0.0f, 0.0f, 0.0f));


        Matrix4f mat = new Matrix4f();
        Matrix4f matT = new Matrix4f();
        Matrix4f matY = new Matrix4f();


        float rotValue = 0.0f;

        Camera camera = new Camera();
        camera.setSpot(new Vector3f(0.0f, 0.0f, 0.0f));
        camera.setUp(new Vector3f(0.0f, 1.0f, 0.0f));

        Projector projector = new Projector();

        GfxScene scene = new GfxScene(graphics);
        scene.add(gfxMesh);


        FPSCounter fpsCounter = new FPSCounter();
        int fps = 0;
        boolean running = true;
        boolean animate = true;
        float rot = 0.0f;
        graphics.setClearColor(new ColorRGBA(0.0f, 0.0f, 0.5f, 1.0f));
        Vector3f tmp = new Vector3f();
        while (running) {
            fpsCounter.tick();
            if (fps != fpsCounter.getFps()) {
                fps = fpsCounter.getFps();
                window.setTitle("Bifrost 3D " + fps + " FPS");
            }

            window.handleEvents();

            graphics.clear(true, true, true);


            camera.setEye(tmp.set(Mathf.sin(rot) * 10.0f, 1.5f, Mathf.cos(rot) * 10.0f));
            camera.setSpot(tmp.set(Mathf.sin(-rot), 1.0f, Mathf.cos(-rot)));
            projector.setDimension(window.getWidth(), window.getHeight());


            scene.render(ERenderPass.FORWARD, camera, projector);

            window.swap();


            if (mouse.isDown(EMouseButton.MB_RIGHT)) {
                mouse.setCursorMode(ECursorMode.LOCKED);
            } else {
                mouse.setCursorMode(ECursorMode.NORMAL);
            }
            if (keyboard.isPressed(EKey.K_ESCAPE)) {
                running = false;
            }
            if (keyboard.isPressed(EKey.K_SPACE)) {
                animate = !animate;
            }


            if (animate) {
                rotValue += 0.0125f;
                rot += 0.0125f;
            }
        }
    }



    private static class FPSCounter {
        private long nextTime;

        private int counter = 0;
        private int fps = 0;

        public FPSCounter() {
            nextTime = System.currentTimeMillis() + 1000;
        }

        public void tick() {
            long time = System.currentTimeMillis();
            if (time > nextTime) {
                fps = counter;
                nextTime += 1000;
                counter = 0;
            }
            counter++;
        }

        public int getFps() {
            return fps;
        }
    }

}
