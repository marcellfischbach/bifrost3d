package com.bifrost3d.graphics.opengl.gl4;

import static org.lwjgl.opengl.GL44.*;

public abstract class GLError {

    public static void check () {
        int error = glGetError();
        if (error == GL_NO_ERROR) {
            return;
        }

        StackTraceElement stack = getStack();
        switch (error) 
        {
            case GL_INVALID_ENUM:
                System.out.printf("[GL_INVALID_ENUM] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_INVALID_VALUE:
                System.out.printf("[GL_INVALID_VALUE] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_INVALID_OPERATION:
                System.out.printf("[GL_INVALID_OPERATION] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_INVALID_FRAMEBUFFER_OPERATION:
                System.out.printf("[GL_INVALID_FRAMEBUFFER_OPERATION] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_OUT_OF_MEMORY:
                System.out.printf("[GL_OUT_OF_MEMORY] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_STACK_UNDERFLOW:
                System.out.printf("[GL_STACK_UNDERFLOW] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            case GL_STACK_OVERFLOW:
                System.out.printf("[GL_STACK_OVERFLOW] @ %s.%s (%d)%n", stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
            default:
                System.out.printf("[Unknown: 0x%08x] @ %s.%s (%d)%n", error, stack.getClassName(), stack.getMethodName(), stack.getLineNumber());
                break;
        }
    }


    private static StackTraceElement getStack () {
        return Thread.currentThread().getStackTrace()[2];
    }

    private GLError () {

    }
}
