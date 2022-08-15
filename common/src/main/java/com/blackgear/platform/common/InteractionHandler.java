package com.blackgear.platform.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class InteractionHandler {
    @ExpectPlatform
    public static void createBlockInteraction(BlockInteraction interaction) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void createItemInteraction(ItemInteraction interaction) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void createMobInteraction(MobInteraction interaction) {
        throw new AssertionError();
    }

    public interface BlockInteraction {
        InteractionResult of(Player player, Level level, InteractionHand hand, BlockHitResult result);
    }

    public interface ItemInteraction {
        InteractionResultHolder<ItemStack> of(Player player, Level level, InteractionHand hand);
    }

    public interface MobInteraction {
        InteractionResult of(Player player, Level level, InteractionHand hand, Entity entity);
    }
}