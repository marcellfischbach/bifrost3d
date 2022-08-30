package com.bifrost3d.graphics.opengl.gl4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.opengl.GL44.*;

public abstract class GLError {

    private static final Logger LOGGER = LogManager.getLogger(GLError.class);

    public static void check () {
        int error = glGetError();
        if (error == GL_NO_ERROR) {
            return;
        }

        StackTraceElement stack = getStack();
        switch (error) 
        {
            case GL_INVALID_ENUM:
                LOGGER.error("[GL_INVALID_ENUM] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_INVALID_VALUE:
                LOGGER.error("[GL_INVALID_VALUE] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_INVALID_OPERATION:
                LOGGER.error("[GL_INVALID_OPERATION] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_INVALID_FRAMEBUFFER_OPERATION:
                LOGGER.error("[GL_INVALID_FRAMEBUFFER_OPERATION] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_OUT_OF_MEMORY:
                LOGGER.error("[GL_OUT_OF_MEMORY] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_STACK_UNDERFLOW:
                LOGGER.error("[GL_STACK_UNDERFLOW] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_STACK_OVERFLOW:
                LOGGER.error("[GL_STACK_OVERFLOW] @ {}.{} ({})", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            default:
                LOGGER.error("[Unknown: {}] @ {}.{} ({})", error, stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
        }
    }


    private static StackTraceElement getStack () {
        return Thread.currentThread().getStackTrace()[3];
    }

    private GLError () {

    }
}
