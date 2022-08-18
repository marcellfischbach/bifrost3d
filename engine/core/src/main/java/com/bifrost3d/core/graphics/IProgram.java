package com.bifrost3d.core.graphics;

public interface IProgram {

    void attach(IShader shader);
    void detach(IShader shader);

    void link ();

}
