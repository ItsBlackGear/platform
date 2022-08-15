package com.blackgear.platform.core.fabric;

import com.blackgear.platform.core.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class EnvironmentImpl {
    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    public static Environment.Loader getLoader() {
        return Environment.Loader.FABRIC;
    }
}
