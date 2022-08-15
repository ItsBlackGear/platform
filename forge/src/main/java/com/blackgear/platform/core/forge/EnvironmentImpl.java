package com.blackgear.platform.core.forge;

import com.blackgear.platform.core.Environment;
import net.minecraftforge.fml.loading.FMLLoader;

public class EnvironmentImpl {
    public static boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    public static Environment.Platform getPlatform() {
        return Environment.Platform.FORGE;
    }
}
