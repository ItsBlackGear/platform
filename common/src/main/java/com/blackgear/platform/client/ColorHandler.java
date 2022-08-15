package com.blackgear.platform.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ColorHandler {
    @ExpectPlatform @SafeVarargs
    public static void register(ItemColor color, Supplier<? extends ItemLike>... items) {
        throw new AssertionError();
    }

    @ExpectPlatform @SafeVarargs
    public static void register(BlockColor color, Supplier<? extends Block>... blocks) {
        throw new AssertionError();
    }
}