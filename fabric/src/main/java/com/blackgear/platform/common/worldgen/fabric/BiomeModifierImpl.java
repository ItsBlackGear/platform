package com.blackgear.platform.common.worldgen.fabric;

import com.blackgear.platform.Platform;
import com.blackgear.platform.common.worldgen.BiomeModifier;
import com.blackgear.platform.common.worldgen.BiomeWriter;
import com.blackgear.platform.mixin.fabric.access.BiomeAccessor;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BiomeModifierImpl {
    public static void setup() {
        BiomeModifications.create(new ResourceLocation(Platform.MOD_ID, "biome_modifier")).add(ModificationPhase.ADDITIONS, ctx -> true, (selector, modifier) -> {
            BiomeModifier.INSTANCE.register(new FabricBiomeWriter(selector, modifier));
        });
    }

    static class FabricBiomeWriter extends BiomeWriter {
        private final BiomeSelectionContext selector;
        private final BiomeModificationContext modifier;

        FabricBiomeWriter(BiomeSelectionContext selector, BiomeModificationContext modifier) {
            this.selector = selector;
            this.modifier = modifier;
        }

        @Override
        public ResourceLocation name() {
            return this.selector.getBiomeKey().location();
        }

        @Override
        public Biome.BiomeCategory category() {
            return ((BiomeAccessor)(Object)this.selector.getBiome()).callGetBiomeCategory();
        }

        @Override
        public void addFeature(GenerationStep.Decoration step, Holder<PlacedFeature> feature) {
            this.modifier.getGenerationSettings().addBuiltInFeature(step, feature.value());
        }

        @Override
        public void addCarver(GenerationStep.Carving step, Holder<? extends ConfiguredWorldCarver<?>> carver) {
            this.modifier.getGenerationSettings().addBuiltInCarver(step, carver.value());
        }

        @Override
        public void addSpawn(MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
            this.modifier.getSpawnSettings().addSpawn(category, new MobSpawnSettings.SpawnerData(type, weight, minGroupSize, maxGroupSize));
        }
    }
}