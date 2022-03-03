package com.bespectacled.modernbeta.world.biome.inf;

import com.bespectacled.modernbeta.ModernBeta;
import com.bespectacled.modernbeta.world.biome.OldBiomes;

import net.minecraft.util.Identifier;

public class InfBiomes {
    protected static final boolean ADD_LAKES_ALPHA = false;
    protected static final boolean ADD_SPRINGS_ALPHA = true;
    
    protected static final boolean ADD_ALTERNATE_STONES_ALPHA = false;
    protected static final boolean ADD_NEW_MINEABLES_ALPHA = false;

    protected static final boolean ADD_LAKES_INF_611 = false;
    protected static final boolean ADD_SPRINGS_INF_611 = false;
    
    protected static final boolean ADD_LAKES_INF_420 = false;
    protected static final boolean ADD_SPRINGS_INF_420 = false;
    
    protected static final boolean ADD_LAKES_INF_415 = false;
    protected static final boolean ADD_SPRINGS_INF_415 = false;
    
    protected static final boolean ADD_LAKES_INF_227 = false;
    protected static final boolean ADD_SPRINGS_INF_227 = false;
    
    public static final Identifier ALPHA_ID = ModernBeta.createId("alpha");
    public static final Identifier ALPHA_WINTER_ID = ModernBeta.createId("alpha_winter");
    
    public static final Identifier INFDEV_611_ID = ModernBeta.createId("infdev_611");
    public static final Identifier INFDEV_611_WINTER_ID = ModernBeta.createId("infdev_611_winter");

    public static final Identifier INFDEV_420_ID = ModernBeta.createId("infdev_420");
    public static final Identifier INFDEV_420_WINTER_ID = ModernBeta.createId("infdev_420_winter");
    
    public static final Identifier INFDEV_415_ID = ModernBeta.createId("infdev_415");
    public static final Identifier INFDEV_415_WINTER_ID = ModernBeta.createId("infdev_415_winter");
    
    public static final Identifier INFDEV_227_ID = ModernBeta.createId("infdev_227");
    public static final Identifier INFDEV_227_WINTER_ID = ModernBeta.createId("infdev_227_winter");
    
    public static void registerAlphaBiomes() {
        OldBiomes.register(ALPHA_ID, Alpha.BIOME);
        OldBiomes.register(ALPHA_WINTER_ID, AlphaWinter.BIOME);
    }
    
    public static void registerInfdev611Biomes() {
        OldBiomes.register(INFDEV_611_ID, Infdev611.BIOME);
        OldBiomes.register(INFDEV_611_WINTER_ID, Infdev611Winter.BIOME);
    }
    
    public static void registerInfdev420Biomes() {
        OldBiomes.register(INFDEV_420_ID, Infdev420.BIOME);
        OldBiomes.register(INFDEV_420_WINTER_ID, Infdev420Winter.BIOME);
    }
    
    public static void registerInfdev415Biomes() {
        OldBiomes.register(INFDEV_415_ID, Infdev415.BIOME);
        OldBiomes.register(INFDEV_415_WINTER_ID, Infdev415Winter.BIOME);
    }
    
    public static void registerInfdev227Biomes() {
        OldBiomes.register(INFDEV_227_ID, Infdev227.BIOME);
        OldBiomes.register(INFDEV_227_WINTER_ID, Infdev227Winter.BIOME);
    }
}
