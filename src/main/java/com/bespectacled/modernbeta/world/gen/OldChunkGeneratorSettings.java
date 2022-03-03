package com.bespectacled.modernbeta.world.gen;

import java.util.HashMap;
import java.util.Map;
import com.bespectacled.modernbeta.ModernBeta;
import com.bespectacled.modernbeta.api.registry.BuiltInTypes;
import com.bespectacled.modernbeta.util.BlockStates;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class OldChunkGeneratorSettings {
    public static final Identifier BETA;
    public static final Identifier ALPHA;
    public static final Identifier SKYLANDS;
    public static final Identifier INFDEV_611;
    public static final Identifier INFDEV_420;
    public static final Identifier INFDEV_415;
    public static final Identifier INFDEV_227;
    public static final Identifier INDEV;
    public static final Identifier CLASSIC_0_30;
    public static final Identifier BETA_ISLANDS;
    public static final Identifier PE;
    
    public static final ChunkGeneratorSettings BETA_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings ALPHA_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings SKYLANDS_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings INFDEV_611_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings INFDEV_420_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings INFDEV_415_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings INFDEV_227_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings INDEV_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings CLASSIC_0_30_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings BETA_ISLANDS_GENERATOR_SETTINGS;
    public static final ChunkGeneratorSettings PE_GENERATOR_SETTINGS;
    
    public static final Map<Identifier, ChunkGeneratorSettings> SETTINGS_MAP = new HashMap<Identifier, ChunkGeneratorSettings>();

    public static void register() {
        register(BETA, BETA_GENERATOR_SETTINGS);
        register(SKYLANDS, SKYLANDS_GENERATOR_SETTINGS);
        register(ALPHA, ALPHA_GENERATOR_SETTINGS);
        register(INFDEV_611, INFDEV_611_GENERATOR_SETTINGS);
        register(INFDEV_420, INFDEV_420_GENERATOR_SETTINGS);
        register(INFDEV_415, INFDEV_415_GENERATOR_SETTINGS);
        register(INFDEV_227, INFDEV_227_GENERATOR_SETTINGS);
        register(INDEV, INDEV_GENERATOR_SETTINGS);
        register(CLASSIC_0_30, CLASSIC_0_30_GENERATOR_SETTINGS);
        register(BETA_ISLANDS, BETA_ISLANDS_GENERATOR_SETTINGS);
        register(PE, PE_GENERATOR_SETTINGS);
    }
    
    private static ChunkGeneratorSettings register(Identifier id, ChunkGeneratorSettings settings) {
        SETTINGS_MAP.put(id, settings);
        BuiltinRegistries.<ChunkGeneratorSettings, ChunkGeneratorSettings>add(BuiltinRegistries.CHUNK_GENERATOR_SETTINGS, id, settings);
        return settings;
    }
    
    static {
        BETA = ModernBeta.createId(BuiltInTypes.Chunk.BETA.name);
        ALPHA = ModernBeta.createId(BuiltInTypes.Chunk.ALPHA.name);
        SKYLANDS = ModernBeta.createId(BuiltInTypes.Chunk.SKYLANDS.name);
        INFDEV_611 = ModernBeta.createId(BuiltInTypes.Chunk.INFDEV_611.name);
        INFDEV_420 = ModernBeta.createId(BuiltInTypes.Chunk.INFDEV_420.name);
        INFDEV_415 = ModernBeta.createId(BuiltInTypes.Chunk.INFDEV_415.name);
        INFDEV_227 = ModernBeta.createId(BuiltInTypes.Chunk.INFDEV_227.name);
        INDEV = ModernBeta.createId(BuiltInTypes.Chunk.INDEV.name);
        CLASSIC_0_30 = ModernBeta.createId(BuiltInTypes.Chunk.CLASSIC_0_30.name);
        BETA_ISLANDS = ModernBeta.createId(BuiltInTypes.Chunk.BETA_ISLANDS.name);
        PE = ModernBeta.createId(BuiltInTypes.Chunk.PE.name);

        BETA_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.BETA_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        ALPHA_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.ALPHA_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        SKYLANDS_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.SKYLANDS_SHAPE_CONFIG, BlockStates.STONE, BlockStates.AIR, -10, -10, 0, false);
        INFDEV_611_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.INFDEV_611_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        INFDEV_420_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.INFDEV_420_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        INFDEV_415_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.INFDEV_415_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        INFDEV_227_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.BETA_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        INDEV_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.INDEV_STRUCTURES, OldGeneratorConfig.INDEV_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        CLASSIC_0_30_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.INDEV_STRUCTURES, OldGeneratorConfig.INDEV_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        BETA_ISLANDS_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.BETA_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
        PE_GENERATOR_SETTINGS = new ChunkGeneratorSettings(OldGeneratorConfig.STRUCTURES, OldGeneratorConfig.PE_SHAPE_CONFIG, BlockStates.STONE, BlockStates.WATER, -10, 0, 64, false);
    }
}
