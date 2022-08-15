package com.blackgear.platform.client.forge;

import com.blackgear.platform.Platform;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Platform.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorHandlerImpl {
    private static final Set<Consumer<ColorHandlerEvent.Item>> ITEM_COLORS = ConcurrentHashMap.newKeySet();
    private static final Set<Consumer<ColorHandlerEvent.Block>> BLOCK_COLORS = ConcurrentHashMap.newKeySet();

    @SafeVarargs
    public static void register(ItemColor color, Supplier<? extends ItemLike>... items) {
        ITEM_COLORS.add(event -> {
            for (Supplier<? extends ItemLike> item : items) event.getItemColors().register(color, item.get());
        });
    }

    @SafeVarargs
    public static void register(BlockColor color, Supplier<? extends Block>... blocks) {
        BLOCK_COLORS.add(event -> {
            for (Supplier<? extends Block> block : blocks) event.getBlockColors().register(color, block.get());
        });
    }

    @SubscribeEvent
    public static void onEvent(ColorHandlerEvent.Item event) {
        ITEM_COLORS.forEach(handler -> handler.accept(event));
    }

    @SubscribeEvent
    public static void onEvent(ColorHandlerEvent.Block event) {
        BLOCK_COLORS.forEach(handler -> handler.accept(event));
    }
}