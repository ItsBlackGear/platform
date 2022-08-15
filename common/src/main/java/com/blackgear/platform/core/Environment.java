package com.blackgear.platform.core;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class Environment {
    @ExpectPlatform
    public static boolean isClient() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Platform getPlatform() {
        throw new AssertionError();
    }

    public enum Platform {
        FORGE,
        FABRIC
    }
}