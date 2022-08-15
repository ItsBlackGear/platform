package com.blackgear.platform.core;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class Environment {
    @ExpectPlatform
    public static boolean isClient() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Loader getLoader() {
        throw new AssertionError();
    }

    public enum Loader {
        FORGE,
        FABRIC
    }
}