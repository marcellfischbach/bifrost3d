package com.bifrost3d.launcher;

import com.bifrost3d.core.Engine;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.*;
import com.bifrost3d.core.graphics.material.Material;
import com.bifrost3d.core.graphics.material.MaterialInstance;
import com.bifrost3d.core.window.*;
import com.bifrost3d.math.ColorRGBA;
import com.bifrost3d.math.Matrix4f;
import com.bifrost3d.math.Vector4f;

import java.util.List;


public class Main {

    private static Mesh generateMesh(IGraphics graphics) {

        Mesh mesh = graphics.createMesh();

        List<Vector4f> vertices = mesh.getVertices();
        vertices.add(new Vector4f(-0.5f, -0.5f, 0.0f, 1.0f));
        vertices.add(new Vector4f(-0.5f, 0.5f, 0.0f, 1.0f));
        vertices.add(new Vector4f(0.5f, -0.5f, 0.0f, 1.0f));
        vertices.add(new Vector4f(0.5f, 0.5f, 0.0f, 1.0f));
        mesh.setVertices(vertices);


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

        program.registerAttribute("DiffuseColor", EShaderAttributeFormat.COL4);
        return program;
    }


    private static IShader createVertexShader(IGraphics graphics) {
        String source = "" +
                "#version 330\n" +
                "" +
                "layout(location = 0) in vec4 bf_Position;" +
                "" +
                "uniform mat4 bf_ModelMatrix;" +
                "uniform mat4 bf_ProjectionMatrix;" +
//                "uniform mat4 bf_ProjectionViewModelMatrix;" +
                "" +
                "out vec4 color;" +
                "" +
                "void main ()" +
                "{" +
                "   gl_Position = bf_ProjectionMatrix * bf_ModelMatrix * bf_Position;" +
//                "   gl_Position = bf_ProjectionViewModelMatrix * bf_Position;" +
                "   color = vec4(1.0, 1.0, 1.0, 1.0);" +
                "}";
        IShader shader = graphics.createShader(EShaderType.VERTEX);
        shader.setSource(source);
        shader.compile();
        return shader;
    }

    private static IShader createFragmentShader(IGraphics graphics) {
        String source = "" +
                "#version 330\n" +
                "" +
                "layout(location = 0) out vec4 bf_FragColor;" +
                "" +
                "uniform vec4 bf_DiffuseColor;" +
                "" +
                "in vec4 color;" +
                "" +
                "void main ()" +
                "{" +
                "   bf_FragColor = color * bf_DiffuseColor;" +
                "}";

        IShader shader = graphics.createShader(EShaderType.FRAGMENT);
        shader.setSource(source);
        shader.compile();
        return shader;
    }


    public static void main(String[] args) {
        Engine engine = Engine.create();
        engine.initializeModules();

        IKeyboard keyboard = ObjectRegistry.get(IKeyboard.class).orElseThrow(NullPointerException::new);
        IMouse mouse = ObjectRegistry.get(IMouse.class).orElseThrow(NullPointerException::new);
        IGraphics graphics = ObjectRegistry.get(IGraphics.class).orElseThrow(NullPointerException::new);
        IWindow window = ObjectRegistry.get(IWindow.class).orElseThrow(NullPointerException::new);


        Mesh mesh = generateMesh(graphics);
        IProgram program = createProgram(graphics);

        Material material = new Material();
        material.setProgram(ERenderPass.FORWARD, program);
        int diffuseColorIdx = material.indexOf("DiffuseColor");
        material.setAttributeColor(diffuseColorIdx, new ColorRGBA(0.0f, 1.0f, 0.0f, 1.0f));


        MaterialInstance matInstance = new MaterialInstance(material);
        matInstance.setAttributeColor(diffuseColorIdx, new ColorRGBA(1.0f, 0.0f, 0.0f, 1.0f));


        Matrix4f mat = new Matrix4f();
        Matrix4f matT = new Matrix4f();
        Matrix4f matY = new Matrix4f();

        float aspect = (float) window.getHeight() / (float) window.getWidth();
        Matrix4f projMat = Matrix4f.projection(-1.0f, 1.0f, -aspect, aspect, 1.0f, 1024.0f);


        graphics.setProjectionMatrix(projMat);
        float rotValue = 0.0f;


        FPSCounter fpsCounter = new FPSCounter();
        int fps = 0;
        boolean running = true;
        boolean override = false;
        graphics.setClearColor(new ColorRGBA(0.0f, 0.0f, 0.5f, 1.0f));
        while (running) {
//            sleep(1);
            fpsCounter.tick();
            if (fps != fpsCounter.getFps()) {
                fps = fpsCounter.getFps();
                window.setTitle("Bifrost 3D " + fps + " FPS");
            }

            window.handleEvents();

            graphics.clear(true, true, true);

            Matrix4f.translation(0.0f, 0.0f, -5.0f, matT);
            Matrix4f.rotationY(rotValue, matY);

            Matrix4f.mul(matT, matY, mat);

            graphics.setModelMatrix(mat);
            graphics.setProgram(program);

            override = !override;
            matInstance.setOverride(diffuseColorIdx, override);

            matInstance.bind(graphics, ERenderPass.FORWARD);
            graphics.renderMesh(mesh);

            window.swap();


            if (mouse.isDown(EMouseButton.MB_RIGHT)) {
                mouse.setCursorMode(ECursorMode.LOCKED);
            } else {
                mouse.setCursorMode(ECursorMode.NORMAL);
            }
            if (keyboard.isPressed(EKey.K_ESCAPE)) {
                running = false;
            }


            rotValue += 0.025f;
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
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
