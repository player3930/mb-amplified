/*
 * Decompiled with CFR 0.150.
 */
package com.bespectacled.modernbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.feature.SpringFeature;
import com.bespectacled.modernbeta.util.BlockStates;
import com.bespectacled.modernbeta.world.gen.OldChunkGenerator;

@Mixin(SpringFeature.class)
public class MixinSpringFeature {
    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    public void injectGenerate(StructureWorldAccess arg, ChunkGenerator arg2, Random random, BlockPos arg3, SpringFeatureConfig arg4, CallbackInfoReturnable<Boolean> cir) {
        boolean flag = false;
        if (!arg4.validBlocks.contains(arg.getBlockState(arg3.up()).getBlock())) {
           cir.setReturnValue(false);
           flag = true;
        }
        if (arg4.requiresBlockBelow && !arg4.validBlocks.contains(arg.getBlockState(arg3.down()).getBlock())) {
           cir.setReturnValue(false);
           flag = true;
        }
        BlockState lv = arg.getBlockState(arg3);
        if (!lv.isAir() && !arg4.validBlocks.contains(lv.getBlock())) {
           cir.setReturnValue(false);
           flag = true;
        }
        int i = 0;
        int j = 0;
        if (arg4.validBlocks.contains(arg.getBlockState(arg3.west()).getBlock())) {
            ++j;
        }
        if (arg4.validBlocks.contains(arg.getBlockState(arg3.east()).getBlock())) {
            ++j;
        }
        if (arg4.validBlocks.contains(arg.getBlockState(arg3.north()).getBlock())) {
            ++j;
        }
        if (arg4.validBlocks.contains(arg.getBlockState(arg3.south()).getBlock())) {
            ++j;
        }
        if (arg4.validBlocks.contains(arg.getBlockState(arg3.down()).getBlock())) {
            ++j;
        }
        int k = 0;
        if (arg.isAir(arg3.west())) {
            ++k;
        }
        if (arg.isAir(arg3.east())) {
            ++k;
        }
        if (arg.isAir(arg3.north())) {
            ++k;
        }
        if (arg.isAir(arg3.south())) {
            ++k;
        }
        if (arg.isAir(arg3.down())) {
            ++k;
        }
        int l = (arg3.getX() + arg3.getY() + arg3.getZ()) % 4;
        if (j == arg4.rockCount && k == arg4.holeCount) {
	    if(l == 0 || !(arg2 instanceof OldChunkGenerator)) {
                arg.setBlockState(arg3, arg4.state.getBlockState(), 2);
            	arg.getFluidTickScheduler().schedule(arg3, arg4.state.getFluid(), 0);
            }
            else {
                if(!arg.isAir(arg3.down()))
			arg.setBlockState(arg3, arg.getBlockState(arg3.down()), 2);
		else if(!arg.isAir(arg3.up()))
            		arg.setBlockState(arg3, arg.getBlockState(arg3.up()), 2);
		else if(!arg.isAir(arg3.west()))
            		arg.setBlockState(arg3, arg.getBlockState(arg3.west()), 2);
		else if (!arg.isAir(arg3.east()))
            		arg.setBlockState(arg3, arg.getBlockState(arg3.east()), 2);
		else if (!arg.isAir(arg3.north()))
            		arg.setBlockState(arg3, arg.getBlockState(arg3.north()), 2);
		else if (!arg.isAir(arg3.south()))
            		arg.setBlockState(arg3, arg.getBlockState(arg3.south()), 2);
            	else
			arg.setBlockState(arg3, BlockStates.STONE, 2);
            }
            ++i;
        }
        if((l == 0 && !flag) || (!(arg2 instanceof OldChunkGenerator) && !flag))
        	cir.setReturnValue(i > 0);
	else
		cir.setReturnValue(false);
    }
}

