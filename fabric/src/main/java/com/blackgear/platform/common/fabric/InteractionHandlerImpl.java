package com.blackgear.platform.common.fabric;

import com.blackgear.platform.common.InteractionHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class InteractionHandlerImpl {
    public static void createBlockInteraction(InteractionHandler.BlockInteraction interaction) {
        UseBlockCallback.EVENT.register(interaction::of);
    }

    public static void createItemInteraction(InteractionHandler.ItemInteraction interaction) {
        UseItemCallback.EVENT.register(interaction::of);
    }

    public static void createMobInteraction(InteractionHandler.MobInteraction interaction) {
        UseEntityCallback.EVENT.register((player, level, hand, entity, result) -> interaction.of(player, level, hand, entity));
    }
}