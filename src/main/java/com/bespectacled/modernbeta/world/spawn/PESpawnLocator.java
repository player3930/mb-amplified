package com.bespectacled.modernbeta.world.spawn;

import java.util.Optional;

import org.apache.logging.log4j.Level;

import com.bespectacled.modernbeta.ModernBeta;
import com.bespectacled.modernbeta.api.world.gen.ChunkProvider;
import com.bespectacled.modernbeta.api.world.gen.NoiseChunkProvider;
import com.bespectacled.modernbeta.api.world.spawn.SpawnLocator;
import com.bespectacled.modernbeta.util.chunk.HeightmapChunk;
import com.bespectacled.modernbeta.util.mersenne.MTRandom;
import com.bespectacled.modernbeta.util.noise.PerlinOctaveNoise;
import com.bespectacled.modernbeta.world.biome.OldBiomeSource;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

/*
 * Port of MCPE v0.6.0 alpha player spawn locator.
 * 
 */
public class PESpawnLocator implements SpawnLocator {
    private final MTRandom rand;
    
    private final ChunkProvider chunkProvider;
    private final PerlinOctaveNoise beachNoiseOctaves;
    
    public PESpawnLocator(ChunkProvider chunkProvider, PerlinOctaveNoise beachNoiseOctaves) {
        this.rand = new MTRandom();
        
        this.chunkProvider = chunkProvider;
        this.beachNoiseOctaves = beachNoiseOctaves;
    }
    
    @Override
    public Optional<BlockPos> locateSpawn() {
        ModernBeta.log(Level.INFO, "Setting a PE beach spawn..");
        
        int x = 0;
        int z = 0;
        int attempts = 0;
        
        while(!this.isSandAt(x, z)) {
            if (attempts > 10000) {
                ModernBeta.log(Level.INFO, "Exceeded spawn attempts, spawning anyway at 128,128..");
                
                x = 128;
                z = 128;
                break;
            }
            
            x += this.rand.nextInt(32) - this.rand.nextInt(32);
            z += this.rand.nextInt(32) - this.rand.nextInt(32);
            
            // Keep spawn pos within bounds of original PE world size
            if (x < 4) x += 32;
            if (x >= 251) x -= 32;
            
            if (z < 4) z += 32;
            if (z >= 251) z -= 32;
            
            attempts++;
        }

        int y = (this.chunkProvider instanceof NoiseChunkProvider) ?
            ((NoiseChunkProvider)this.chunkProvider).getHeight(x, z, HeightmapChunk.Type.SURFACE_FLOOR) :
            this.chunkProvider.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
   
        return Optional.of(new BlockPos(x, y, z));
    }

    private boolean isSandAt(int x, int z) {
        double eighth = 0.03125D;
        int seaLevel = this.chunkProvider.getSeaLevel();

        int y = (this.chunkProvider instanceof NoiseChunkProvider) ?
            ((NoiseChunkProvider)this.chunkProvider).getHeight(x, z, HeightmapChunk.Type.SURFACE_FLOOR) :
            this.chunkProvider.getHeight(x, z, Heightmap.Type.OCEAN_FLOOR_WG);

        Biome biome = (this.chunkProvider.getChunkGenerator().getBiomeSource() instanceof OldBiomeSource) ? 
            ((OldBiomeSource)this.chunkProvider.getChunkGenerator().getBiomeSource()).getBiomeForSurfaceGen(x, y, z) :
            this.chunkProvider.getBiomeForNoiseGen(x >> 2, y >> 2, z >> 2);
        BlockState topMaterial = biome.getGenerationSettings().getSurfaceConfig().getTopMaterial();
        
        return 
            (topMaterial.isOf(Blocks.SAND) && y >= seaLevel) || 
            (this.beachNoiseOctaves.sample(x * eighth, z * eighth, 0.0) > 0.0 && y >= seaLevel && y <= seaLevel + 2);
    }
}
