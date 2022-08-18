package com.bifrost3d.core.window;

public interface IKeyboard {

    boolean isDown (EKey key);
    boolean isUp (EKey key);
    boolean isPressed(EKey key);
    boolean isReleased(EKey key);

}
