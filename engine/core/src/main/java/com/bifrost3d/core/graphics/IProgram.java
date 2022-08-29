package com.bifrost3d.core.graphics;

public interface IProgram {

    void registerAttribute (String attributeName, EShaderAttributeFormat format);
    IShaderAttribute getAttribute (String attributeName);

    IShaderAttribute getAttribute (EShaderAttributeType type);


    void attach(IShader shader);

    void link ();

}
