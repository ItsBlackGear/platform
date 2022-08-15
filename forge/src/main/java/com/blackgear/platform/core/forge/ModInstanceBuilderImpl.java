package com.blackgear.platform.core.forge;

import com.blackgear.platform.core.ModInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModInstanceBuilderImpl {
    public static ModInstance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        return new Forge(modId, common, postCommon, client, postClient);
    }

    static class Forge extends ModInstance {
        protected Forge(String modId, Runnable onCommon, Runnable onPostCommon, Runnable onClient, Runnable onPostClient) {
            super(modId, onCommon, onPostCommon, onClient, onPostClient);
        }

        @Override
        public void bootstrap() {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
            bus.<FMLCommonSetupEvent>addListener(event -> this.onPostCommon.run());
            bus.<FMLClientSetupEvent>addListener(event -> this.onPostClient.run());

            this.onCommon.run();
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> this.onClient.run());
        }
    }
}