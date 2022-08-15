package com.blackgear.platform.common;

import com.blackgear.platform.mixin.access.AxeItemAccessor;
import com.blackgear.platform.mixin.access.FireBlockAccessor;
import com.google.common.collect.Maps;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;

public class IntegrationHandler {
    public static void registerFlammable(Block block, int flameOdds, int burnOdds) {
        ((FireBlockAccessor)Blocks.FIRE).callSetFlammable(block, flameOdds, burnOdds);
    }

    public static void registerCompostable(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void addStrippable(Block input, Block output) {
        AxeItemAccessor.setStrippables(Maps.newHashMap(AxeItemAccessor.getStrippables()));
        AxeItemAccessor.getStrippables().put(input, output);
    }
}