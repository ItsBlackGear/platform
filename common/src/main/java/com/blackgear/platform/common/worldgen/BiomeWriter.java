package com.blackgear.platform.common.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public abstract class BiomeWriter {
    public abstract void addFeature(GenerationStep.Decoration step, Holder<PlacedFeature> feature);

    public abstract void addCarver(GenerationStep.Carving step, Holder<? extends ConfiguredWorldCarver<?>> carver);

    public abstract void addSpawn(MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize);
}