package com.crimsonedge.core.window;

public interface IMouse {

    void setCursorMode (ECursorMode mode);
    ECursorMode getCursorMode();

    double getX();
    double getDeltaX();

    double getY ();
    double getDeltaY();

    boolean isDown (EMouseButton button);
    boolean isUp (EMouseButton button);
    boolean isPressed (EMouseButton button);
    boolean isReleased (EMouseButton button);

}
