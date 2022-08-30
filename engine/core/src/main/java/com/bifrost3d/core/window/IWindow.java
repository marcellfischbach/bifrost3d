package com.bifrost3d.core.window;

public interface IWindow {

    int getWidth();
    int getHeight ();

    void setTitle (String title);
    String getTitle();

    void setVSync (boolean vSync);
    boolean isVSync ();

    void handleEvents();
    void swap ();


    IKeyboard getKeyboard ();
}
