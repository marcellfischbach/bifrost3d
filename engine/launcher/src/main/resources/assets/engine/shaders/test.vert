#version 330

layout(location = 0) in vec4 bf_Position;
layout(location = 5) in vec2 bf_UV;

uniform mat4 bf_ModelMatrix;
uniform mat4 bf_ProjectionViewMatrix;

out vec4 color;
out vec2 uv;

void main ()
{
   gl_Position = bf_ProjectionViewMatrix * bf_ModelMatrix * bf_Position;
   color = vec4(1.0, 1.0, 1.0, 1.0);
   uv = bf_UV;
}

