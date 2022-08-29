package com.bifrost3d.launcher;

import com.bifrost3d.core.Engine;
import com.bifrost3d.core.ObjectRegistry;
import com.bifrost3d.core.graphics.*;
import com.bifrost3d.core.window.*;
import com.bifrost3d.math.ColorRGBA;
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
        mesh.setIndices(indices);


        return mesh;
    }

    private static IProgram createProgram (IGraphics graphics) {
        IProgram program = graphics.createProgram();
        program.attach(createVertexShader(graphics));
        program.attach(createFragmentShader(graphics));
        program.link();
        return program;
    }


    private static IShader createVertexShader (IGraphics graphics) {
        String source = "" +
                "#version 330\n" +
                "" +
                "layout(location = 0) in vec4 bf_Position;" +
                "" +
                "out vec4 color;" +
                "" +
                "void main ()" +
                "{" +
                "   gl_Position = bf_Position;" +
                "   color = vec4(0.0, 0.0, 0.5, 1.0);" +
                "}";
        IShader shader = graphics.createShader(EShaderType.VERTEX);
        shader.setSource(source);
        shader.compile();
        return shader;
    }

    private static IShader createFragmentShader (IGraphics graphics) {
        String source = "" +
                "#version 330\n" +
                "" +
                //"layout(location = 0) out vec4 bf_FragColor;" +
                "" +
                "in vec4 color;" +
                "" +
                "void main ()" +
                "{" +
                "   gl_FragColor = color;" +
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

        boolean running = true;
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            window.handleEvents();

            graphics.clear(true, new ColorRGBA(0.5f, 0.0f, 0.0f, 1.0f), true, 1.0f, true, 0);

            graphics.setProgram(program);
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
        }
    }

}
