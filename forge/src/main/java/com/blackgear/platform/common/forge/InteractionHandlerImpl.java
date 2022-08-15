package com.blackgear.platform.common.forge;

import com.blackgear.platform.Platform;
import com.blackgear.platform.common.InteractionHandler;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = Platform.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InteractionHandlerImpl {
    private static final Set<Consumer<PlayerInteractEvent.RightClickBlock>> BLOCK_INTERACTIONS = ConcurrentHashMap.newKeySet();
    private static final Set<Consumer<PlayerInteractEvent.RightClickItem>> ITEM_INTERACTIONS = ConcurrentHashMap.newKeySet();
    private static final Set<Consumer<PlayerInteractEvent.EntityInteract>> MOB_INTERACTIONS = ConcurrentHashMap.newKeySet();

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock event) {
        BLOCK_INTERACTIONS.forEach(handler -> handler.accept(event));
    }

    public static void createBlockInteraction(InteractionHandler.BlockInteraction interaction) {
        BLOCK_INTERACTIONS.add(event -> event.setCancellationResult(interaction.of(event.getPlayer(), event.getWorld(), event.getHand(), event.getHitVec())));
    }

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickItem event) {
        ITEM_INTERACTIONS.forEach(handler -> handler.accept(event));
    }

    public static void createItemInteraction(InteractionHandler.ItemInteraction interaction) {
        ITEM_INTERACTIONS.add(event -> event.setCancellationResult(interaction.of(event.getPlayer(), event.getWorld(), event.getHand()).getResult()));
    }

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.EntityInteract event) {
        MOB_INTERACTIONS.forEach(handler -> handler.accept(event));
    }

    public static void createMobInteraction(InteractionHandler.MobInteraction interaction) {
        MOB_INTERACTIONS.add(event -> event.setCancellationResult(interaction.of(event.getPlayer(), event.getWorld(), event.getHand(), event.getEntity())));
    }
}