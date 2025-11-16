package com.michael.frontend.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
// Pastikan ini menunjuk ke package tempat MyGdxGame Anda berada
import com.mygame.tp10.MyGdxGame;

public class Tp10Launcher {
    public static void main(String[] args) {
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MyGdxGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("TP10_State_Strategy_Demo");
        configuration.useVsync(true);
        configuration.setWindowedMode(640, 480);
        return configuration;
    }
}
