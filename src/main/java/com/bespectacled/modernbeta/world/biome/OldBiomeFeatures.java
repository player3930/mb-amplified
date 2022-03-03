package com.bespectacled.modernbeta.world.biome;

import com.bespectacled.modernbeta.world.carver.OldCarvers;
import com.bespectacled.modernbeta.world.feature.OldConfiguredFeatures;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.GenerationStep.Feature;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class OldBiomeFeatures {
    public static void addDefaultFeatures(GenerationSettings.Builder genSettings, boolean isOcean, boolean addLakes, boolean addSprings) {
        if (isOcean)
            DefaultBiomeFeatures.addOceanStructures(genSettings);
        else
            DefaultBiomeFeatures.addDefaultUndergroundStructures(genSettings);
        
        if (addLakes)
		DefaultBiomeFeatures.addDefaultLakes(genSettings);
        DefaultBiomeFeatures.addDungeons(genSettings);
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(genSettings);
        if (addSprings)
		DefaultBiomeFeatures.addSprings(genSettings);
    }

    public static void addDefaultFeatures(GenerationSettings.Builder genSettings, boolean isOcean, boolean addWaterLakes, boolean addWaterSprings, boolean addLavaLakes, boolean addLavaSprings) {
        if (isOcean)
            DefaultBiomeFeatures.addOceanStructures(genSettings);
        else
            DefaultBiomeFeatures.addDefaultUndergroundStructures(genSettings);
        
        if (addWaterLakes)
		genSettings.feature(GenerationStep.Feature.LAKES, ConfiguredFeatures.LAKE_WATER);
	if (addLavaLakes)
		genSettings.feature(GenerationStep.Feature.LAKES, ConfiguredFeatures.LAKE_LAVA);
        DefaultBiomeFeatures.addDungeons(genSettings);
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(genSettings);
        if (addWaterSprings)
		genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_WATER);
        if (addLavaSprings)
		genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
    }
    
    public static void addOres(GenerationSettings.Builder genSettings) {
        genSettings.feature(Feature.UNDERGROUND_ORES, OldConfiguredFeatures.ORE_EMERALD_Y95);
    }

    public static void addMineables(GenerationSettings.Builder genSettings, boolean addAlternateStones) {
        genSettings.feature(Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DIRT);
        genSettings.feature(Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GRAVEL);
        genSettings.feature(Feature.UNDERGROUND_ORES, OldConfiguredFeatures.ORE_CLAY);
        
        if (addAlternateStones) {
            genSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GRANITE);
            genSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DIORITE);
            genSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_ANDESITE);
        }
    }

    public static void addCarvers(GenerationSettings.Builder genSettings, boolean addCanyons) {
        genSettings.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        
        if (addCanyons) {
            genSettings.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        }
    }

    public static void addOceanCarvers(GenerationSettings.Builder genSettings) {
        genSettings.carver(GenerationStep.Carver.LIQUID, ConfiguredCarvers.UNDERWATER_CAVE);
        genSettings.carver(GenerationStep.Carver.LIQUID, ConfiguredCarvers.UNDERWATER_CANYON);
    }

    public static void addVegetalPatches(GenerationSettings.Builder genSettings) {
        genSettings.feature(Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUGAR_CANE);
        genSettings.feature(Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PUMPKIN);
    }

    public static void addBetaFrozenTopLayer(GenerationSettings.Builder genSettings) {
        genSettings.feature(Feature.TOP_LAYER_MODIFICATION, OldConfiguredFeatures.BETA_FREEZE_TOP_LAYER);
    }
}
