package com.blackgear.platform.client.forge;

import com.blackgear.platform.Platform;
import com.blackgear.platform.client.ParticleHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Platform.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleHandlerImpl {
    private static final Set<Consumer<ParticleFactoryRegisterEvent>> FACTORIES = ConcurrentHashMap.newKeySet();

    @SubscribeEvent
    public static void onEvent(ParticleFactoryRegisterEvent event) {
        FACTORIES.forEach(handler -> handler.accept(event));
    }

    public static <T extends ParticleOptions, P extends ParticleType<T>> void create(Supplier<P> type, ParticleProvider<T> provider) {
        FACTORIES.add(event -> Minecraft.getInstance().particleEngine.register(type.get(), provider));
    }

    public static <T extends ParticleOptions, P extends ParticleType<T>> void create(Supplier<P> type, ParticleHandler.Factory<T> factory) {
        FACTORIES.add(event -> Minecraft.getInstance().particleEngine.register(type.get(), factory::create));
    }
}