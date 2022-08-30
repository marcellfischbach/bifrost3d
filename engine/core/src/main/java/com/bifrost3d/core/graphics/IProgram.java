package com.bifrost3d.core.graphics;

import java.util.Set;

public interface IProgram {

    Set<String> namedAttributes ();
    int indexOf (String attributeName);
    int registerAttribute (String attributeName, EShaderAttributeFormat format);
    IShaderAttribute getAttribute (String attributeName);
    IShaderAttribute getAttribute (int idx);

    IShaderAttribute getAttribute (EShaderAttributeType type);


    void attach(IShader shader);

    void link ();

}
