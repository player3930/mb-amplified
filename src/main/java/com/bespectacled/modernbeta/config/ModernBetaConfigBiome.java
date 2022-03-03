package com.bespectacled.modernbeta.config;

import com.bespectacled.modernbeta.api.registry.BuiltInTypes;
import com.bespectacled.modernbeta.world.biome.beta.BetaBiomes;
import com.bespectacled.modernbeta.world.biome.inf.InfBiomes;
import com.bespectacled.modernbeta.world.biome.pe.PEBiomes;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "config_biome")
public class ModernBetaConfigBiome implements ConfigData {

    // General
    public String biomeType = BuiltInTypes.Biome.BETA.name;
    
    // Oceans
    public boolean generateOceans = false;
    
    // Beta
    public String betaDesertBiome = BetaBiomes.DESERT_ID.toString();
    public String betaForestBiome = BetaBiomes.FOREST_ID.toString();
    public String betaIceDesertBiome = BetaBiomes.ICE_DESERT_ID.toString();
    public String betaPlainsBiome = BetaBiomes.PLAINS_ID.toString();
    public String betaPineForestBiome = BetaBiomes.PINE_FOREST_ID.toString();
    public String betaRainforestBiome = BetaBiomes.RAINFOREST_ID.toString();
    public String betaSavannaBiome = BetaBiomes.SAVANNA_ID.toString();
    public String betaShrublandBiome = BetaBiomes.SHRUBLAND_ID.toString();
    public String betaSeasonalForestBiome = BetaBiomes.SEASONAL_FOREST_ID.toString();
    public String betaSwamplandBiome = BetaBiomes.SWAMPLAND_ID.toString();
    public String betaTaigaBiome = BetaBiomes.TAIGA_ID.toString();
    public String betaTundraBiome = BetaBiomes.TUNDRA_ID.toString();
    public String betaOceanBiome = BetaBiomes.OCEAN_ID.toString();
    public String betaColdOceanBiome = BetaBiomes.COLD_OCEAN_ID.toString();
    public String betaFrozenOceanBiome = BetaBiomes.FROZEN_OCEAN_ID.toString();
    public String betaLukewarmOceanBiome = BetaBiomes.LUKEWARM_OCEAN_ID.toString();
    public String betaWarmOceanBiome = BetaBiomes.WARM_OCEAN_ID.toString();
    
    // PE
    public String peDesertBiome = PEBiomes.PE_DESERT_ID.toString();
    public String peForestBiome = PEBiomes.PE_FOREST_ID.toString();
    public String peIceDesertBiome = PEBiomes.PE_TUNDRA_ID.toString();
    public String pePlainsBiome = PEBiomes.PE_PLAINS_ID.toString();
    public String peRainforestBiome = PEBiomes.PE_RAINFOREST_ID.toString();
    public String peSavannaBiome = PEBiomes.PE_SAVANNA_ID.toString();
    public String peShrublandBiome = PEBiomes.PE_SHRUBLAND_ID.toString();
    public String peSeasonalForestBiome = PEBiomes.PE_SEASONAL_FOREST_ID.toString();
    public String peSwamplandBiome = PEBiomes.PE_SWAMPLAND_ID.toString();
    public String peTaigaBiome = PEBiomes.PE_TAIGA_ID.toString();
    public String peTundraBiome = PEBiomes.PE_TUNDRA_ID.toString();
    public String peOceanBiome = PEBiomes.PE_OCEAN_ID.toString();
    public String peColdOceanBiome = PEBiomes.PE_COLD_OCEAN_ID.toString();
    public String peFrozenOceanBiome = PEBiomes.PE_FROZEN_OCEAN_ID.toString();
    public String peLukewarmOceanBiome = PEBiomes.PE_LUKEWARM_OCEAN_ID.toString();
    public String peWarmOceanBiome = PEBiomes.PE_WARM_OCEAN_ID.toString();
    
    // Single
    public String singleBiome = InfBiomes.ALPHA_ID.toString();
    
    // Vanilla
    public int vanillaBiomeSize = 4;
    public int vanillaOceanBiomeSize = 6;
}
