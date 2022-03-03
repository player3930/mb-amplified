package com.bespectacled.modernbeta.api.world.gen;

import com.bespectacled.modernbeta.util.BlockStates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;

public interface NoiseChunkImitable {
    /**
     * Gets blockstate at block coordinates given block and default fluid block.
     * Simulates a noise density for the purpose of masking terrain around structures.
     * Used for 2D noise terrain generators (i.e. Infdev 20100227 and Indev terrain generators).
     * 
     * @param x x-coordinate in absolute block coordinates.
     * @param y y-coordinate in absolute block coordinates.
     * @param z z-coordinate in absolute block coordinates.
     * @param blockToSet Block to get blockstate for.
     * @oaran defaultFluid Default fluid block.
     * 
     * @return A blockstate.
     */
    default BlockState getBlockState(StructureWeightSampler weightSampler, int x, int y, int z, Block blockToSet, Block defaultFluid, Block defaultBlock) {
        boolean isFluid = blockToSet == Blocks.AIR || blockToSet == defaultFluid;
        double simDensity = isFluid ? -25D : 25D;
        
        double clampedDensity = MathHelper.clamp(simDensity / 200.0, -1.0, 1.0);
        clampedDensity = clampedDensity / 2.0 - clampedDensity * clampedDensity * clampedDensity / 24.0;
        // clampedDensity += weightSampler.getWeight(x, y, z);
        
        BlockState blockState = blockToSet.getDefaultState();
        if (clampedDensity > 0.0 && isFluid) {
            blockState = defaultBlock.getDefaultState();
        } else if (clampedDensity < 0.0 && !isFluid) {
            blockState = BlockStates.AIR;
        }

        return blockState;
    }
    
}
