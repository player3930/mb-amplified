package com.bespectacled.modernbeta.mixin.client;

import java.util.Optional;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.bespectacled.modernbeta.ModernBeta;
import com.bespectacled.modernbeta.api.world.biome.ClimateBiomeProvider;
import com.bespectacled.modernbeta.api.world.biome.climate.ClimateSampler;
import com.bespectacled.modernbeta.api.world.biome.climate.SkyClimateSampler;
import com.bespectacled.modernbeta.client.color.BetaBlockColors;
import com.bespectacled.modernbeta.util.GenUtil;
import com.bespectacled.modernbeta.util.ModernBetaClientWorld;
import com.bespectacled.modernbeta.world.biome.OldBiomeSource;
import com.bespectacled.modernbeta.world.biome.provider.climate.BetaClimateSampler;
import com.bespectacled.modernbeta.world.biome.provider.climate.BetaClimateSampler.BetaSkyClimateSampler;
import com.bespectacled.modernbeta.world.gen.OldChunkGenerator;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Environment(EnvType.CLIENT)
@Mixin(value = ClientWorld.class, priority = 1)
public abstract class MixinClientWorld implements ModernBetaClientWorld {
    @Shadow private MinecraftClient client;
    
    @Unique private BlockPos capturedPos;
    @Unique private Optional<ClimateSampler> climateSampler;
    @Unique private Optional<SkyClimateSampler> skyClimateSampler;
    @Unique private boolean isModernBetaWorld;
    
    @Override
    public boolean isModernBetaWorld() {
        return this.isModernBetaWorld;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(
        ClientPlayNetworkHandler netHandler,
        ClientWorld.Properties properties,
        RegistryKey<World> registryRef, 
        DimensionType dimensionType, 
        int loadDistance,
        Supplier<Profiler> profiler,
        WorldRenderer renderer,
        boolean debugWorld,
        long seed,
        CallbackInfo info
    ) {
        long worldSeed = GenUtil.parseSeed(ModernBeta.RENDER_CONFIG.configFixedSeed.fixedSeed);
        boolean useFixedSeed = ModernBeta.RENDER_CONFIG.configFixedSeed.useFixedSeed;
        
        // Init with default values
        this.climateSampler = Optional.ofNullable(useFixedSeed ? new BetaClimateSampler(worldSeed) : null);
        this.skyClimateSampler = Optional.ofNullable(useFixedSeed ? new BetaSkyClimateSampler(worldSeed) : null);
        this.isModernBetaWorld = false;
        
        // Server check
        if (this.client.getServer() != null && registryRef != null) {
            ChunkGenerator chunkGenerator = this.client.getServer().getWorld(registryRef).getChunkManager().getChunkGenerator();
            BiomeSource biomeSource = chunkGenerator.getBiomeSource();
            
            worldSeed = this.client.getServer().getWorld(registryRef).getSeed();
            
            if (biomeSource instanceof OldBiomeSource &&
                ((OldBiomeSource)biomeSource).getBiomeProvider() instanceof ClimateBiomeProvider
            ) {
                this.climateSampler = Optional.ofNullable(((ClimateBiomeProvider)((OldBiomeSource)biomeSource).getBiomeProvider()).getClimateSampler());
                this.skyClimateSampler = Optional.ofNullable(((ClimateBiomeProvider)((OldBiomeSource)biomeSource).getBiomeProvider()).getSkyClimateSampler());
            }
            
            this.isModernBetaWorld = chunkGenerator instanceof OldChunkGenerator || biomeSource instanceof OldBiomeSource;
        }
        
        // Set Beta block colors seed.
        BetaBlockColors.INSTANCE.setSeed(worldSeed, this.climateSampler);
    }
    
    @Inject(method = "method_23777", at = @At("HEAD"))
    private void capturePos(BlockPos cameraPos, float tickDelta, CallbackInfoReturnable<Integer> info) {
        this.capturedPos = cameraPos;
    }
    
    @ModifyVariable(
        method = "method_23777",
        at = @At(value = "INVOKE_ASSIGN",  target = "Lnet/minecraft/world/biome/Biome;getSkyColor()I"),
        index = 6  
    )
    private int injectBetaSkyColor(int skyColor) {
        if (this.skyClimateSampler.isPresent() && this.skyClimateSampler.get().sampleSkyColor()) {
            int x = (int)capturedPos.getX();
            int z = (int)capturedPos.getZ();
            
            float temp = (float)this.skyClimateSampler.get().sampleSkyTemp(x, z);
            temp /= 3F;
            temp = MathHelper.clamp(temp, -1F, 1F);
            
            skyColor = MathHelper.hsvToRgb(0.6222222F - temp * 0.05F, 0.5F + temp * 0.1F, 1.0F);
        }
        
        return skyColor;
    }
}

