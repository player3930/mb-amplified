package com.bespectacled.modernbeta.world.biome;

import java.util.function.Predicate;

import com.bespectacled.modernbeta.world.structure.OldStructures;
import com.google.common.collect.ImmutableList;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class OldBiomeModifier {
    private static ImmutableList<RegistryKey<Biome>> VANILLA_OCEANS = ImmutableList.of(
        BiomeKeys.OCEAN,
        BiomeKeys.COLD_OCEAN,
        BiomeKeys.FROZEN_OCEAN,
        BiomeKeys.LUKEWARM_OCEAN,
        BiomeKeys.WARM_OCEAN
    );
    
    public static void addShrineToOceans() {
        Predicate<BiomeSelectionContext> biomeSelector = BiomeSelectors.includeByKey(VANILLA_OCEANS);
        BiomeModifications.addStructure(biomeSelector, OldStructures.OCEAN_SHRINE_KEY);
    }
}
