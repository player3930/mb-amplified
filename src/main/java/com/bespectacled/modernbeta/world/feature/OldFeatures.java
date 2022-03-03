package com.bespectacled.modernbeta.world.feature;

import com.bespectacled.modernbeta.ModernBeta;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OldFeatures {
    public static final BetaFreezeTopLayerFeature BETA_FREEZE_TOP_LAYER = (BetaFreezeTopLayerFeature) register("beta_freeze_top_layer", new BetaFreezeTopLayerFeature(DefaultFeatureConfig.CODEC));
    public static final OldFancyOakFeature OLD_FANCY_OAK = (OldFancyOakFeature) register("old_fancy_oak", new OldFancyOakFeature(DefaultFeatureConfig.CODEC));
    public static final OreClayFeature ORE_CLAY = (OreClayFeature) register("ore_clay", new OreClayFeature(OreFeatureConfig.CODEC));
    
    private static Feature<?> register(String id, Feature<?> feature) {
        return Registry.register(Registry.FEATURE, ModernBeta.createId(id), feature);
    }
}
