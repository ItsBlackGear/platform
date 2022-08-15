package com.blackgear.platform;

import com.blackgear.platform.core.ModInstance;

//<>

public class Platform {
    public static final String MOD_ID = "platform";
    public static final ModInstance INSTANCE = ModInstance.create(Platform.MOD_ID).build();

    public static void bootstrap() {
        INSTANCE.bootstrap();
    }
}