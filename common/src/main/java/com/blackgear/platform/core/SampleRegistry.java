package com.blackgear.platform.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public record SampleRegistry<T>(ResourceKey<Registry<T>> key, Registry<T> registry) {}