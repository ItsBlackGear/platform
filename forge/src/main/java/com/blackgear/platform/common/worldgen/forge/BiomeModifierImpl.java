package com.blackgear.platform.common.worldgen.forge;

import com.blackgear.platform.Platform;
import com.blackgear.platform.common.worldgen.BiomeModifier;
import com.blackgear.platform.common.worldgen.BiomeWriter;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Platform.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BiomeModifierImpl {
    public static void setup() {}

    @SubscribeEvent
    public static void event(BiomeLoadingEvent event) {
        BiomeModifier.INSTANCE.register(new ForgeBiomeWriter(event));
    }

    static class ForgeBiomeWriter extends BiomeWriter {
        private final BiomeLoadingEvent event;

        ForgeBiomeWriter(BiomeLoadingEvent event) {
            this.event = event;
        }

        @Override
        public ResourceLocation name() {
            return this.event.getName();
        }

        @Override
        public Biome.BiomeCategory category() {
            return this.event.getCategory();
        }

        @Override
        public void addFeature(GenerationStep.Decoration step, Holder<PlacedFeature> feature) {
            this.event.getGeneration().addFeature(step, feature);
        }

        @Override
        public void addCarver(GenerationStep.Carving step, Holder<? extends ConfiguredWorldCarver<?>> carver) {
            this.event.getGeneration().addCarver(step, carver);
        }

        @Override
        public void addSpawn(MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
            this.event.getSpawns().addSpawn(category, new MobSpawnSettings.SpawnerData(type, weight, minGroupSize, maxGroupSize));
        }
    }
}