package com.bifrost3d.core.graphics;

import java.util.Set;

@SuppressWarnings("unused")
public interface IProgram {

    Set<String> namedAttributes ();
    int indexOf (String attributeName);
    void registerAttribute (String attributeName, EShaderAttributeType format);
    IShaderAttribute getAttribute (String attributeName);
    IShaderAttribute getAttribute (int idx);

    IShaderAttribute getAttribute (EShaderAttribute type);


    void attach(IShader shader);

    void link ();

}
