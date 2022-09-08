#version 330

layout(location = 0) out vec4 bf_FragColor;

uniform vec4 bf_DiffuseColor;
uniform sampler2D bf_Diffuse;

in vec4 color;
in vec2 uv;

#include<:test.glsl>
#include<:test.glsl>

void main ()
{
   vec4 tex = texture(bf_Diffuse, uv);
   bf_FragColor = tex * color * bf_DiffuseColor * acquire_color();
}

