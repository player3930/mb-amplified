package com.bespectacled.modernbeta.mixin;

import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.bespectacled.modernbeta.ModernBeta;
import com.bespectacled.modernbeta.api.world.gen.ChunkProvider;
import com.bespectacled.modernbeta.world.gen.OldChunkGenerator;
import com.bespectacled.modernbeta.world.gen.provider.IndevChunkProvider;
import com.bespectacled.modernbeta.world.gen.provider.indev.IndevTheme;

import net.fabricmc.fabric.mixin.gamerule.IntRuleAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.SpawnLocating;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Redirect(
        method = "setupSpawn", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/server/network/SpawnLocating;findServerSpawnPoint(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/ChunkPos;Z)Lnet/minecraft/util/math/BlockPos;"
        )
    )
    private static BlockPos redirectSpawnLocating(ServerWorld world, ChunkPos chunkPos, boolean validSpawnNeeded) {
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        BlockPos spawnPos = SpawnLocating.findServerSpawnPoint(world, chunkPos, validSpawnNeeded);
        
        if (chunkGenerator instanceof OldChunkGenerator) {
            ChunkProvider chunkProvider = ((OldChunkGenerator)chunkGenerator).getChunkProvider();
            
            ((IntRuleAccessor)world.getGameRules().get(GameRules.SPAWN_RADIUS)).setValue(0); // Ensure a centered spawn
            spawnPos = chunkProvider.locateSpawn().orElse(spawnPos);
            
            if (spawnPos != null && ModernBeta.DEV_ENV) {
                ModernBeta.log(Level.INFO, String.format("Spawning at %d/%d/%d", spawnPos.getX(), spawnPos.getY(), spawnPos.getZ()));
            }
            
            if (spawnPos != null && chunkProvider instanceof IndevChunkProvider) {
                // Generate Indev house
                ((IndevChunkProvider)chunkProvider).generateIndevHouse(world, spawnPos);
                
                // Set Indev world properties.
                setIndevProperties(world, ((IndevChunkProvider)chunkProvider).getLevelTheme());
            }
        }
        
        return spawnPos;
    }
    
    @Unique
    private static void setIndevProperties(ServerWorld world, IndevTheme theme) {
        switch(theme) {
            case HELL:
                world.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(false, null); 
                world.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(false, null); 
                world.setTimeOfDay(18000);
                break;
            case PARADISE:
                world.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(false, null); 
                world.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(false, null); 
                world.setTimeOfDay(6000);
                break;
            case WOODS:
                world.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(false, null); 
                world.setWeather(0, Integer.MAX_VALUE, true, false);
                break;
            default:
                break;
        }
    }
}
